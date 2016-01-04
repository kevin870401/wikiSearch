package com.otpp.wikiSearch.data.repository;

import org.springframework.data.solr.core.query.result.FacetPage;

/**
 * Created by root on 04/01/16.
 */
public interface WikiRepositoryCustom {

    String getTopContributorUserName(FacetPage facetPage);

    long getTopContributorArticleNumber(FacetPage facetPage);
}
