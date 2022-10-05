package com.cision.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "palindrome")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalindromeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private ZonedDateTime timestamp;

    private Integer longestPalindrome;

}
