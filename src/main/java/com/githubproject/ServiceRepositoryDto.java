package com.githubproject;

public record ServiceRepositoryDto(String name, OwnerDto owner, boolean fork) {
}
