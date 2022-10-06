package com.cision.assignment.service;

import org.springframework.stereotype.Component;

@Component
public class PalindromeTesterServiceImpl implements PalindromeTesterService {

    @Override
    public boolean isPalindrome(String word) {
        var maxIndex = word.length() - 1;
        for (int i = 0; i < word.length() / 2; i++) {
            if(word.charAt(i) != word.charAt(maxIndex - i)) {
                return false;
            }
        }
        return true;
    }
}
