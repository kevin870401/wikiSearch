package com.otpp.wikiSearch.data.repository;

import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.SolrResultPage;

public class WikiRepositoryImpl implements WikiRepositoryCustom {

    @Override
    public String getTopContributorUserName(FacetPage facetPage) {
        return ((SimpleFacetFieldEntry) ((SolrResultPage) facetPage.getFacetResultPages()
                                                                   .iterator()
                                                                   .next()).getContent()
                                                                           .iterator()
                                                                           .next()).getValue();

    }

    @Override
    public long getTopContributorArticleNumber(FacetPage facetPage) {
        return ((SimpleFacetFieldEntry) ((SolrResultPage) facetPage.getFacetResultPages()
                                                                   .iterator()
                                                                   .next()).getContent()
                                                                           .iterator()
                                                                           .next()).getValueCount();

    }
}
