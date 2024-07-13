package com.githubproject.dto.response;

import java.util.List;

public record RepositoryResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branches) {
}
