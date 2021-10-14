package com.ktds.jirarestapiclient;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyJiraClient {
    private final String username;
    private final String password;
    private final String jiraUrl;
    private final JiraRestClient restClient;
    private final long timeout;

   public MyJiraClient(String username, String password, String jiraUrl){
       this.username=username;
       this.password=password;
       this.jiraUrl=jiraUrl;
       this.restClient = getJiraRestClient();
       this.timeout=30;
   }
   private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
   }

   private URI getJiraUri(){
        return URI.create(this.jiraUrl);
   }
   public Issue createIssue(String projectKey, Long issueType, String issueSummary, String description){
       IssueRestClient issueClient = restClient.getIssueClient();
       IssueInput newIssue = new IssueInputBuilder(
               projectKey, issueType, issueSummary).setDescription(description).build();
//       return issueClient.createIssue(newIssue).claim().getKey();
       String key= issueClient.createIssue(newIssue).claim().getKey();
       return getIssue(key);
   }

   public Issue getIssue(String issueKey){
       IssueRestClient issueRestClient = restClient.getIssueClient();
       try {
           return issueRestClient.getIssue(issueKey).get(timeout, TimeUnit.SECONDS);
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (TimeoutException e) {
           e.printStackTrace();
       }
       return null;
   }

}
