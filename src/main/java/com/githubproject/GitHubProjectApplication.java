package com.githubproject;

import com.githubproject.githubrepositories.domain.proxy.GitHubApiProxy;
import com.githubproject.githubrepositories.domain.service.GitHubRetriever;
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
    GitHubRetriever gitHubProxyService;

    public static void main(String[] args) {
        SpringApplication.run(GitHubProjectApplication.class, args);
    }
}
