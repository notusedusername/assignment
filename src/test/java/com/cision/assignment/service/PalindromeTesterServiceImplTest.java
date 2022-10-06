package com.cision.assignment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PalindromeTesterServiceImplTest {

    private final PalindromeTesterServiceImpl service = new PalindromeTesterServiceImpl();

    @ParameterizedTest
    @ValueSource(strings = {
            "ada", "sadas", "MrOwlatemymetalworm",
            "WasitacaroracatIsaw", "abba"
    })
    void whenPassingPalindromeThenReturnTrue(String palindrome) {
        Assertions.assertTrue(service.isPalindrome(palindrome));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "adas", "sadmessedupas", "Mr. Owl ate my metal worms",
            "Was it a car or a cat I saw there", "apple", "notpalindrome",
            "palindrome", "ironically"
    })
    void whenPassingANotPalindromeWordThenReturnFalse(String notPalindrome) {
        Assertions.assertFalse(service.isPalindrome(notPalindrome));
    }
}
