package com.framework.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        try {
            Reader reader = new FileReader(Paths.get("src", "test", "resources", "testdata", fileName).toFile());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord record : csvParser) {
                String[] row = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }
                data.add(row);
            }
            csvParser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
