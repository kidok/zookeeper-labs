package x.lab_02;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class DomainCountTopology {
  public static void main(String[] args) {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();
    
    // TODO-1 : setSpout
    // builder.setSpout(???, ???);
    
    // TODO-2 set 'DomainExtractorBolt'
    // shuffleGroup connect with 'clickstream' spout
    // builder.setBolt(???, ????). ????
    
    // TODO-3 : set bolt : 'DomainCounterBolt'
    // fieldGroup it to previous bolt
    // builder.setBolt(???  ???) 
    //    .fieldsGrouping("domain-extractor", new Fields("domain"));

    // start a local cluster
    Config conf = new Config();
    // conf.setDebug(false);
    LocalCluster cluster = new LocalCluster();
    cluster.submitTopology(DomainCountTopology.class.getSimpleName(), conf,
        builder.createTopology());

    // wait a few and shutdown
    Utils.sleep(30000);
    cluster.shutdown();
  }
}
