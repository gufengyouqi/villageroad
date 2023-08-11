package com.villageroad.config;

import org.kie.api.KieServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    @Bean(value = "kieServices")
    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }
}
