package com.otpp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WikiController {



  @RequestMapping(value = "/people", method = GET, headers = "Accept=application/json")
  public @ResponseBody String getJivePeopleById(@RequestParam("id") long id) {
    return "";

  }


}
