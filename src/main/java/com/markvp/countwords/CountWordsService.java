package com.markvp.countwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mvp
 * Sep 7, 2023
 */
@Service
public class CountWordsService {

	public CountWordsResult countWordsExcel(MultipartFile file) throws IOException {
		
        // Read the uploaded Excel file
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Initialize counts and result lists
        int wordStartsWithMCount = 0;
        List<String> longWords = new ArrayList<>();

        // Iterate through rows and cells to count words
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.length() > 5) {
                        longWords.add(cellValue);
                    }
                    if (cellValue.startsWith("M") || cellValue.startsWith("m")) {
                        wordStartsWithMCount++;
                    }
                }
            }
        }

        // Result Object
        CountWordsResult result = new CountWordsResult(wordStartsWithMCount, longWords);
        workbook.close();

        return result;
	}

}
