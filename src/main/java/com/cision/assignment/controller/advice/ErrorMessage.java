package com.cision.assignment.controller.advice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class ErrorMessage {
    private final List<String> errors;
}
