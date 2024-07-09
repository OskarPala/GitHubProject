package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@Log4j2
@SpringBootApplication
@EnableFeignClients
public class GitHubProjectApplication {
    @Autowired
    GitHubApiProxy gitHubApiProxy;
    @Autowired
    GitHubService gitHubService;

    public static void main(String[] args) {
        SpringApplication.run(GitHubProjectApplication.class, args);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void makeRequest() {
        List<AllInfoResult> resultList = gitHubService.makeRequestToGithubEndpoint("OskarPala");

        log.info(resultList);
    }

}
