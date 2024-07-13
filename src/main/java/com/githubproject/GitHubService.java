package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.githubproject.GitHubProxyMapper.mapFromBranchesDtoToBranches;
import static com.githubproject.GitHubProxyMapper.mapFromGitHubRepositoryDtoToGitHubRepository;

@Log4j2
@Service
public class GitHubService {
    @Autowired
    GitHubApiProxy gitHubApiProxy;


    public List<AllInfoResult> getAllInfoResults(String userName) {
        List<GitHubRepository> filteredRepositories = getAllFilteredRepository(userName);
        return filteredRepositories.stream()
                .map(repository -> new AllInfoResult(
                        repository.repositoryName(),
                        repository.owner(),
                        getAllRepositoryBranches(repository.owner(), repository.repositoryName())))
                .collect(Collectors.toList());
    }

    private List<GitHubRepositoryDto> getNotForkRepositories(List<GitHubRepositoryDto> results) {
        return results.stream()
                .filter(gitHubRepositoryDto -> !gitHubRepositoryDto.fork())
                .collect(Collectors.toList());
    }

    private List<Branch> getAllRepositoryBranches(String owner, String repoName) {
        List<BranchDto> result = gitHubApiProxy.getRepoDetails(owner, repoName);
        return mapFromBranchesDtoToBranches(result);
    }

    private List<GitHubRepository> getAllFilteredRepository(String username) {
        List<GitHubRepositoryDto> results = gitHubApiProxy.getRepos(username);
        List<GitHubRepositoryDto> notForkRepositories = getNotForkRepositories(results);
        return mapFromGitHubRepositoryDtoToGitHubRepository(notForkRepositories);
    }
}
