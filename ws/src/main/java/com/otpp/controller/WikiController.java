package com.otpp.controller;

import com.otpp.wikisearch.service.WikiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/wikiArticleAdvance")
public class WikiController {

    private final WikiService wikiService;

    public WikiController(WikiService wikiService) {
        this.wikiService = wikiService;
    }
/*
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
*/
    @RequestMapping(value = "/topAuthor", method = GET, headers = "Accept=application/json")
    public
    @ResponseBody
    String getTopContributorUserName() {
        return wikiService.getTopContributorUserName();
    }

    @RequestMapping(value = "/topAuthorArticleAmount", method = GET, headers = "Accept=application/json")
    public
    @ResponseBody
    long getTopContributorArticleAmount() {
        return wikiService.getTopContributorArticleAmount();
    }
}
