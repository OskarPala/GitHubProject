package com.githubproject.githubrepositories.infrastructure.controller.dto.response.db;

import org.springframework.http.HttpStatus;

public record DeleteRepositoryResponseDto(String message, HttpStatus status){
}
