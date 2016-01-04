package com.otpp.wikisearch.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(includeFieldNames = true)
public class WikiArticle {

    private String id;
    private String title;
    private int revision;
    private String user;
    private int userId;
    private String text;
    private Date time;

    @Override
    public String toString() {
        return "WikiArticle [id=" + id + ", title=" + title + "]";
    }
}
