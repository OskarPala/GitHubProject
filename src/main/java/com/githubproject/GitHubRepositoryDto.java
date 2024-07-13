package com.githubproject;

public record GitHubRepositoryDto(String name, OwnerDto owner, boolean fork) {
}
