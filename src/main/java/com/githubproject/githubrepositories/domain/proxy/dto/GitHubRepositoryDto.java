package com.githubproject.githubrepositories.domain.proxy.dto;

public record GitHubRepositoryDto(String name, OwnerDto owner, boolean fork) {
}
