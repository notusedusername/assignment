package com.cision.assignment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LongestPalindromeProcessorServiceImpTest {

    @Mock
    private PalindromeTesterService palindromeTesterService;

    @Mock
    private PalindromeSanitizerService sanitizerService;

    @InjectMocks
    private LongestPalindromeServiceImpl service;

    @Test
    void whenSanitizedStringIsEmptyThenReturn0() {
        var input = "test";
        when(sanitizerService.sanitizeText(input)).thenReturn("");
        Assertions.assertEquals(0, service.getLongestPalindromeSize(input));
        verify(sanitizerService).sanitizeText(input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "test, test",
            "test, est",
            "test, st",
            "nonlyAlphabeticTextLong, non",
    })
    void whenSanitizedTextIsNotEmptyTheLongestPalindromeLengthWillReturn(String input, String longestPalindrome) {
        when(sanitizerService.sanitizeText(input)).thenReturn(input);
        when(palindromeTesterService.isPalindrome(anyString())).thenReturn(false);
        when(palindromeTesterService.isPalindrome(longestPalindrome)).thenReturn(true);

        Assertions.assertEquals(longestPalindrome.length(), service.getLongestPalindromeSize(input));
    }
}
