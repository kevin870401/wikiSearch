package com.otpp.wikisearch.service.config;

import com.otpp.wikiSearch.data.repository.WikiRepository;
import com.otpp.wikisearch.service.WikiService;
import com.otpp.wikisearch.service.mappers.WikiArticleMapper;
import com.otpp.wikisearch.service.serviceImpl.WikiServiceImpl;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class WikiConfig {

    @Autowired
    private WikiRepository wikiRepository;

    @Bean
    public WikiArticleMapper wikiArticleMapper() {
        return new WikiArticleMapper(defaultMapperFactory());
    }

    @Bean
    public MapperFactory defaultMapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    public WikiService wikiService() {
        return new WikiServiceImpl(wikiRepository, wikiArticleMapper());
    }
}
