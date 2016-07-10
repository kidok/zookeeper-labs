package x.lab_03;

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

public class StreamSplitterBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(StreamSplitterBolt.class);

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    String clickstream = tuple.getStringByField("clickstream");
    String[] parts = clickstream.split(",");

    String domain = parts[4];
    Values v1 = new Values(domain);
    // ## TODO-1  : emit v1 into "domain-stream"
    collector.emit("???", v1);
    LOG.debug("emited to domain-stream : " +v1 );

    // Emit [campaign, cost] to additional stream
    String campaign = parts[5];
    int cost = Integer.parseInt(parts[6]);
    // ## TODO-2 : create Values (campaign, cost) and emit to 'campaign-cost-stream'
    // Values v2 = new Values(??? , ???) ;
    // collector.emit(???,  ???);
    // LOG.debug("emited to campaign-cost-stream : " + v2);

  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    // ## TODO-3 : declare two streams : "domain-stream"  and "campaign-cost-stream"
    // ## syntax is : declareStream ('stream-name', Fields)
    // declarer.declareStream("name of stream", new Fields("???"));
    // declarer.declareStream("name of stream 2", new Fields("???", "???"));

  }

}
