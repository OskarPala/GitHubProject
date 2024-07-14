package com.githubproject.githubrepositories.domain.proxy;

import com.githubproject.githubrepositories.domain.proxy.dto.BranchDto;
import com.githubproject.githubrepositories.domain.proxy.dto.GitHubRepositoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "github-api-client", url = "https://api.github.com/")
public interface GitHubApiProxy {
    @GetMapping("users/{userName}/repos")
    List<GitHubRepositoryDto> getAllRepositories(@PathVariable("userName") String userName);

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<BranchDto> getAllBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repoName);
}
