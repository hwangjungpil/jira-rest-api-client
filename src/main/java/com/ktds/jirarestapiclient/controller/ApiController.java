package com.ktds.jirarestapiclient.controller;

import com.ktds.jirarestapiclient.MyJiraClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping(value="/api/test", method = RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.OK)
    public String getApiTest() {
        MyJiraClient client = new MyJiraClient("thinkhero","P@ssw0rd","http://localhost:8080");
        client.createIssue("TEST", 10005L, "api test");
        return "{\"result\":\"ok\"}";
    }

}
