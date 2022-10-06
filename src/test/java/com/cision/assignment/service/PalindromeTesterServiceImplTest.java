package com.cision.assignment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PalindromeTesterServiceImplTest {

    private PalindromeTesterServiceImpl service = new PalindromeTesterServiceImpl();

    @ParameterizedTest
    @ValueSource(strings = {
            "ada", "sadas", "Mr. Owl ate my metal worm",
            "Was it a car or a cat I saw", "1221", "1a2bb2a1"
    })
    void whenPassingPalindromeThenReturnTrue(String palindrome) {
        Assertions.assertTrue(service.isPalindrome(palindrome));
    }
}
