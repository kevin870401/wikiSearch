package com.otpp.wikiSearch.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Date;

@Data
@SolrDocument(solrCoreName = "wiki")
public class WikiArticle {
    private @Id @Indexed String id;
    private @Indexed("title") String title;
    private @Indexed("revision") int revision;
    private @Indexed("user") String user;
    private @Indexed("userId") int userId;
    private @Indexed("text") String text;
    private @Indexed("time") Date time;

    @Override
    public String toString() {
        return "WikiArticle [id=" + id + ", title=" + title + "]";
    }
}
