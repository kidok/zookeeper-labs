package x.lab_02;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DomainExtractorBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(DomainExtractorBolt.class);

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    // TODO-1 : we are emiting 'domain' field
  }
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    // ## TODO-2 : extract 'clickstream' field 
    String clickstream = tuple.getStringByField("???");
    
    // ## TODO-3 : split the clickstream string by ,
    // ## extract the domain column
    // ## emit it
  }
  

}
