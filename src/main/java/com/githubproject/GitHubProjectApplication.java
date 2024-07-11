package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

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




}
