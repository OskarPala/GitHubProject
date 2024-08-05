package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import com.githubproject.githubrepositories.domain.model.Branch;
import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.proxy.dto.BranchDto;
import com.githubproject.githubrepositories.domain.proxy.dto.GitHubRepositoryDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class GitRepositoryRetriever {
    private final ProxyService gitHubServiceProxy;
    private final RepositoryAdder repositoryAdder;


    public List<AllInfoResult> getAllInfoResultsAndSaveToDb(String userName) {
        List<GitHubRepository> results = getMappedRepositories(userName);
        repositoryAdder.addAllRepositoriesToDatabase(results);
        return createAllInfoResult(results);
    }

    private List<AllInfoResult> createAllInfoResult(List<GitHubRepository> results) {
        return results.stream()
                .map(repository -> new AllInfoResult(
                        repository.getName(),
                        repository.getOwner(),
                        getMappedBranches(repository)))
                .collect(Collectors.toList());
    }

    private List<GitHubRepository> getMappedRepositories(String userName) {
        List<GitHubRepositoryDto> filteredRepository = getFilteredRepositories(userName);
        return ProxyMapper.mapFromGitHubRepositoryDtoToGitHubRepository(filteredRepository);
    }

    private List<GitHubRepositoryDto> getAllRepositories(String username) {
        log.info("Fetching all " + username + " repositories");
        return gitHubServiceProxy.makeGetRepositoryRequest(username);
    }

    private List<Branch> getMappedBranches(GitHubRepository repository) {
        List<BranchDto> allRepositoryBranches = getAllBranchesDto(repository);
        return ProxyMapper.mapFromBranchesDtoToBranches(allRepositoryBranches);
    }

    private List<BranchDto> getAllBranchesDto(GitHubRepository repository) {
        return gitHubServiceProxy.makeGetBranchesRequest(
                repository.getOwner(),
                repository.getName());
    }

    private List<GitHubRepositoryDto> getFilteredRepositories(String username) {
        return getAllRepositories(username).stream()
                .filter(gitHubRepositoryDto -> !gitHubRepositoryDto.fork())
                .collect(Collectors.toList()
                );
    }
}
