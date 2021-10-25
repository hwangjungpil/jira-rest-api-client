package com.ktds.jirarestapiclient.domain;

import lombok.Data;

@Data
public class IssueDomain {
    private String projectKey;
    private Long issueType;
    private String issueSummary;
    private String description;
    private String url;
    private String key;
}
