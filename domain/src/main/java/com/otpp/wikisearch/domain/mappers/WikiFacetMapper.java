package com.otpp.wikisearch.domain.mappers;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class WikiFacetMapper
        extends DefaultMapper<com.otpp.wikisearch.domain.WikiFacet, com.otpp.wikiSearch.data.entity.WikiFacet> {

    @Autowired
    public WikiFacetMapper(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    @PostConstruct
    public void init() {
        configureMapperFactory();
    }

    @Override
    protected Class<com.otpp.wikiSearch.data.entity.WikiFacet> dbClass() {
        return com.otpp.wikiSearch.data.entity.WikiFacet.class;
    }

    @Override
    protected Class<com.otpp.wikisearch.domain.WikiFacet> domainClass() {
        return com.otpp.wikisearch.domain.WikiFacet.class;
    }
}