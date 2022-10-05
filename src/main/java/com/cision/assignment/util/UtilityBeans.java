package com.cision.assignment.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

@Configuration
public class UtilityBeans {

    @Bean
    public SpelAwareProxyProjectionFactory getProjectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }
}
