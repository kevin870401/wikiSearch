package com.otpp.wikiSearch.data.repository;

import com.otpp.wikiSearch.data.entity.WikiFacet;
import com.otpp.wikiSearch.data.utils.QueryUtil;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.SolrResultPage;

import java.util.Iterator;
import java.util.List;

public interface WikiRepositoryCustom {

    static public List<WikiFacet> getTopContributors(FacetPage facetPage) {
        Iterator<SimpleFacetFieldEntry> facetFieldEntryIterator = ((SolrResultPage) facetPage.getFacetResultPages()
                                                                                             .iterator()
                                                                                             .next()).getContent()
                                                                                                     .iterator();
        return QueryUtil.convertWikiUserFromFacetPage(facetFieldEntryIterator);
    }

    static public String getTopContributorUserName(FacetPage facetPage) {
        return ((SimpleFacetFieldEntry) ((SolrResultPage) facetPage.getFacetResultPages()
                                                                   .iterator()
                                                                   .next()).getContent()
                                                                           .iterator()
                                                                           .next()).getValue();

    }

    static public long getTopContributorArticleAmount(FacetPage facetPage) {
        return ((SimpleFacetFieldEntry) ((SolrResultPage) facetPage.getFacetResultPages()
                                                                   .iterator()
                                                                   .next()).getContent()
                                                                           .iterator()
                                                                           .next()).getValueCount();

    }

}
