package com.otpp.wikisearch.service.serviceImpl;

import com.otpp.wikiSearch.data.configuration.SolrContext;
import com.otpp.wikisearch.service.WikiService;
import com.otpp.wikisearch.service.config.WikiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {WikiConfig.class, SolrContext.class})
public class WikiServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WikiService wikiService;

    @Test
    public void getWikiArticle_idExist_success() {
        assertThat(wikiService.getWikiArticle("10")).isNotNull();
    }
}