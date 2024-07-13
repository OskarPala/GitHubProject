package com.githubproject;

import java.util.List;
import java.util.stream.Collectors;

public class GitHubProxyMapper {
    public static List<Branch> mapFromBranchesDtoToBranches(List<BranchDto> result) {
        return result.stream()
                .map(r -> new Branch(r.name(), r.commit().sha()))
                .toList();
    }

    public static List<GitHubRepository> mapFromGitHubRepositoryDtoToGitHubRepository(List<GitHubRepositoryDto> notForkRepositories) {
        return notForkRepositories.stream()
                .map(repository ->
                        new GitHubRepository(repository.name(), repository.owner().login()))
                .collect(Collectors.toList());
    }
}
