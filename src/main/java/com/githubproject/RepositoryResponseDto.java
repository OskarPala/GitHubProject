package com.githubproject;

import java.util.List;

public record RepositoryResponseDto(String repositoryName, String OwnerLogin, List<Branch> branch) {
}
