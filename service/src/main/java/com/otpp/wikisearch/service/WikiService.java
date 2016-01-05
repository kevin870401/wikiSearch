package com.otpp.wikisearch.service;

import com.otpp.wikisearch.domain.DomainPage;
import com.otpp.wikisearch.domain.WikiArticle;
import org.springframework.data.domain.Page;

public interface WikiService {

    WikiArticle getWikiArticle(String id);
    DomainPage<WikiArticle> getWikiArticleContainsText(String text);
}
