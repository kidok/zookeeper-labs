package x.examples.docproc.main;

import x.examples.docproc.bolts.DocumentBolt;
import x.examples.docproc.spouts.DirectoryReaderSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;

public class StormStart {
    
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            printUsageAndExit();
        }
        
        String inputDir = args[0]; 
        int numberOfBolts = 1;
        
        try {
            numberOfBolts = Integer.parseInt(args[1]);
        } catch (Exception e) {
            printUsageAndExit();
        }
        
        
        Config config = new Config();
        config.put("inputFile", inputDir);
        config.setDebug(true);
        
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("directory-reader-spout", new DirectoryReaderSpout());
        builder.setBolt("eml-bolt", new DocumentBolt(), numberOfBolts).shuffleGrouping(
                "directory-reader-spout");
        
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("ES Storm", config, builder.createTopology());
    }
    
    private static void printUsageAndExit() {
        System.out.println("Usage: java -jar storm-1.0-SNAPSHOT-jar-with-dependencies.jar <input file/dir> <number of bolts>");
        System.exit(-1);
    }
}
