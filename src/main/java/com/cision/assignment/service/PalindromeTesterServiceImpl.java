package com.cision.assignment.service;

import org.springframework.stereotype.Component;

@Component
public class PalindromeTesterServiceImpl implements PalindromeTesterService {

    @Override
    public boolean isPalindrome(String word) {
        return new StringBuilder(word)
                .reverse()
                .toString()
                .equalsIgnoreCase(word);
    }
}
