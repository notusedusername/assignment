package com.cision.assignment.service;

import com.cision.assignment.entity.PalindromeEntity;
import com.cision.assignment.model.PalindromeModel;
import com.cision.assignment.projection.PalindromeProjection;
import com.cision.assignment.repository.PalindromeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.AdditionalAnswers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PalindromeServiceTest {

    @MockBean
    private LongestPalindromeService longestPalindromeService;

    @MockBean
    private PalindromeRepository palindromeRepository;

    @Autowired
    private SpelAwareProxyProjectionFactory projectionFactory;

    @Autowired
    private PalindromeService palindromeService;

    @ParameterizedTest
    @CsvSource(value = {
            "this is the content, 1",
            "abrakadabra, 3",
            "some other content, 10"
    })
    void whenPalindromeSubmittedThenItWillBeSavedWithCalculatedPalindromeLength(String content, int length) {
        when(longestPalindromeService.getLongestPalindromeSize(content)).thenReturn(length);
        when(palindromeRepository.save(any(PalindromeEntity.class))).then(AdditionalAnswers.returnsFirstArg());

        var timestamp = ZonedDateTime.now();
        var model = PalindromeModel.builder()
            .content(content)
            .timestamp(timestamp)
            .build();
        var projection = palindromeService.submitPalindromeCheckRequest(model);

        Assertions.assertEquals(content, projection.getContent());
        Assertions.assertEquals(length, projection.getlongest_palindrome_size());
        Assertions.assertEquals(timestamp, projection.getTimestamp());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "randomFilterStringContent, 1"
    })
    void whenPalindromeListRequestedThenReturnWithPagedList(String content, int length) {
        var pageable = PageRequest.of(1, 25, Sort.by(Sort.Direction.DESC, "timestamp"));
        var filter = content.substring(1);

        var timeStamp = ZonedDateTime.now();
        var projection = projectionFactory.createProjection(
                PalindromeProjection.class,
                PalindromeEntity.builder()
                    .content(content)
                    .timestamp(timeStamp)
                    .longestPalindrome(length)
                    .build());
        var expectedPage = new PageImpl<>(List.of(projection));

        when(palindromeRepository.findAllProjection(pageable, filter)).thenReturn(expectedPage);

        Assertions.assertEquals(expectedPage, palindromeService.getPalindromeRequests(pageable, filter));
        verify(palindromeRepository).findAllProjection(pageable, filter);
    }
}
