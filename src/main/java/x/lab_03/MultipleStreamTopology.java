package x.lab_03;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class MultipleStreamTopology {
  public static void main(String[] args) {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("clickstream", new ClickstreamSpout());

    // TODO-1 : set the 'splitter' bolt, connect with 'clickstream' using shuffleGrouping
    // builder.setBolt("bolt name ??? ", new StreamSplitterBolt())
    //    .shuffleGrouping(" spout name");

    // TODO-2 : set DomainCounterBOlt, field group with 'splitter bolt
    // builder.setBolt("domain-counter", new DomainCounterBolt())
    //    .fieldsGrouping("from bolt name ?? ", "stream name ??? ", new Fields ("domain"));

    // TODO-3 : set CostCounterBolt, connect with 'splitter' bolt using fieldsgrouping
    // builder.setBolt("campaign-cost-calculator", new CostCounterBolt())
    //    .fieldsGrouping("from bolt name ??? ", "stream name ???", new Fields("campaign", "cost"));

    // start a local cluster
    Config conf = new Config();
    // conf.setDebug(false);
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology(MultipleStreamTopology.class.getSimpleName(), conf,
        builder.createTopology());

    // wait a few and shutdown
    Utils.sleep(30000);
    cluster.shutdown();
  }
}
