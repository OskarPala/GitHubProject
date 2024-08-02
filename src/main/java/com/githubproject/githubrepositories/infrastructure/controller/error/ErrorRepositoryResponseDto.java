package com.githubproject.githubrepositories.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorRepositoryResponseDto(String message, HttpStatus httpStatus) {
}
