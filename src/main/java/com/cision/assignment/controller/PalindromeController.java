package com.cision.assignment.controller;

import com.cision.assignment.model.PalindromeModel;
import com.cision.assignment.projection.PalindromeProjection;
import com.cision.assignment.service.PalindromeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/palindromes",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class PalindromeController {

    private final PalindromeService palindromeService;

    @PostMapping("/")
    public ResponseEntity<PalindromeProjection> createPalindromeCheckRequest(@RequestBody @Valid PalindromeModel palindromeModel) {
        return ResponseEntity.ok(palindromeService.submitPalindromeCheckRequest(palindromeModel));
    }
}
