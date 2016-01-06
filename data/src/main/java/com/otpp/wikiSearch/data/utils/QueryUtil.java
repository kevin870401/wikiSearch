package com.otpp.wikiSearch.data.utils;

import com.otpp.wikiSearch.data.entity.WikiFacet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.data.solr.core.query.result.ValueCountEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class QueryUtil {

    private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("\\p{Punct}");

    public static Collection<String> splitSearchTermAndRemoveIgnoredCharacters(String searchTerm) {
        String[] searchTerms = StringUtils.split(searchTerm, " ");
        List<String> result = new ArrayList<String>(searchTerms.length);
        for (String term : searchTerms) {
            if (StringUtils.isNotEmpty(term)) {
                result.add(IGNORED_CHARS_PATTERN.matcher(term)
                                                .replaceAll(" "));
            }
        }
        return result;
    }

    public static List<WikiFacet> convertWikiUserFromFacetPage(Iterator<SimpleFacetFieldEntry>
            simpleFacetFieldEntryIterator){
        List<WikiFacet> wikiFacets = new ArrayList<>();
        while (simpleFacetFieldEntryIterator.hasNext()) {
            ValueCountEntry temp= simpleFacetFieldEntryIterator.next();
            wikiFacets.add(new WikiFacet(temp.getValue(), temp.getValueCount()));
        }
        return wikiFacets;
    }
}
