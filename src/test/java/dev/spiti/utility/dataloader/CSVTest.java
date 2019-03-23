package dev.spiti.utility.dataloader;

import dev.spiti.utility.datareader.readers.CSV;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;

public class CSVTest {

    String file = "test";


    @DataProvider
    public Object[] providerTest() {
        return new CSV(file).getData();
    }

    @DataProvider
    public Object[] providerFilter() {
        return new CSV(file).getData("store", "00001");
    }

    @Test (dataProvider = "providerTest")
    public void loadCsvTest(Map<String, String> map) {
         System.out.println(map);
    }

    @Test (dataProvider = "providerFilter")
    public void csvTestFilter(Map<String, String> map) {
            System.out.println(map);
    }




}
