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

public class CostCounterBolt extends BaseBasicBolt {
  private static final Logger LOG = LoggerFactory.getLogger(CostCounterBolt.class);

  private Map<String, Integer> costs;

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    costs = new HashMap<String, Integer>();
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    LOG.debug("got tuple : " + tuple);
    String campaign = tuple.getStringByField("campaign");
    int cost = tuple.getIntegerByField("cost");
    int totalSoFar = costs.getOrDefault(campaign, 0);
    // ## TODO-1 : update the cost
    // costs.put(campaign,  ???);
    printCounts();
  }


  private void printCounts() {
    TreeSet <String> countSet = new TreeSet(costs.keySet());
    LOG.debug(countSet.size() + " entries in the costs map");
    for (String domain : countSet) {
      LOG.debug(String.format("    %s = %s", domain, costs.get(domain)));
    }
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer arg0) {
    // no output
  }

}
