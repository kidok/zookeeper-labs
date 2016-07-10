package x.remote;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

import org.apache.storm.utils.Utils;

public class CountTopology {
  public static void main(String[] args) throws Exception {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("clickstream", new ClickstreamSpout());
    builder.setBolt("counter", new TupleCounterBolt())
        .shuffleGrouping("clickstream");

    /*
    // start a local cluster
    Config conf = new Config();
     conf.setDebug(false);
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("countTopology", conf, builder.createTopology());
     // wait a few and shutdown
     Utils.sleep(30000);
    cluster.shutdown();
    */

    // remote cluster
     Config conf = new Config();
     StormSubmitter.submitTopologyWithProgressBar("countTopology", conf,
         builder.createTopology());


    // wait for enter from console
    // System.out.println ("Enter when ready to quit : ");
    // System.console().readLine();
    // System.out.println ("terminating....");

  }
}
