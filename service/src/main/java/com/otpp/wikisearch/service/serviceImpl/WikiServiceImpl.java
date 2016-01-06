package com.otpp.wikisearch.service.serviceImpl;

import com.otpp.wikiSearch.data.repository.WikiRepository;
import com.otpp.wikiSearch.data.utils.QueryUtil;
import com.otpp.wikisearch.domain.DomainPage;
import com.otpp.wikisearch.domain.WikiArticle;
import com.otpp.wikisearch.domain.mappers.WikiArticleMapper;
import com.otpp.wikisearch.service.WikiService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;
    private final WikiArticleMapper wikiArticleMapper;
    private static final Pageable FIRST_TEN = new PageRequest(0, 10);
    private static final Pageable ONE_ITEM = new PageRequest(0, 1);
    public WikiServiceImpl(WikiRepository wikiRepository, WikiArticleMapper wikiArticleMapper) {
        this.wikiRepository = wikiRepository;
        this.wikiArticleMapper = wikiArticleMapper;
    }

    @Override
    public String getTopContributorUserName() {
        return wikiRepository.getTopContributorUserName(wikiRepository.getUsersFacetSortedByArticleCount(ONE_ITEM));
    }

    @Override
    public long getTopContributorArticleAmount() {
        return wikiRepository.getTopContributorArticleAmount(wikiRepository.getUsersFacetSortedByArticleCount(ONE_ITEM));
    }


}
