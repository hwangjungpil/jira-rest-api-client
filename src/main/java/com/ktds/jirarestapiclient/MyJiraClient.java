package com.ktds.jirarestapiclient;





import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;

public class MyJiraClient {
    private final String username;
    private final String password;
    private final String jiraUrl;
    private final JiraRestClient restClient;

   public MyJiraClient(String username, String password, String jiraUrl){
       this.username=username;
       this.password=password;
       this.jiraUrl=jiraUrl;
       this.restClient = getJiraRestClient();
   }
   private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
   }

   private URI getJiraUri(){
        return URI.create(this.jiraUrl);
   }
   public String createIssue(String projectKey, Long issueType, String issueSummary){
       IssueRestClient issueClient = restClient.getIssueClient();
       IssueInput newIssue = new IssueInputBuilder(
               projectKey, issueType, issueSummary).build();
       return issueClient.createIssue(newIssue).claim().getKey();
   }
}
