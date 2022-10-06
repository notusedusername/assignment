package com.cision.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LongestPalindromeServiceImpl implements LongestPalindromeService {

    private final PalindromeTesterService palindromeTester;
    private final PalindromeSanitizerService sanitizerService;

    @Override
    public int getLongestPalindromeSize(String text) {
        var alphanumericTextOnly = sanitizerService.sanitizeText(text);
        var palindromeCandidates = getAllPalindromeCandidates(alphanumericTextOnly);
        return getLongestPalindromeFrom(palindromeCandidates);
    }

    private int getLongestPalindromeFrom(Set<String> palindromeCandidates) {
        return palindromeCandidates.stream().sorted(Comparator.comparingInt(String::length).reversed())
                .filter(palindromeTester::isPalindrome)
                .findFirst()
                .map(String::length)
                .orElse(0);
    }

    private Set<String> getAllPalindromeCandidates(String word) {
        var candidates = new HashSet<String>();
        for (var start = 0; start < word.length(); start++) {
            for(var end = start + 1; end <= word.length(); end++) {
                candidates.add(word.substring(start, end));
            }
        }
        return candidates;
    }
}
