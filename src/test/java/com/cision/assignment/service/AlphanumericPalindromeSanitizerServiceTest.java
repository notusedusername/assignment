package com.cision.assignment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class AlphabeticPalindromeSanitizerServiceTest {

    private final AlphabeticPalindromeSanitizerService service = new AlphabeticPalindromeSanitizerService();

    @ParameterizedTest
    @ValueSource(strings = {
            "onlyAlphabetic", "asdasdasdasdasd", "testValue", "abrakadabra",
            "isThisAPalindrome"
    })
    void whenOnlyAlphabeticCharactersWerePassedThenReturnThat(String passedText) {
        Assertions.assertEquals(passedText, service.sanitizeText(passedText));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "not Only Alphabetic, notOnlyAlphabetic",
            "asd\\asd\\asd, asdasdasd",
            "test@&d°%'\"a$ßt|/a, testdata",
            "abraka      d       abra, abrakadabra",
    })
    void whenNotOnlyAlphabeticCharactersWerePassedThenReturnTextContainingOnlyAlphabetic(String passedText, String expectedText) {
        Assertions.assertEquals(expectedText, service.sanitizeText(passedText));
    }
}
