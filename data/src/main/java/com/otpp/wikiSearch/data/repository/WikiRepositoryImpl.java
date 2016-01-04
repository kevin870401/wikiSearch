package com.otpp.wikiSearch.data.repository;

import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.SolrResultPage;
//this file must named as the original WikiRepository + Impl even it is not implementing WikiRepository otherwise
// repository mapping exception will be thrown
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
