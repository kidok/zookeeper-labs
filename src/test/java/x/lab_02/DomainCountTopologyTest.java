package x.lab_02;

import org.apache.storm.Config;
import org.apache.storm.ILocalCluster;
import org.apache.storm.Testing;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.testing.CompleteTopologyParam;
import org.apache.storm.testing.MkClusterParam;
import org.apache.storm.testing.MockedSources;
import org.apache.storm.testing.TestJob;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Values;
import org.junit.Test;
import x.lab_01.ClickstreamSpout;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DomainCountTopologyTest {
  @Test public void verifyProperValuesAreEmittedByEachBolt() {
    Config config = new Config();
    config.setDebug(true);

    MkClusterParam clusterParam = new MkClusterParam();
    clusterParam.setSupervisors(1);
    clusterParam.setDaemonConf(config);

    Testing.withSimulatedTimeLocalCluster(clusterParam, new TestJob() {
      @Override public void run(ILocalCluster cluster) {
        MockedSources mockedSources = new MockedSources();
        mockedSources.addMockData("clickstream", new Values(
            "1451635200005,ip_67,user_16,clicked,facebook.com,campaign_5,91,session_251"));

        Config config = new Config();
        config.setDebug(true);

        CompleteTopologyParam topologyParam = new CompleteTopologyParam();
        topologyParam.setMockedSources(mockedSources);
        topologyParam.setStormConf(config);

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("clickstream", new ClickstreamSpout());

        builder.setBolt("domain-extractor", new DomainExtractorBolt())
            .shuffleGrouping("clickstream");

        builder.setBolt("domain-counter", new DomainCounterBolt())
            .shuffleGrouping("domain-extractor");
        
        StormTopology topology = builder.createTopology();

        Map result = Testing.completeTopology(cluster, topology, topologyParam);
        System.out.println(
            "from clickstream " + Testing.readTuples(result, "clickstream"));
        assertTrue(Testing.multiseteq(new Values(new Values(
                "1451635200005,ip_67,user_16,clicked,facebook.com,campaign_5,91,session_251")),
            Testing.readTuples(result, "clickstream")));
        assertTrue(Testing.multiseteq(new Values(new Values(
                "facebook.com")),
            Testing.readTuples(result, "domain-extractor")));       
        // Domain counter bolt does not emit any fields, therefore testing with empty Values()
        assertTrue(Testing
            .multiseteq(new Values(), Testing.readTuples(result, "domain-counter")));
      }
    });
  }
}