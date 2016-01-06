package com.otpp.wikiSearch.data.configuration;

import com.otpp.wikiSearch.data.repository.WikiRepositoryCustom;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@PropertySource("classpath:config.properties")
@EnableSolrRepositories(basePackages = {"com.otpp.wikiSearch.data.repository"}, multicoreSupport = true)
public class SolrContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${solr.host}")
    private String solrHostUrl;

    @Bean
    public SolrServer solrServer() {
        return new HttpSolrServer(solrHostUrl);
    }
}
