package com.githubproject.githubrepositories.infrastructure.controller.dto;

import java.util.List;

public record GetAllRepositoriesResponseDto (List<RepositoryResponseDto> repositories) {
}
