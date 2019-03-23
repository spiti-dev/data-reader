package dev.spiti.utility.dataloader;


import dev.spiti.utility.datareader.readers.Excel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelTest {

    String file = "text";


    @DataProvider
    public Object[] providerTest() {
        return new Excel(file, "Sheet1").getData();
    }

    @DataProvider
    public Object[] providerFilter() {
        return new Excel(file, "Sheet1").getData("Header1","test21");
    }

    @Test (dataProvider = "providerTest")
    public void loadExcel(Map<String, String> map) {
         System.out.println(map);

    }

    @Test (dataProvider = "providerFilter")
    public void loadFilteredExcel(Map<String, String> map) {
            System.out.println(map);
    }
}
