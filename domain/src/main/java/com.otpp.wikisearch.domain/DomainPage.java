package com.otpp.wikisearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otpp.wikisearch.domain.mappers.DefaultMapper;
import com.otpp.wikisearch.domain.mappers.WikiArticleMapper;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@ToString(includeFieldNames = true)
public class DomainPage<T> {

    @Autowired
    @JsonIgnore
    private WikiArticleMapper wikiArticleMapper;

    public DomainPage(Page page,DefaultMapper defaultMapper) {
        this.elements = defaultMapper.mapDbToDomain(page.getContent());
        this.pageNumber = page.getNumber();
        this.totalPageAmount = page.getTotalPages();
        this.totalElementAmountInCurrentPage = page.getNumberOfElements();
        this.totalElementAmount = page.getTotalElements();
        this.maximumElementInOnePage = page.getSize();
    }

    private long pageNumber;
    private long maximumElementInOnePage;
    private long totalElementAmount;
    private long totalElementAmountInCurrentPage;
    private long totalPageAmount;
    private List<T> elements;

}
