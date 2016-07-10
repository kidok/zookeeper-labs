package bolts;

import java.io.File;
import java.util.Map;

import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;

public class DocumentBolt implements IRichBolt {
    private static final long serialVersionUID = 1L;
    
    private OutputCollector collector;
    private String inputDir;
    
    @Override
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        inputDir = stormConf.get("inputFile").toString();
    }

    @Override
    public void execute(Tuple input) {
        String fileName = input.getString(0);
        File file = new File(fileName);
        
        
        try {
            // do something with the file
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close resources
        }
        collector.ack(input);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override
    public void cleanup() {
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
