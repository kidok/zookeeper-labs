package x.lab_02;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DomainCounterBolt extends BaseBasicBolt {
  private static final Logger LOG =
      LoggerFactory.getLogger(DomainCounterBolt.class);

  private Map<String, Integer> counts;

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    counts = new HashMap<String, Integer>();
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    LOG.debug("got tuple : " + tuple);
    // ## TODO - 1 : extract 'domain'
    // ## increment the count and put it back
    // String domain = tuple.getStringByField(???);
    // int newCount = countFor(domain) + 1
    // counts.put(???, ???)
    printCounts();
  }

  private Integer countFor(String domain) {
    Integer count = counts.get(domain);
    return count == null ? 0 : count;
  }

  private void printCounts() {
    TreeSet<String> countSet = new TreeSet(counts.keySet());
    LOG.debug(countSet.size() + " entries in the map");
    for (String domain : countSet) {
      LOG.debug(String.format("   %s = %d", domain, counts.get(domain)));
    }
  }

}
