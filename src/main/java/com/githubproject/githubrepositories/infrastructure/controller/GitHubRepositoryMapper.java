package com.githubproject.githubrepositories.infrastructure.controller;

import com.githubproject.githubrepositories.infrastructure.controller.dto.BranchResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.GetAllRepositoriesResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.RepositoryResponseDto;
import com.githubproject.githubrepositories.domain.model.AllInfoResult;

import java.util.List;
import java.util.stream.Collectors;

public class GitHubRepositoryMapper {
    public static GetAllRepositoriesResponseDto mapFromAllInfoResulToGetAllRepositoryResponseDto(List<AllInfoResult> allInfoRepositoryList) {
        List<RepositoryResponseDto> response = mapFromAllInfoResultToRepositoryResponseDto(allInfoRepositoryList);
        return new GetAllRepositoriesResponseDto(response);
    }
    private static List<RepositoryResponseDto> mapFromAllInfoResultToRepositoryResponseDto(List<AllInfoResult> allInfoRepositoryList) {
        return allInfoRepositoryList.stream()
                .map(repository -> new RepositoryResponseDto(
                        repository.repositoryName(),
                        repository.owner(),
                        mapBranchToBranchResponseDto(repository)))
                .collect(Collectors.toList());
    }
    private static List<BranchResponseDto> mapBranchToBranchResponseDto(AllInfoResult repository) {
        return repository.branch()
                .stream()
                .map(branch -> new BranchResponseDto(branch.name(), branch.sha()))
                .collect(Collectors.toList());
    }
}
