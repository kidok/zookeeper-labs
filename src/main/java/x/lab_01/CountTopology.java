package x.lab_01;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

import org.apache.storm.utils.Utils;

public class CountTopology {
  public static void main(String[] args) throws Exception {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();

    // TODO-1 : setup Spout - and run this class
    // builder.setSpout(???, ???);

    // TODO-2 : setup bolt and run this class
    // builder.setBolt(???, ???)
    
    // TODO-3 : connect bolt and spout using shuffle grouping
    // builder.setBolt(???, ???).shuffleGrouping(???)
    

    // start a local cluster
    Config conf = new Config();
    // conf.setDebug(false);
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology("countTopology", conf, builder.createTopology());

    // remote cluster
    // StormSubmitter.submitTopologyWithProgressBar("countTopology", conf,
    //    builder.createTopology());

    // wait a few seconds and shutdown
    Utils.sleep(30000);
    cluster.shutdown();
  }
}
