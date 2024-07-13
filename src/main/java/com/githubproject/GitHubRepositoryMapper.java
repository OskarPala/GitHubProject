package com.githubproject;

import com.githubproject.dto.response.BranchResponseDto;
import com.githubproject.dto.response.GetAllRepositoriesResponseDto;
import com.githubproject.dto.response.RepositoryResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class GitHubRepositoryMapper {
    public static GetAllRepositoriesResponseDto mapFromAllInfoResulToGetAllRepositoryResponseDto(List<AllInfoResult> allInfoRepositoryList) {
        List<RepositoryResponseDto> response = mapFromAllInfoResultToResponseDto(allInfoRepositoryList);
        return new GetAllRepositoriesResponseDto(response);
    }
    private static List<RepositoryResponseDto> mapFromAllInfoResultToResponseDto(List<AllInfoResult> allInfoRepositoryList) {
        return allInfoRepositoryList.stream()
                .map(repository -> new RepositoryResponseDto(
                        repository.repositoryName(),
                        repository.owner(),
                        mapBranchesToBranchResponseDto(repository)))
                .collect(Collectors.toList());
    }
    private static List<BranchResponseDto> mapBranchesToBranchResponseDto(AllInfoResult repository) {
        return repository.branch()
                .stream()
                .map(branch -> new BranchResponseDto(branch.name(), branch.sha()))
                .collect(Collectors.toList());
    }

}
