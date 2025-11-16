package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataLoader {
    
    /**
     * Load test data from JSON file
     */
    public static List<Map<String, String>> loadJson(String filePath) throws IOException {
        InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
            return gson.fromJson(reader, listType);
        }
    }
    
    /**
     * Load test data from CSV file
     */
    public static List<Map<String, String>> loadCsv(String filePath) throws IOException {
        InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        
        List<Map<String, String>> data = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            
            for (CSVRecord record : csvParser) {
                Map<String, String> row = record.toMap();
                data.add(row);
            }
        }
        
        return data;
    }
}

