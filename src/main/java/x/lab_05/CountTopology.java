package x.lab_05;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

public class CountTopology {
  public static void main(String[] args) throws Exception {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("clickstream", new ClickstreamSpout());
    builder.setBolt("counter", new TupleCounterBoltWithMetrics())
        .shuffleGrouping("clickstream");

    // start a local cluster
    Config conf = new Config();
    // conf.setDebug(false);
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("countTopology", conf, builder.createTopology());

    // remote cluster
    // StormSubmitter.submitTopologyWithProgressBar("countTopology", conf,
    //    builder.createTopology());

    // wait a few and shutdown
    Utils.sleep(30000);
    cluster.shutdown();
  }
}
