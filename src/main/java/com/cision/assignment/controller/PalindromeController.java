package com.cision.assignment.controller;

import com.cision.assignment.model.PalindromeModel;
import com.cision.assignment.projection.PalindromeProjection;
import com.cision.assignment.service.PalindromeService;
import com.cision.assignment.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiPaths.PALINDROMES,
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class PalindromeController {

    private final PalindromeService palindromeService;

    @PostMapping(value = "/",
        consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PalindromeProjection> createPalindromeCheckRequest(@RequestBody @Valid PalindromeModel palindromeModel) {
        return ResponseEntity.ok(palindromeService.submitPalindromeCheckRequest(palindromeModel));
    }

    @GetMapping("/")
    public ResponseEntity<Page<PalindromeProjection>> getSubmittedPalindromeChecks(Pageable pageable,
                                                                                   @RequestParam(name = "filter", required = false) String filter) {
        return ResponseEntity.ok(palindromeService.getPalindromeRequests(pageable, filter));
    }
}
