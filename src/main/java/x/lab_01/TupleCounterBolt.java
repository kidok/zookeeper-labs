package x.lab_01;

import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TupleCounterBolt extends BaseBasicBolt {
  private static final Logger LOG =
      LoggerFactory.getLogger(TupleCounterBolt.class);

  long counter;

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    // TODO-1 : initialize the counter to zero (optional)
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    // TODO-2 : increment the counter and print something to log
    // LOG.debug("????")
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // TODO-3 : what goes here?
  }

}
