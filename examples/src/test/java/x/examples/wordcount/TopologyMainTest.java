package x.examples.wordcount;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mark
 */
public class TopologyMainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopologyMainTest.class);

    public TopologyMainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class TopologyMain.
     * @throws java.lang.Exception
     */
    @Test
    public void testMain() throws Exception {
        LOGGER.info("TopologyMainTest: testMain");
        String[] args = new String[2];
        args[0] = "test-data";
        args[1] = "1";
        TopologyMain.main(args);
    }

}
