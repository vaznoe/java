package com.company.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
  @RequestMapping("/")
  public String index() {
    return "index"; // return template index.html
  }
}
