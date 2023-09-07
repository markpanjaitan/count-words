package com.markvp.countwords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

/**
 * @author mvp
 * Sep 7, 2023
 */
public class CountWordsServiceTest {

    @Mock
    private CountWordsService countWordsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createMockExcelThenVerifyResult() throws IOException {
        // Create a mock Excel file
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/test1.xlsx");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test1.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileInputStream);

        // Define the expected results
        List<String> mockLongWords = Arrays.asList("MarkValentinoWord1", "MarkValentinoWord2");
        CountWordsResult expectedCountWordsResult = new CountWordsResult(2, mockLongWords);

        // Mock the service
        when(countWordsService.countWordsExcel(mockMultipartFile)).thenReturn(expectedCountWordsResult);

        CountWordsResult actualCountWordsResult = countWordsService.countWordsExcel(mockMultipartFile);

        // Assertions
        assertEquals(2, actualCountWordsResult.getNumberOfWordsStartsWithM());
        assertEquals(mockLongWords, actualCountWordsResult.getWordsLongerThanFive());
    }
}
