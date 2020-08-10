package com.study.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {
    	KieServices kieSevices = KieServices.Factory.get();
        return kieSevices.getKieClasspathContainer();
    }
    
    
}
