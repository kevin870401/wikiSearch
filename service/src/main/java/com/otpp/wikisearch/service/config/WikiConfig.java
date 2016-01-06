package com.otpp.wikisearch.service.config;

import com.otpp.wikiSearch.data.repository.WikiRepository;
import com.otpp.wikisearch.domain.mappers.WikiFacetMapper;
import com.otpp.wikisearch.service.WikiService;
import com.otpp.wikisearch.domain.mappers.WikiArticleMapper;
import com.otpp.wikisearch.service.serviceImpl.WikiServiceImpl;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WikiConfig {

    @Autowired
    private WikiRepository wikiRepository;

    @Autowired
    public WikiArticleMapper wikiArticleMapper;

    @Autowired
    public WikiFacetMapper wikiFacetMapper;

    @Bean
    public WikiService wikiService() {
        return new WikiServiceImpl(wikiRepository, wikiArticleMapper,wikiFacetMapper);
    }
}
