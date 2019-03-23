package dev.spiti.utility.datareader.readers;

import dev.spiti.utility.datareader.DataReader;
import org.apache.log4j.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseReader implements DataReader {

    public List<Map<String, String>> data = new ArrayList<>();
    public final Logger LOGGER = Logger.getLogger(this.getClass());



    public String getPath() {
        return System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "test" + File.separator
                + "resources" + File.separator;
    }

    public List<Map<String, String>> filter(String key, String value) {
        return data.stream().filter(m -> isValidRecord(m, key, value))
                .collect(Collectors.toList());
    }

    public static boolean isValidRecord(Map<String, String> stringObjectMap,String key , String value) {
        return stringObjectMap.getOrDefault(key,"").equals(value);
    }
}
