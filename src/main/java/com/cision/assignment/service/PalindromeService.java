package com.cision.assignment.service;

import com.cision.assignment.entity.PalindromeEntity;
import com.cision.assignment.model.PalindromeModel;
import com.cision.assignment.projection.PalindromeProjection;
import com.cision.assignment.repository.PalindromeRepository;
import com.cision.assignment.util.PalindromeProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PalindromeService {

    private final PalindromeRepository palindromeRepository;
    private final SpelAwareProxyProjectionFactory projectionFactory;
    private final PalindromeProcessor palindromeProcessor;

    public PalindromeProjection submitPalindromeCheckRequest(PalindromeModel model) {
        var entity = PalindromeEntity.builder()
                .content(model.getContent())
                .timestamp(model.getTimestamp())
                .longestPalindrome(palindromeProcessor.getLongestPalindromeSize(model.getContent()))
                .build();
        entity = palindromeRepository.save(entity);
        return projectionFactory.createProjection(PalindromeProjection.class, entity);
    }

    public Page<PalindromeProjection> getPalindromeRequests(Pageable pageable, String filter) {
        return palindromeRepository.findAllProjection(pageable, filter);
    }
}
