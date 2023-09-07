package com.markvp.countwords;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author mvp
 * Sep 7, 2023
 */
@Data
@AllArgsConstructor
public class CountWordsResult {

    private int numberOfWordsStartsWithM;
    
    private List<String> wordsLongerThanFive;
}
