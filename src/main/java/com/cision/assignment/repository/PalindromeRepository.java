package com.cision.assignment.repository;

import com.cision.assignment.entity.PalindromeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeRepository extends CrudRepository<PalindromeEntity, Long> {


}
