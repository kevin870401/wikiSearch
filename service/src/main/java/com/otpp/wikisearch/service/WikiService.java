package com.otpp.wikisearch.service;

import com.otpp.wikisearch.domain.WikiFacet;

import java.util.List;

public interface WikiService {
    List<WikiFacet> getTopContributors();
    String getTopContributorUserName();
    long getTopContributorArticleAmount();
}
