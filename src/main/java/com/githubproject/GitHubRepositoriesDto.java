package com.githubproject;

public record GitHubRepositoriesDto(String name, OwnerDto owner, boolean fork) {
}
