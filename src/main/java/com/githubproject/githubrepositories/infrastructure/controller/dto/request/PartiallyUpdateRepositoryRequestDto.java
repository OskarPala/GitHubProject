package com.githubproject.githubrepositories.infrastructure.controller.dto.request;

public record PartiallyUpdateRepositoryRequestDto(
        String owner,
        String name
) {
}
