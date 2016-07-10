package x.lab_04;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import x.utils.DbConnect;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DomainPersistorBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(DomainPersistorBolt.class);

  private Map<String, Integer> counts;
  DbConnect dbInstance;
  
  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    counts = new HashMap<String, Integer>();

    // TODO-1  : initialize dbInstance by calling 'DbConnect.getInstance'
    // dbInstance = ???
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    String domain = tuple.getStringByField("domain");
    int currentCount = counts.getOrDefault(domain,0);
    counts.put(domain, currentCount + 1);
    saveDomainMapToDB();
    printCounts();
  }


  private void printCounts() {
    TreeSet <String> countSet = new TreeSet(counts.keySet());
    LOG.debug(countSet.size() + " entries in the map");
    for (String domain : countSet) {
      LOG.debug(String.format("   %s = %s", domain, counts.get(domain)));
    }
  }
  
  private void saveDomainMapToDB() {
    // TODO-2 : call the right method on dbInstance
  }
  
  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

}
