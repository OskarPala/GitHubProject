package com.githubproject.dto.response;

import java.util.List;

public record GetAllRepositoriesResponseDto (List<RepositoryResponseDto> repositories) {
}
