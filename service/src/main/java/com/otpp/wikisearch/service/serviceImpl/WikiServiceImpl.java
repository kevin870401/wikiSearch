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

    public WikiServiceImpl(WikiRepository wikiRepository, WikiArticleMapper wikiArticleMapper) {
        this.wikiRepository = wikiRepository;
        this.wikiArticleMapper = wikiArticleMapper;
    }

    @Override
    public WikiArticle getWikiArticle(String id) {
        return wikiArticleMapper.mapDbToDomain(wikiRepository.findOne(id));

    }

    @Override
    public DomainPage<WikiArticle> getWikiArticleContainsText(String text) {
        DomainPage<WikiArticle> resultPage = new DomainPage(
                wikiRepository.findByTextContaining(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(text),
                        FIRST_TEN), wikiArticleMapper);
        return resultPage;
    }
}
