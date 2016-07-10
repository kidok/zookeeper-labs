package x.bolts;

import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import x.utils.MetricsUtil;

public class TupleCounterBoltWithMetrics extends BaseBasicBolt {
  private static final Logger LOG =
      LoggerFactory.getLogger(TupleCounterBoltWithMetrics.class);
  long counter;

  // metrics - must be transient
  private transient Counter tuplesCount;
  private transient Meter tuplesMeter;

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    this.counter = 0L;

    tuplesCount = MetricsUtil.metrics.counter(
        MetricRegistry.name(TupleCounterBoltWithMetrics.class, "tuples_received"));
    tuplesMeter = MetricsUtil.metrics.meter(
        MetricRegistry.name(TupleCounterBoltWithMetrics.class, "tuples"));
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    this.counter++;
    tuplesCount.inc();
    tuplesMeter.mark();
    LOG.debug(this.counter + " : " + tuple);
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

}
