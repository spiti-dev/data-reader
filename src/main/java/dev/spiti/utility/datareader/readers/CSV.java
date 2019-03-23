package dev.spiti.utility.datareader.readers;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class CSV extends BaseReader {

    private static CSVReader READER;
    private static final String EXTENSION = ".csv";


    public CSV() {

    }

    public CSV(String file) {
        this.READER = readFile(file);
    }

    public CSVReader readFile(String file)  {
        CSVReader reader = null;
        try{
            reader =  new CSVReader(new FileReader(getPath() + file + EXTENSION));
        } catch (Exception e) {
            LOGGER.error("Error is reading CSV file " + e.getMessage());
            e.printStackTrace();
        }
        return reader;
    }

    private List<Map<String, String>> getDataAsList() {
        List<List<String>> dataAsList = new ArrayList<>();
        String[] line;
        try{
            while((line = READER.readNext()) != null) {
                dataAsList.add(Arrays.asList(line));
            }
        } catch (Exception e) {
            LOGGER.error("Error is reading CSV data row " + e.getMessage());
            e.printStackTrace();
        }
        List<String> header = dataAsList.get(0);
        for(int i=1; i<dataAsList.size(); i++) {
            List<String> rowData = dataAsList.get(i);
            if (header.size() == rowData.size()) {
                Map<String, String> map = new HashMap<>();
                for(int j=0; j<header.size(); j++) {
                    map.put(header.get(j).trim(), rowData.get(j).trim());
                }
                data.add(map);
            }
        }
        return data;
    }

    public Object[] getData() {
        return getDataAsList().stream().collect(Collectors.toList()).toArray();
    }

    public Object[] getData(String key, String value) {
        getDataAsList();
        return filter(key, value).stream().collect(Collectors.toList()).toArray();
    }
}
