package com.otpp.wikisearch.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class WikiFacet {

    String userName;
    long articleCount;
}
