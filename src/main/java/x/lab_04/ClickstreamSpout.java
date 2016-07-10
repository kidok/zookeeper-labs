package x.lab_04;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import x.utils.ClickStreamGenerator;

import java.util.Map;

public class ClickstreamSpout extends BaseRichSpout {

  private static final Logger LOG =
      LoggerFactory.getLogger(ClickstreamSpout.class);

  SpoutOutputCollector collector;

  @Override public void open(Map conf, TopologyContext context,
      SpoutOutputCollector collector) {
    this.collector = collector;
  }

  @Override public void nextTuple() {
    Utils.sleep(500);
    String clickstream = ClickStreamGenerator.getClickstreamAsCsv();
    Values v = new Values(clickstream);
    collector.emit(v);

    LOG.debug("sent tuple : " + v);
  }

  @Override public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("clickstream"));
  }

}
