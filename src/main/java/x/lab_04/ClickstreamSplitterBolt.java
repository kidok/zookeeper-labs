package x.lab_04;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ClickstreamSplitterBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(ClickstreamSplitterBolt.class);
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    String clickstream = tuple.getStringByField("clickstream");
    String[] parts = clickstream.split(",");
    collector.emit(new Values(parts[4]));
  }
  
  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("domain"));
  }

}
