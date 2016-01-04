package com.otpp.wikiSearch.data.repository;


import com.otpp.wikiSearch.data.entity.WikiArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.SolrResultPage;

import java.util.Collection;

public class CustomWikiRepositoryImpl implements CustomWikiRepository {
    @Override
    public String getTopContributorUserName(FacetPage facetPage) {
        return((SimpleFacetFieldEntry)((SolrResultPage)facetPage.getFacetResultPages().iterator().next()).getContent().iterator().next()).getValue();

    }

    @Override
    public long getTopContributorArticleNumber(FacetPage facetPage) {
        return((SimpleFacetFieldEntry)((SolrResultPage)facetPage.getFacetResultPages().iterator().next()).getContent().iterator().next()).getValueCount();

    }
}
