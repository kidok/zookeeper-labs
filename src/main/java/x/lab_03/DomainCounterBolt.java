package x.lab_03;

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
  private static final Logger LOG = LoggerFactory.getLogger(DomainCounterBolt.class);

  private Map<String, Integer> counts;
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    counts = new HashMap<String, Integer>();
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    LOG.debug("got tuple : " + tuple);
    String domain = tuple.getStringByField("domain");
    int currentCount = counts.getOrDefault(domain, 0);
    // TODO-1 : increment and update the count
    // counts.put(domain, ???);
    printCounts();
  }

  private void printCounts() {
    TreeSet <String> countSet = new TreeSet(counts.keySet());
    LOG.debug(countSet.size() + " entries in the map");
    for (String domain : countSet) {
      LOG.debug(String.format("   %s = %s", domain, counts.get(domain)));
    }
  }
  
  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

}
