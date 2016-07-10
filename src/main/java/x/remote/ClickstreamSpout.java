// adapted from : https://github.com/apache/storm/tree/master/examples/storm-starter

package x.remote;

import java.util.Map;

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

public class ClickstreamSpout extends BaseRichSpout {

  private static final Logger LOG =
      LoggerFactory.getLogger(ClickstreamSpout.class);

  SpoutOutputCollector collector;

  @Override
  public void open(Map conf, TopologyContext context,
      SpoutOutputCollector collector) {
    this.collector = collector;
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("clickstream"));
  }

  @Override
  public void nextTuple() {
    Utils.sleep(500);
    String clickstream = ClickStreamGenerator.getClickstreamAsCsv();
    // clickstream = 1451635200005,ip_67,user_16,clicked,facebook.com,campaign_5,91,session_251
    Values v = new Values(clickstream);
    collector.emit(v);

    LOG.debug("sent tuple : " + v);
  }



}
