package com.otpp.wikiSearch.data.repository;

import com.otpp.wikiSearch.data.configuration.SolrContext;
import com.otpp.wikiSearch.data.entity.WikiArticle;
import com.otpp.wikiSearch.data.utils.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = SolrContext.class)
public class WikiRepositoryIT extends AbstractTestNGSpringContextTests {

    private static final String AFGHANISTAN = "Afghanistan";
    private static final Pageable FIRST_TEN = new PageRequest(0, 10);

    @Autowired
    private WikiRepository wikiRepository;

    @Test
    public void findByTitleIn_plainString_exist() {
        Page<WikiArticle> wikiArticles =
                wikiRepository.findByTitleIn(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(AFGHANISTAN),
                        FIRST_TEN);
        assertThat(wikiArticles.getTotalPages() == 1);
        assertThat(wikiArticles.getContent()
                               .get(0)
                               .getTitle()).isEqualTo(AFGHANISTAN);
    }

    @Test
    public void findByTitleIn_StringContainsSpace_notExist() {
        Page<WikiArticle> wikiArticles = wikiRepository.findByTitleIn(
                QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("Afghanistan communication"), FIRST_TEN);
        assertThat(wikiArticles.getTotalPages() == 1);
        assertThat(wikiArticles.getContent()
                               .size() == 0);
    }

    @Test
    public void findOne_validId_exist() {
        WikiArticle wikiArticle = wikiRepository.findOne("10");
        assertThat(wikiArticle.getId()).isEqualTo("10");
    }

    @Test
    public void findByTitleStartsWith_afghanistan_9ArticlesFound() {
        Page<WikiArticle> wikiArticles =
                wikiRepository.findByTitleStartsWith(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(AFGHANISTAN),
                        FIRST_TEN);
        assertThat(wikiArticles.getSize()).isEqualTo(10);
        assertThat(wikiArticles.getTotalPages()).isEqualTo(1);
        assertThat(wikiArticles.getContent()
                               .size()).isEqualTo(9);
    }

    @Test
    public void findByTitleStartsWith_a_highestUserCountFoundInFacet() {
        FacetPage<WikiArticle> wikiArticles =
                wikiRepository.findByTitleStartsWith(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("A"),
                        FIRST_TEN);
        assertThat(wikiRepository.getTopContributorUserName(wikiArticles)).isEqualTo("Jim Carter");
        assertThat(wikiRepository.getTopContributorArticleNumber(wikiArticles)).isEqualTo(47);
    }

    @Test
    public void getTopContributorUserName_JimCarter() {
        FacetPage<WikiArticle> wikiArticles = wikiRepository.getUsersFacetSortedByArticleCount(FIRST_TEN);
        assertThat(wikiRepository.getTopContributorUserName(wikiArticles)).isEqualTo("Jim Carter");
    }

    @Test
    public void getTopContributorArticleNumber_164() {
        FacetPage<WikiArticle> wikiArticles = wikiRepository.getUsersFacetSortedByArticleCount(FIRST_TEN);
        assertThat(wikiRepository.getTopContributorArticleNumber(wikiArticles)).isEqualTo(164);
    }

    @Test
    public void count_equalsToTotalNumber() {
        assertThat(wikiRepository.count()).isEqualTo(5171);

    }

    @Test
    public void countByTitleStartsWith_afghanistan_9ArticlesFound() {
        assertThat(wikiRepository.countByTitleStartsWith(
                QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(AFGHANISTAN))).isEqualTo(9);
    }

    //--------text related tests------------

    @Test
    public void findByTextContaining_kevin_manyArticlesFound() {
        Page result = wikiRepository.findByTextContaining(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("kevin"),
                FIRST_TEN);
        assertThat(result.getNumber()).isEqualTo(0); // current page number
        assertThat(result.getTotalElements()).isEqualTo(355); //total elements
        assertThat(result.getTotalPages()).isEqualTo(36); //total amount of pages
        assertThat(result.getNumberOfElements()).isEqualTo(10);// total elements in current page
        assertThat(result.getSize()).isEqualTo(10); // maximum element in this page
    }

    @Test
    public void findByTextContaining_yinan_1ArticlesFound() {
        Page result = wikiRepository.findByTextContaining(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("yinan"),
                FIRST_TEN);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getNumberOfElements()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(10);
    }
}