package com.cision.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PalindromeModel {

    @NotBlank
    private String content;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssZ")
    private ZonedDateTime timestamp;
}
