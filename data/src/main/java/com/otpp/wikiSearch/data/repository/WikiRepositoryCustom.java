package com.otpp.wikiSearch.data.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.solr.core.query.result.FacetPage;

@RepositoryRestResource(collectionResourceRel = "wikiArticle1", path = "wikiArticle1")
public interface WikiRepositoryCustom {

    String getTopContributorUserName(FacetPage facetPage);

    long getTopContributorArticleAmount(FacetPage facetPage);
}
