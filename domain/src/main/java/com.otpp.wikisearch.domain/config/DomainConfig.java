package com.otpp.wikisearch.domain.config;

import com.otpp.wikisearch.domain.mappers.WikiArticleMapper;
import com.otpp.wikisearch.domain.mappers.WikiFacetMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    private final MapperFactory defaultMapperFactory=new DefaultMapperFactory.Builder().build();

    @Bean
    public WikiArticleMapper wikiArticleMapper() {
        return new WikiArticleMapper(defaultMapperFactory);
    }
    @Bean
    public WikiFacetMapper wikiFacetMapper() {
        return new WikiFacetMapper(defaultMapperFactory);
    }
}
