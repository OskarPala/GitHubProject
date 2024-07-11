package com.githubproject;

import java.util.List;

public record GetAllRepositoriesResponseDto (List<RepositoryResponseDto> repositories) {
}
