package com.githubproject;

public record RepositoryDto (String name, OwnerDto owner, boolean fork) {
}
