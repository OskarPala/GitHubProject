package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import com.githubproject.githubrepositories.domain.model.Branch;
import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.proxy.dto.BranchDto;
import com.githubproject.githubrepositories.domain.proxy.dto.GitHubRepositoryDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class GitHubRetriever {
    private final GitHubProxyService gitHubServiceProxy;

    public GitHubRetriever(GitHubProxyService gitHubServiceProxy) {
        this.gitHubServiceProxy = gitHubServiceProxy;
    }

    public List<AllInfoResult> getAllInfoResults(String userName) {
        List<GitHubRepository> results = getMappedRepositories(userName);
        return createAllInfoResult(results);
    }

    private List<AllInfoResult> createAllInfoResult(List<GitHubRepository> results) {
        return results.stream()
                .map(repository -> new AllInfoResult(
                        repository.repositoryName(),
                        repository.owner(),
                        getMappedBranches(repository)))
                .collect(Collectors.toList());
    }

    private List<GitHubRepository> getMappedRepositories(String userName) {
        List<GitHubRepositoryDto> filteredRepository = getFilteredRepositories(userName);
        return GitHubProxyMapper.mapFromGitHubRepositoryDtoToGitHubRepository(filteredRepository);
    }

    private List<GitHubRepositoryDto> getAllRepositories(String username) {
        log.info("Fetching all " + username + " repositories");
        return gitHubServiceProxy.makeGetRepositoryRequest(username);
    }

    private List<Branch> getMappedBranches(GitHubRepository repository) {
        List<BranchDto> allRepositoryBranches = getAllBranchesDto(repository);
        return GitHubProxyMapper.mapFromBranchesDtoToBranches(allRepositoryBranches);
    }

    private List<BranchDto> getAllBranchesDto(GitHubRepository repository) {
        return gitHubServiceProxy.makeGetBranchesRequest(
                repository.owner(),
                repository.repositoryName());
    }

    private List<GitHubRepositoryDto> getFilteredRepositories(String username) {
        return getAllRepositories(username).stream()
                .filter(gitHubRepositoryDto -> !gitHubRepositoryDto.fork())
                .collect(Collectors.toList()
                );
    }
}
