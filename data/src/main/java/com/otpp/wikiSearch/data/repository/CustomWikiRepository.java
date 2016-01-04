package com.otpp.wikiSearch.data.repository;

import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;

/**
 * Created by root on 04/01/16.
 */
public interface CustomWikiRepository{
    String getTopContributorUserName(FacetPage facetPage);
    long getTopContributorArticleNumber(FacetPage facetPage);
}
