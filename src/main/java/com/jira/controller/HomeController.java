package com.jira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {
    @GetMapping("/")
    public String navigateToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
