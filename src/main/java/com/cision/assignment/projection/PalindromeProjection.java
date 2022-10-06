package com.cision.assignment.projection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.time.ZonedDateTime;

public interface PalindromeProjection {
    String getContent();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    ZonedDateTime getTimestamp();
    @Value("#{target.longestPalindrome}")
    int getlongest_palindrome_size();
}
