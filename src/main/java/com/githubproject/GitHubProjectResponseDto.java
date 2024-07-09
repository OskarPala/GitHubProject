package com.githubproject;

import java.util.List;

public record GitHubProjectResponseDto(String repositoryName, String OwnerLogin, List<BranchResponseDto> branches) {
}
