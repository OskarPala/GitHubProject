package com.githubproject.githubrepositories.infrastructure.controller.dto;

import java.util.List;

public record RepositoryResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branches) {
}
