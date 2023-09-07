package com.markvp.countwords;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mvp
 * Sep 7, 2023
 */
@RestController
@RequestMapping("/count-words")
public class CountWordsController {
	
	@Autowired
	CountWordsService countWordsService;
	
    @PostMapping("/upload")
    public CountWordsResult countWords(@RequestParam("file") MultipartFile file) throws IOException {

    	return countWordsService.countWords(file);
    }

}
