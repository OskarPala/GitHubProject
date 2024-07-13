package com.githubproject.dto.response;

import com.githubproject.Branch;

import java.util.List;

public record RepositoryResponseDto(String repositoryName, String ownerLogin, List<BranchResponseDto> branches) {
}
