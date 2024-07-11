package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@Log4j2
public class GithubProjectRestController {
    GitHubService gitHubService;

    public GithubProjectRestController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/{userName}")
    public ResponseEntity<testREsponse> getAllRepositories(@PathVariable String userName) {

        List<AllInfoResult> allInfoRepositoryList = gitHubService.getAllInfoResults(userName);
        testREsponse testREsponse = new testREsponse(allInfoRepositoryList);
        return ResponseEntity.ok(testREsponse);
    }


}
