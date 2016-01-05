package com.otpp.controller;


import com.otpp.wikisearch.domain.WikiArticle;
import com.otpp.wikisearch.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WikiController {
    private final WikiService wikiService;
    public WikiController(WikiService wikiService){
        this.wikiService=wikiService;
    }

  @RequestMapping(value = "/article", method = GET, headers = "Accept=application/json")
  public @ResponseBody WikiArticle getArticle(@RequestParam("id") String id) {
    return wikiService.getWikiArticle(id);
  }


}
