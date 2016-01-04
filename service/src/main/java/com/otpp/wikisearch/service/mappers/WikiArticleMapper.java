package com.otpp.wikisearch.service.mappers;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class WikiArticleMapper
        extends DefaultMapper<com.otpp.wikisearch.domain.WikiArticle, com.otpp.wikiSearch.data.entity.WikiArticle> {

    @Autowired
    public WikiArticleMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    protected Class<com.otpp.wikiSearch.data.entity.WikiArticle> dbClass() {
        return com.otpp.wikiSearch.data.entity.WikiArticle.class;
    }

    @Override
    protected Class<com.otpp.wikisearch.domain.WikiArticle> domainClass() {
        return com.otpp.wikisearch.domain.WikiArticle.class;
    }
}
