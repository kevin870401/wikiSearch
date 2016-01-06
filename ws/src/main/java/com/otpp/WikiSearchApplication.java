package com.otpp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Import(RepositoryRestMvcConfiguration.class)
@SpringBootApplication
public class WikiSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiSearchApplication.class, args);
    }
}
