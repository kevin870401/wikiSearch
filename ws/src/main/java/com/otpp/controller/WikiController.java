package com.otpp.controller;

import com.otpp.wikisearch.domain.DomainPage;
import com.otpp.wikisearch.domain.WikiArticle;
import com.otpp.wikisearch.service.WikiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/article")
public class WikiController {

    private final WikiService wikiService;

    public WikiController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @RequestMapping(value = "/{id}", method = GET, headers = "Accept=application/json")
    public
    @ResponseBody
    WikiArticle getArticle(@PathVariable("id") String id) {
        return wikiService.getWikiArticle(id);
    }

    @RequestMapping(value = "/textSearch", method = GET, headers = "Accept=application/json")
    public
    @ResponseBody
    DomainPage<WikiArticle> getWikiArticleContainsText(@RequestParam("keyword") String keyword) {
        return wikiService.getWikiArticleContainsText(keyword);
    }

}
