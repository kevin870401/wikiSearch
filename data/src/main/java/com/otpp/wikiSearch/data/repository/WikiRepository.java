package com.otpp.wikiSearch.data.repository;

import com.otpp.wikiSearch.data.entity.WikiArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "wikiArticle", path = "wikiArticle")
public interface WikiRepository extends SolrCrudRepository<WikiArticle, String>, WikiRepositoryCustom {

    @Highlight(prefix = "<b>", postfix = "</b>")
    @Query(fields = {"id", "title"}, defaultOperator = Operator.AND)
    HighlightPage<WikiArticle> findByTitleIn(@Param("title") Collection<String> title, Pageable page);

    long countByTitleStartsWith(@Param("title") Collection<String> titleFragments);

    @Facet(fields = {"user"})
    FacetPage<WikiArticle> findByTitleStartsWith(@Param("title")Collection<String> titleFragments, Pageable pagebale);

    @Facet(fields = {"user"}, minCount = 1)
    @Query(value = "*:*")
    FacetPage<WikiArticle> getUsersFacetSortedByArticleCount(Pageable page);

    Page<WikiArticle> findByTextContaining(@Param("text") Collection<String> textFragments, Pageable pagebale);

}
