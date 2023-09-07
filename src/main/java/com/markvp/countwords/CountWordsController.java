package com.markvp.countwords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> countWordsExcel(@RequestParam("file") MultipartFile file) throws Exception {
    	
    	// Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        
        // Check if the file is an Excel file
        String contentType = file.getContentType();
        if (contentType != null && contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
        	return ResponseEntity.ok(countWordsService.countWordsExcel(file));        	
        } else {
            return ResponseEntity.badRequest().body("Please use Excel file");
        }
    }

}
