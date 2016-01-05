package com.otpp.configuration;

import com.otpp.controller.WikiController;
import com.otpp.wikiSearch.data.entity.WikiArticle;
import com.otpp.wikisearch.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class ControllerConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private WikiService wikiService;
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Lazy
    public HttpEntity<String> jsonRequestEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/json");
        return new HttpEntity<String>(requestHeaders);
    }
    
    @Bean
    public WikiController wikiController() {
        WikiController controller = new WikiController(wikiService);
        return controller;
    }


    
}