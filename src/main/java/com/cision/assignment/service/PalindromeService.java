package com.cision.assignment.service;

import com.cision.assignment.entity.PalindromeEntity;
import com.cision.assignment.model.PalindromeModel;
import com.cision.assignment.projection.PalindromeProjection;
import com.cision.assignment.repository.PalindromeRepository;
import com.cision.assignment.util.PalindromeProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PalindromeService {

    private PalindromeRepository palindromeRepository;
    private SpelAwareProxyProjectionFactory projectionFactory;
    private PalindromeProcessor palindromeProcessor;

    public PalindromeProjection submitContentWithPalindrome(PalindromeModel model) {
        var entity = PalindromeEntity.builder()
                .content(model.getContent())
                .timestamp(model.getTimestamp())
                .longestPalindrome(palindromeProcessor.getLongestPalindromeSize(model.getContent()))
                .build();
        entity = palindromeRepository.save(entity);
        return projectionFactory.createProjection(PalindromeProjection.class, entity);
    }
}
