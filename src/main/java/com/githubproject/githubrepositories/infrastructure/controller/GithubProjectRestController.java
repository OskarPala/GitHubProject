package com.githubproject.githubrepositories.infrastructure.controller;

import com.githubproject.githubrepositories.validation.BadRequestException;
import com.githubproject.githubrepositories.domain.service.GitHubRetriever;
import com.githubproject.githubrepositories.infrastructure.controller.dto.GetAllRepositoriesResponseDto;
import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.githubproject.githubrepositories.infrastructure.controller.GitHubRepositoryMapper.mapFromAllInfoResulToGetAllRepositoryResponseDto;

@RestController
@Log4j2
public class GithubProjectRestController {
    private final GitHubRetriever gitHubProxyService;

    public GithubProjectRestController(GitHubRetriever gitHubProxyService) {
        this.gitHubProxyService = gitHubProxyService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<GetAllRepositoriesResponseDto> getAllRepositories(
            @PathVariable String userName,
            @RequestHeader(required = false) String accept
    ) {
        if (MediaType.APPLICATION_XML_VALUE.equals(accept)) {
            throw new BadRequestException(HttpStatus.NOT_ACCEPTABLE,"XML not supported");
        }
        List<AllInfoResult> results = gitHubProxyService.getAllInfoResults(userName);
        GetAllRepositoriesResponseDto response = mapFromAllInfoResulToGetAllRepositoryResponseDto(results);
        return ResponseEntity.ok(response);
    }
}

