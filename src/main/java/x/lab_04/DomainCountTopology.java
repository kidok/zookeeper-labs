package x.lab_04;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class DomainCountTopology {
  public static void main(String[] args) {

    // setup topology
    TopologyBuilder builder = new TopologyBuilder();
    builder.setSpout("clickstream", new ClickstreamSpout());
    
    builder.setBolt("domain-extractor", new ClickstreamSplitterBolt())
        .shuffleGrouping("clickstream");
    
    builder.setBolt("domain-counter", new DomainPersistorBolt())
        .fieldsGrouping("domain-extractor", new Fields("domain"));

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
