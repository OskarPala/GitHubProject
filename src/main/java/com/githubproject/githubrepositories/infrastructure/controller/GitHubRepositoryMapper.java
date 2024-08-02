package com.githubproject.githubrepositories.infrastructure.controller;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.infrastructure.controller.dto.request.CreateRepositoryRequestDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.CreateRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.DeleteRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.GetAllRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.GitHubRepositoryDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy.BranchResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy.GetAllRepositoriesResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy.RepositoryResponseDto;
import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

class GitHubRepositoryMapper {
    public static GetAllRepositoriesResponseDto mapFromAllInfoResulToGetAllRepositoryResponseDto(List<AllInfoResult> allInfoRepositoryList) {
        List<RepositoryResponseDto> response = mapFromAllInfoResultToRepositoryResponseDto(allInfoRepositoryList);
        return new GetAllRepositoriesResponseDto(response);
    }


    public static GitHubRepositoryDto mapFromGitHubRepositoryToGitHubRepositoryDto(GitHubRepository repository) {
        return new GitHubRepositoryDto(repository.getId(), repository.getOwner(), repository.getName());
    }

    public static GetAllRepositoryResponseDto mapFromGitHubRepositoryToGetAllRepositoryResponseDto(List<GitHubRepository> allRepos) {
        List<GitHubRepositoryDto> repositoryDtos = allRepos.stream()
                .map(GitHubRepositoryMapper::mapFromGitHubRepositoryToGitHubRepositoryDto)
                .toList();
        return new GetAllRepositoryResponseDto(repositoryDtos);
    }

    public static DeleteRepositoryResponseDto mapFromGitHubRepositoryToDeleteRepositoryResponseDto(Long id) {
        return new DeleteRepositoryResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static GitHubRepository mapFromCreateRepositoryRequestDtoToGitHubRepository(CreateRepositoryRequestDto request) {
        return new GitHubRepository(request.name(), request.owner());
    }

    public static CreateRepositoryResponseDto mapFromGitHubRepositoryToCreateRepositoryResponseDto(GitHubRepository savedRepository) {
        GitHubRepositoryDto repositoryDto = mapFromGitHubRepositoryToGitHubRepositoryDto(savedRepository);
        return new CreateRepositoryResponseDto(repositoryDto);
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
