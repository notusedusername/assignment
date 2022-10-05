package com.cision.assignment.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public interface PalindromeProjection {
    String getContent();
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    ZonedDateTime getTimestamp();
    @Value("#{target.longestPalindrome}")
    int getlongest_palindrome_size();
}
