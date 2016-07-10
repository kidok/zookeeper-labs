package x.examples.docproc.main;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by mark on 5/20/15.
 */
public class StormStartTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StormStartTest.class);

    @Test
    public void testCompleteParse()  throws Exception {
        LOGGER.info("JUnit: testCompleteParse");
        String [] args = new String[2];
        args[0] = "test-data";
        args[1] = "1";
        StormStart.main(args);
    }
}