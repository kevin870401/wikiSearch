package com.otpp.wikisearch.service.serviceImpl;

import com.otpp.wikiSearch.data.repository.WikiRepository;
import com.otpp.wikiSearch.data.repository.WikiRepositoryCustom;
import com.otpp.wikiSearch.data.utils.QueryUtil;
import com.otpp.wikisearch.domain.DomainPage;
import com.otpp.wikisearch.domain.WikiArticle;
import com.otpp.wikisearch.domain.WikiFacet;
import com.otpp.wikisearch.domain.mappers.WikiArticleMapper;
import com.otpp.wikisearch.domain.mappers.WikiFacetMapper;
import com.otpp.wikisearch.service.WikiService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class WikiServiceImpl implements WikiService {

    private final WikiRepository wikiRepository;
    private final WikiArticleMapper wikiArticleMapper;
    private final WikiFacetMapper wikiFacetMapper;
    private static final Pageable FIRST_TEN = new PageRequest(0, 10);
    private static final Pageable ONE_ITEM = new PageRequest(0, 1);
    public WikiServiceImpl(WikiRepository wikiRepository, WikiArticleMapper wikiArticleMapper,WikiFacetMapper wikiFacetMapper) {
        this.wikiRepository = wikiRepository;
        this.wikiArticleMapper = wikiArticleMapper;
        this.wikiFacetMapper = wikiFacetMapper;
    }

    @Override
    public List<WikiFacet> getTopContributors() {
        return wikiFacetMapper.mapDbToDomain( WikiRepositoryCustom.getTopContributors(wikiRepository
                .getUsersFacetSortedByArticleCount(ONE_ITEM)));
    }

    @Override
    public String getTopContributorUserName() {
        return WikiRepositoryCustom.getTopContributorUserName(
                wikiRepository.getUsersFacetSortedByArticleCount(ONE_ITEM));
    }

    @Override
    public long getTopContributorArticleAmount() {
        return WikiRepositoryCustom.getTopContributorArticleAmount(wikiRepository.getUsersFacetSortedByArticleCount(ONE_ITEM));
    }


}
