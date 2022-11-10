package common;

import org.junit.jupiter.api.Test;

public class FileTest {

    Common common = new Common();

    @Test
    public void fileCsvTest(){
        common.readCsvFile("src/test/resources/example.csv");

    }
}
