package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static Object[][] readSheet(String fileName, String sheetName) {
        String path = Paths.get("src", "test", "resources", "testdata", fileName).toString();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Iterator<Row> it = sheet.iterator();
            if (!it.hasNext()) {
                return new Object[0][0]; // empty sheet
            }

            // First row is header — capture column count, then skip
            Row header = it.next();
            int cols = header.getLastCellNum();

            List<Object[]> rows = new ArrayList<>();
            while (it.hasNext()) {
                Row row = it.next();
                // skip completely empty rows
                if (row == null) continue;

                Object[] data = new Object[cols];
                boolean allBlank = true;

                for (int c = 0; c < cols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String value = (cell == null) ? "" : formatter.formatCellValue(cell);
                    if (!value.isEmpty()) allBlank = false;
                    data[c] = value;
                }

                if (!allBlank) rows.add(data);
            }

            Object[][] array = new Object[rows.size()][cols];
            for (int i = 0; i < rows.size(); i++) array[i] = rows.get(i);
            return array;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel: " + path + " sheet: " + sheetName);
        }
    }
}
