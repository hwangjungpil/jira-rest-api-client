package com.ktds.jirarestapiclient.controller;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ktds.jirarestapiclient.MyJiraClient;
import com.ktds.jirarestapiclient.domain.IssueDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value="/api")
@RestController
public class ApiController {
    @Value("${jira.username}")
    private String username;
    @Value("${jira.password}")
    private String password;
    @Value("${jira.jiraUrl}")
    private String jiraUrl;
    @Value("${jira.projectKey}")
    private String projectKey;
    @Value("${jira.issueType}")
    private Long issueType;
    private MyJiraClient client;


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

    @PostMapping(value="/issue", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveIssue(@RequestBody IssueDomain issueDomain){
        MyJiraClient client = new MyJiraClient(this.username,this.password,this.jiraUrl);

        System.out.println(issueDomain.getProjectKey() +","+ issueDomain.getIssueType()+","+issueDomain.getIssueSummary()+","+issueDomain.getDescription());
        Issue issue=client.createIssue(issueDomain.getProjectKey(), issueDomain.getIssueType(), issueDomain.getIssueSummary(), issueDomain.getDescription());

        return issue.getKey();
    }

    @PostMapping(value="/issue")
    public IssueDomain saveIssueFormRequest(IssueDomain issueDomain){
        MyJiraClient client = new MyJiraClient(this.username,this.password,this.jiraUrl);

        System.out.println(issueDomain.getProjectKey() +","+ issueDomain.getIssueType()+","+issueDomain.getIssueSummary()+","+issueDomain.getDescription());
        Issue issue=client.createIssue(issueDomain.getProjectKey(), issueDomain.getIssueType(), issueDomain.getIssueSummary(), issueDomain.getDescription());

        IssueDomain returnIssueDomain = new IssueDomain();

        returnIssueDomain.setKey(issue.getKey());
        returnIssueDomain.setUrl(jiraUrl+"/browse/"+issue.getKey());
        returnIssueDomain.setIssueSummary(issue.getSummary());
        returnIssueDomain.setDescription(issue.getDescription());

        return returnIssueDomain;
    }

    @GetMapping(value="/issue/{id}")
    public String getIssue(@PathVariable String id){
        MyJiraClient client = new MyJiraClient(this.username,this.password,this.jiraUrl);
        return client.getIssue(id).toString();
    }
}
