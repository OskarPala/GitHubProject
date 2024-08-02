package com.githubproject.githubrepositories.infrastructure.controller.dto.response.db;

import java.util.List;

public record GetAllRepositoryResponseDto(List<GitHubRepositoryDto> repositories) {
}
