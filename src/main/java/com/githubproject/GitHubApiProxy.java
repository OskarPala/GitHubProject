package com.githubproject;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-api-client", url = "https://api.github.com/")
public interface GitHubApiProxy {

    // GET https://api.github.com/users/USERNAME/repos
    @GetMapping("users/{userName}/repos")
    List<RepositoryDto> getRepos(@PathVariable("userName") String userName);


// GET  https://api.github.com/repos/OWNER/REPO/branches
    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getRepoDetails(@PathVariable("owner") String owner, @PathVariable("repo") String repoName);

}
