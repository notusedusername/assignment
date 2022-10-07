package com.cision.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LongestPalindromeServiceImpl implements LongestPalindromeService {

    private final PalindromeTesterService palindromeTester;
    private final PalindromeSanitizerService sanitizerService;

    private static final int EMPTY_PALINDROME_LENGTH = 0;
    private static final int MIN_PALINDROME_LENGTH = 0;

    @Override
    public int getLongestPalindromeSize(String text) {
        var alphanumericTextOnly = sanitizerService.sanitizeText(text);
        return getLongestSanitizedPalindrome(alphanumericTextOnly);
    }

    private int getLongestSanitizedPalindrome(String word) {
        if(word.isEmpty()) {
            return EMPTY_PALINDROME_LENGTH;
        } else {
            return getLongestNotEmptyPalindrome(word);
        }
    }

    private int getLongestNotEmptyPalindrome(String word) {
        for(var subStringLength = word.length(); subStringLength > 1; subStringLength--) {
            for(var startIndex = 0; startIndex + subStringLength <= word.length(); startIndex++) {
                var substringToCheck = substringWithLength(word, startIndex, subStringLength);

                if(palindromeTester.isPalindrome(substringToCheck)) {
                    return substringToCheck.length();
                }
            }
        }
        return MIN_PALINDROME_LENGTH;
    }

    private String substringWithLength(String word, int start, int length) {
        return word.substring(start, start + length);
    }
}
