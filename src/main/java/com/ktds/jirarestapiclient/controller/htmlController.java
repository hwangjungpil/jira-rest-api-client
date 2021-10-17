package com.ktds.jirarestapiclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class htmlController {
    @Value("${jira.projectKey}")
    String projectKey;
    @Value("${jira.issueType}")
    Long issueType;
    @GetMapping("")
    public String hello (Model model){
        System.out.println("html Controller hello: "+projectKey + ", "+ issueType);
        model.addAttribute("projectKey",projectKey);
        model.addAttribute("issueType",issueType);
        return "index";
    }
}
