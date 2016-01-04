package com.otpp.wikisearch.service.serviceImpl;

import com.otpp.wikiSearch.data.repository.WikiRepository;
import com.otpp.wikisearch.domain.WikiArticle;
import com.otpp.wikisearch.service.WikiService;
import com.otpp.wikisearch.service.mappers.WikiArticleMapper;

public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;
    private final WikiArticleMapper wikiArticleMapper;

    public WikiServiceImpl(WikiRepository wikiRepository, WikiArticleMapper wikiArticleMapper) {
        this.wikiRepository = wikiRepository;
        this.wikiArticleMapper = wikiArticleMapper;
    }

    @Override
    public WikiArticle getWikiArticle(String id) {
        return wikiArticleMapper.mapDbToDomain(wikiRepository.findOne(id));

    }
}
