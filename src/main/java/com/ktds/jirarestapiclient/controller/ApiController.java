package com.ktds.jirarestapiclient.controller;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.util.concurrent.Promise;
import com.ktds.jirarestapiclient.MyJiraClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping(value="/api")
@RestController
public class ApiController {
    @Value("${jira.username}")
    String username;
    @Value("${jira.password}")
    String password;
    @Value("${jira.jiraUrl}")
    String jiraUrl;
    @Value("${jira.projectKey}")
    String projectKey;
    @Value("${jira.issueType}")
    Long issueType;

    @GetMapping(value="/test", produces = "application/json")
//    @GetMapping(value="/test")
    @ResponseStatus(value= HttpStatus.OK)
    public String getApiTest() {

        MyJiraClient client = new MyJiraClient(this.username,this.password,this.jiraUrl);
//        MyJiraClient client = new MyJiraClient("thinkhero","P@ssw0rd","http://localhost:8080");
//        String key=client.createIssue(this.projectKey, this.issueType, "api test3","description sample");

        Issue issue=client.createIssue(this.projectKey, this.issueType, "api test3","description sample");
        return issue.toString();
    }

    @GetMapping(value="/issue/{id}")
    public String getIssue(@PathVariable String id){
        MyJiraClient client = new MyJiraClient(this.username,this.password,this.jiraUrl);
        return client.getIssue(id).toString();
    }



}
