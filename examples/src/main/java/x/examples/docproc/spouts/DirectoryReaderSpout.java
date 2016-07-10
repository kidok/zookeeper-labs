package x.examples.docproc.spouts;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;

import java.io.File;
import java.util.Map;

import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Values;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryReaderSpout implements IRichSpout {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(DirectoryReaderSpout.class);

  private SpoutOutputCollector collector;
  private boolean completed = false;
  private TopologyContext context;
  private String inputDir;

  @Override public void open(Map conf, TopologyContext context,
      SpoutOutputCollector collector) {
    System.out.println("DirectoryReaderSpout.open");
    this.context = context;
    this.inputDir = conf.get("inputFile").toString();
    this.collector = collector;
  }

  @Override public void nextTuple() {
    if (completed) {
      return;
    }

    try {
      File f = new File(inputDir);
      emitFile(f);
    } catch (Exception e) {
      throw new RuntimeException("Error reading tuple", e);
    } finally {
      completed = true;
    }

  }

  private void emitFile(File file) {
    if (file.isDirectory()) {
      String[] filesInDir = file.list();
      for (String fileInDir : filesInDir) {
        String name = file.getAbsolutePath() + File.separator + fileInDir;
        File newFile = new File(name);
        emitFile(newFile);
      }
    } else {
      String fileName = file.getAbsolutePath();
      LOGGER.debug("Collector emitting {}", fileName);
      collector.emit(new Values(fileName));
    }
  }

  @Override public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("file"));
  }

  @Override public void close() {
  }

  public boolean isDistributed() {
    return false;
  }

  @Override public void activate() {
  }

  @Override public void deactivate() {
  }

  @Override public void ack(Object msgId) {
  }

  @Override public void fail(Object msgId) {
  }

  @Override public Map<String, Object> getComponentConfiguration() {
    return null;
  }
}
