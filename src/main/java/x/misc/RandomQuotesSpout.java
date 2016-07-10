// adopted from : https://github.com/apache/storm/tree/master/examples/storm-starter

package x.misc;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;

public class RandomQuotesSpout extends BaseRichSpout {
  SpoutOutputCollector _collector;
  Random _rand;

  static String[] quotes = new String[]{
          "640 K ought to be enough for anybody - Bill Gates, 1981 (may be)",
          "I'm doing a (free) operating system (just a hobby, won't be big and professional like gnu) - Linus Torvalds 1991",
          "more data usually beats better algorithms -- unknown",
          "software is eating the world - unknown, 1990s",
          "machine learning is eating software - unknown, 2010"
        };



  @Override
  public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
    _collector = collector;
    _rand = new Random();
  }

  @Override
  public void nextTuple() {
    Utils.sleep(500);
    String sentence = quotes[_rand.nextInt(quotes.length)];
    _collector.emit(new Values(sentence));
  }

  @Override
  public void ack(Object id) {
  }

  @Override
  public void fail(Object id) {
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {
    declarer.declare(new Fields("quote"));
  }

}
