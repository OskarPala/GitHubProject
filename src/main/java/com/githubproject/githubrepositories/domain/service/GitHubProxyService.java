package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.proxy.GitHubApiProxy;
import com.githubproject.githubrepositories.domain.proxy.dto.BranchDto;
import com.githubproject.githubrepositories.domain.proxy.dto.GitHubRepositoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubProxyService {

    private final GitHubApiProxy gitHubApiProxy;

    public GitHubProxyService(GitHubApiProxy gitHubApiProxy) {
        this.gitHubApiProxy = gitHubApiProxy;
    }


    List<GitHubRepositoryDto> makeGetRepositoryRequest(String username) {
        return gitHubApiProxy.getAllRepositories(username);
    }

    List<BranchDto> makeGetBranchesRequest(String owner, String repoName) {
        return gitHubApiProxy.getAllBranches(owner, repoName);
    }
}
