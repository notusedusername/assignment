package com.cision.assignment.service;

import org.springframework.stereotype.Service;

@Service
public class AlphabeticPalindromeSanitizerService implements PalindromeSanitizerService {

    private static final String NOT_ALPHABETIC_PATTERN = "[^a-zA-Z]";

    @Override
    public String sanitizeText(String text) {
        return text.replaceAll(NOT_ALPHABETIC_PATTERN, "");
    }
}
