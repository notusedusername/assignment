package com.cision.assignment.controller;

import com.cision.assignment.util.ApiPaths;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PalindromeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(10)
    void whenContentFormatInvalidReturnWithBadRequest() throws Exception {
        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content("{\"invalid\": json"))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Order(20)
    void whenRequiredPropertiesMissingReturnWithBadRequest() throws Exception {

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"other\": \"property\"}"))
                .andExpect(
                    status().isBadRequest()
                );
    }

    @Test
    @Order(30)
    void whenRequiredPropertiesAreInvalidReturnWithBadRequestAndDetails() throws Exception {

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"content\": \"   \", \"timestamp\": null}"))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.errors").exists(),
                        jsonPath("$.errors").isArray(),
                        jsonPath("$.errors.length()").value(Matchers.greaterThan(0))
                );

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"content\": \"   \", \"timestamp\": \"2022-10-06 01:13:12+0100\"}"))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.errors").exists(),
                        jsonPath("$.errors").isArray(),
                        jsonPath("$.errors.length()").value(Matchers.greaterThan(0))
                );

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"content\": \"   \", \"timestamp\": \"asd\"}"))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.errors").exists(),
                        jsonPath("$.errors").isArray(),
                        jsonPath("$.errors.length()").value(Matchers.greaterThan(0))
                );

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"content\": \"  some content\", \"timestamp\": \"20123321421\"}"))
                .andExpectAll(
                        status().isBadRequest(),
                        jsonPath("$.errors").exists(),
                        jsonPath("$.errors").isArray(),
                        jsonPath("$.errors.length()").value(Matchers.greaterThan(0))
                );
    }

    @Test
    @Order(40)
    void whenParamsAreValidThenCreateRecord() throws Exception {
        var content = "Mr. Owl ate my metal worm";
        var timestamp = "2018-10-09 00:13:12+0100";
        var length = "MrOwlatemymetalworm".length();

        mockMvc.perform(post(ApiPaths.PALINDROMES + "/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content("{\"content\": \"Mr. Owl ate my metal worm\", \"timestamp\": \"2018-10-09 00:13:12+0100\"}"))
            .andExpectAll(
                    status().isOk(),
                    jsonPath("$.content").value(content),
                    // todo fix timestamp format
                    //jsonPath("$.timestamp").value(timestamp),
                    jsonPath("$.longest_palindrome_size").value(length));
    }

    @Test
    @Order(50)
    void whenListRequestedThenReturnWithListOfSubmittedEntities() throws Exception {
        var filterValue = "r";
        var pageSize = 5;
        mockMvc.perform(get(ApiPaths.PALINDROMES + "/")
                .queryParam("size", String.valueOf(pageSize))
                .queryParam("filter", filterValue))
            .andExpectAll(
                    status().isOk(),
                    jsonPath("$.content.length()").value(Matchers.lessThanOrEqualTo(pageSize)),
                    jsonPath("$.content[*].content").exists(),
                    jsonPath("$.content[*].timestamp").exists(),
                    jsonPath("$.content[*].longest_palindrome_size").exists(),
                    jsonPath("$.size").value(pageSize)

            );
    }
}
