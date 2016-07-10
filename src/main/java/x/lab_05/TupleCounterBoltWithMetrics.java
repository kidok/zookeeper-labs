package x.lab_05;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import x.utils.MetricsUtil;

import java.util.Map;

public class TupleCounterBoltWithMetrics extends BaseBasicBolt {
  private static final Logger LOG =
      LoggerFactory.getLogger(TupleCounterBoltWithMetrics.class);
  long counter;

  // # TODO-1 : declare metrics as 'transient' fields


  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    this.counter = 0L;

    // ## TODO-2 : initialize metrics
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    this.counter++;
    LOG.debug(this.counter + " : " + tuple);

    // ## TODO-3 increment metrics
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

}
