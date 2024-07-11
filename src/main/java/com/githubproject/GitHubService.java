package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<GitHubRepository> getAllFilteredRepository(String username) {
        List<GitHubRepositoriesDto> results = gitHubApiProxy.getRepos(username);
        List<GitHubRepositoriesDto> notForkRepositories = getNotForkRepositories(results);
        return mapFromServiceRepositoryDtoToGitHubRepository(notForkRepositories);
    }

    private List<GitHubRepository> mapFromServiceRepositoryDtoToGitHubRepository(List<GitHubRepositoriesDto> notForkRepositories) {
        return notForkRepositories.stream()
                .map(repository ->
                        new GitHubRepository(repository.name(), repository.owner().login()))
                .collect(Collectors.toList());
    }

    private List<GitHubRepositoriesDto> getNotForkRepositories(List<GitHubRepositoriesDto> results) {
        return results.stream()
                .filter(gitHubRepositoriesDto -> !gitHubRepositoriesDto.fork())
                .collect(Collectors.toList());
    }

    private List<Branch> getAllRepositoryBranches(String owner, String repoName) {
        List<BranchDto> result = gitHubApiProxy.getRepoDetails(owner, repoName);
        return MapFromBranchesDtoToBranches(result);
    }

    private List<Branch> MapFromBranchesDtoToBranches(List<BranchDto> result) {
        return result.stream()
                .map(r -> new Branch(r.name(), r.commit().sha()))
                .toList();
    }
}
