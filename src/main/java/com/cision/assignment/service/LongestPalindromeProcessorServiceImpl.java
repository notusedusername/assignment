package com.cision.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LongestPalindromeProcessorServiceImpl implements LongestPalindromeService {

    private final PalindromeTesterService palindromeTester;
    private final WordCollectorService wordCollector;

    private static final int MIN_PALINDROME_LENGTH = 1;
    @Override
    public int getLongestPalindromeSize(String text) {
        var validWords = wordCollector.collectWords(text);
        var palindromeCandidates = tearDownAllWords(validWords);
        return getLongestPalindromeFrom(palindromeCandidates);
    }

    private int getLongestPalindromeFrom(Set<String> palindromeCandidates) {
        return palindromeCandidates.stream().sorted(Comparator.comparingInt(String::length).reversed())
                .filter(palindromeTester::isPalindrome)
                .findFirst()
                .map(String::length)
                .orElse(MIN_PALINDROME_LENGTH);
    }

    private HashSet<String> tearDownAllWords(Set<String> validWords) {
        var candidates = new HashSet<>(validWords);
        for(var word : validWords) {
            putAllSubStringsOfWordToCandidates(word, candidates);
        }
        return candidates;
    }

    private void putAllSubStringsOfWordToCandidates(String word, Set<String> candidates) {
        for (var start = 0; start < word.length(); start++) {
            for(var end = start + 1; end < word.length(); end++) {
                candidates.add(word.substring(start, end));
            }
        }
    }
}
