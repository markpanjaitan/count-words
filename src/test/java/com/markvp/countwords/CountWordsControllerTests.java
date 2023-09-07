package com.markvp.countwords;

import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author mvp
 * Sep 7, 2023
 */
@WebMvcTest(CountWordsController.class)
public class CountWordsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private CountWordsService countWordsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCountWords() throws Exception {
        // Create a mock Excel file for testing
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/test1.xlsx");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test1.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileInputStream);

        // Define the expected results for the service call
        List<String> mockLongWords = Arrays.asList("MarkValentinoWord1", "MarkValentinoWord2");
        when(countWordsService.countWords(mockMultipartFile)).thenReturn(new CountWordsResult(2, mockLongWords));

        // Perform the file upload and test the response
        mockMvc.perform(MockMvcRequestBuilders.multipart("/count-words/upload")
                .file(mockMultipartFile))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfWordsStartsWithM").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.wordsLongerThanFive[0]").value("MarkValentinoWord1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.wordsLongerThanFive[1]").value("MarkValentinoWord2"));
    }
}
