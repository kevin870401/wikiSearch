package com.otpp.wikiSearch.data.repository;

import com.otpp.wikiSearch.data.entity.WikiArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Collection;
public interface WikiRepository extends SolrCrudRepository<WikiArticle,String>,CustomWikiRepository {
    @Highlight(prefix = "<b>", postfix = "</b>")
    @Query(fields = { "id", "title" }, defaultOperator = Operator.AND)
    HighlightPage<WikiArticle> findByTitleIn(Collection<String> title, Pageable page);

    long countByTitleStartsWith(Collection<String> titleFragments);


    @Facet(fields = { "user"})
    FacetPage<WikiArticle> findByTitleStartsWith(Collection<String> titleFragments, Pageable pagebale);

    @Facet(fields = { "id"})
    FacetPage<WikiArticle> findByTextContaining(Collection<String> textFragments, Pageable pagebale);


    @Facet(fields={"user"}, minCount=1, limit=1)
    @Query(value="*:*")
    FacetPage<WikiArticle> getUsersFacetSortedByArticleCount(Pageable page);

}
