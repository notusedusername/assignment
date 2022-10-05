package com.cision.assignment.util;

import org.springframework.stereotype.Component;

@Component
public interface PalindromeProcessor {
    int getLongestPalindromeSize(final String text);
}
