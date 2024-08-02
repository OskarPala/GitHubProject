package com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy;

import java.util.List;

public record RepositoryResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branches) {
}
