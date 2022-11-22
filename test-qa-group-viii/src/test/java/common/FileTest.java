package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FileTest {

    Common common = new Common();
    private static final Logger log = LogManager.getLogger(FileTest.class);
    @Test
    public void fileCsvTest(){

        String [] array = common.readCsvFile("src/test/resources/example.csv").get(4);

       log.info("Result Jana {} owner of {}", array[1], array[2]);

    }
}
