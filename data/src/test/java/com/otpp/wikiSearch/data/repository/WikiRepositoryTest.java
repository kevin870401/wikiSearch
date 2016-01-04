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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = SolrContext.class)
public class WikiRepositoryTest extends AbstractTestNGSpringContextTests {

    private static final String AFGHANISTAN = "Afghanistan";
    @Autowired
    private WikiRepository wikiRepository;

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void findByTitleIn_plainString_exist() {
        Pageable pageable = new PageRequest(0, 10);
        Page<WikiArticle> wikiArticles =
                wikiRepository.findByTitleIn(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(AFGHANISTAN),
                        pageable);
        assertThat(wikiArticles.getTotalPages() == 1);
        assertThat(wikiArticles.getContent()
                               .get(0)
                               .getTitle()).isEqualTo(AFGHANISTAN);
    }

    @Test
    public void findByTitleIn_StringContainsSpace_notExist() {
        Pageable pageable = new PageRequest(0, 10);
        Page<WikiArticle> wikiArticles = wikiRepository.findByTitleIn(
                QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("Afghanistan communication"), pageable);
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
        Pageable pageable = new PageRequest(0, 10);
        Page<WikiArticle> wikiArticles =
                wikiRepository.findByTitleStartsWith(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters(AFGHANISTAN),
                        pageable);
        assertThat(wikiArticles.getSize()).isEqualTo(10);
        assertThat(wikiArticles.getTotalPages()).isEqualTo(1);
        assertThat(wikiArticles.getContent()
                               .size()).isEqualTo(9);
    }

    @Test
    public void findByTitleStartsWith_a_highestUserCountFoundInFacet() {
        Pageable pageable = new PageRequest(0, 10);
        FacetPage<WikiArticle> wikiArticles =
                wikiRepository.findByTitleStartsWith(QueryUtil.splitSearchTermAndRemoveIgnoredCharacters("A"),
                        pageable);
        assertThat(wikiRepository.getTopContributorUserName(wikiArticles)).isEqualTo("Jim Carter");
        assertThat(wikiRepository.getTopContributorArticleNumber(wikiArticles)).isEqualTo(47);
    }

    @Test
    public void getTopContributorUserName_JimCarter() {
        Pageable pageable = new PageRequest(0, 10);
        FacetPage<WikiArticle> wikiArticles = wikiRepository.getUsersFacetSortedByArticleCount(pageable);
        assertThat(wikiRepository.getTopContributorUserName(wikiArticles)).isEqualTo("Jim Carter");
    }

    @Test
    public void getTopContributorArticleNumber_164() {
        Pageable pageable = new PageRequest(0, 10);
        FacetPage<WikiArticle> wikiArticles = wikiRepository.getUsersFacetSortedByArticleCount(pageable);
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

}