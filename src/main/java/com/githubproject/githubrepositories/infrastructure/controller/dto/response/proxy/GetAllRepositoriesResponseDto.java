package com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy;

import java.util.List;

public record GetAllRepositoriesResponseDto (List<RepositoryResponseDto> repositories) {
}
