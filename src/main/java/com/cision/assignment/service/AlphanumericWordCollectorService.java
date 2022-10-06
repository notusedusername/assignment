package com.cision.assignment.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlphanumericWordCollectorService implements WordCollectorService {

    private static final String NOT_ALPHANUMERIC_PATTERN = "^[0-9a-zA-Z]";
    @Override
    public Set<String> collectWords(String text) {
        return Arrays.stream(text.split(NOT_ALPHANUMERIC_PATTERN)).collect(Collectors.toSet());
    }
}
