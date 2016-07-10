package x.remote;

import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TupleCounterBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(TupleCounterBolt.class);
  
  long counter;
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    this.counter = 0L;
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    this.counter ++;
    LOG.debug(this.counter + " : " + tuple);
  }



}
