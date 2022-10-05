package com.cision.assignment.repository;

import com.cision.assignment.entity.PalindromeEntity;
import com.cision.assignment.projection.PalindromeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeRepository extends PagingAndSortingRepository<PalindromeEntity, Long> {

    @Query("SELECT p FROM PalindromeEntity p WHERE content LIKE CONCAT('%', COALESCE(:filter,''), '%')")
    Page<PalindromeProjection> findAllProjection(Pageable pageable, String filter);
}
