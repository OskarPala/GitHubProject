package com.githubproject.githubrepositories.infrastructure.controller;

import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.service.GitHubFromDbRetriever;
import com.githubproject.githubrepositories.domain.service.GitHubRepositoryDeleter;
import com.githubproject.githubrepositories.domain.service.GitHubRetriever;
import com.githubproject.githubrepositories.infrastructure.controller.dto.GetAllRepositoriesResponseDto;
import com.githubproject.githubrepositories.validation.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.githubproject.githubrepositories.infrastructure.controller.GitHubRepositoryMapper.mapFromAllInfoResulToGetAllRepositoryResponseDto;

@RestController
@Log4j2
@AllArgsConstructor
public class GithubProjectRestController {
    private final GitHubRetriever gitHubProxyService;
    private final GitHubFromDbRetriever gitHubFromDbRetriever;
    private final GitHubRepositoryDeleter gitHubRepositoryDeleter;


    @GetMapping("/{userName}")
    public ResponseEntity<GetAllRepositoriesResponseDto> getAllRepositories(
            @PathVariable String userName,
            @RequestHeader(required = false) String accept
    ) {
        if (MediaType.APPLICATION_XML_VALUE.equals(accept)) {
            throw new BadRequestException(HttpStatus.NOT_ACCEPTABLE, "XML not supported");
        }
        List<AllInfoResult> results = gitHubProxyService.getAllInfoResults(userName);
        GetAllRepositoriesResponseDto response = mapFromAllInfoResulToGetAllRepositoryResponseDto(results);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/database")
    public ResponseEntity<List<GitHubRepository>> getAllRepositoriesFromDb(Pageable pageable) {
        List<GitHubRepository> allRepos = gitHubFromDbRetriever.findAll(pageable);
        return ResponseEntity.ok(allRepos);
    }

    @GetMapping("/database/{id}")
    public ResponseEntity<GitHubRepository> getRepositoryFromDbById(@PathVariable Long id) {
        GitHubRepository repositoryById = gitHubFromDbRetriever.findById(id);
        return ResponseEntity.ok(repositoryById);
    }

    @DeleteMapping("/database/{id}")
    public ResponseEntity<HttpStatus> deleteRepositoryFromDbByUsingId(@PathVariable Long id) {
        gitHubRepositoryDeleter.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}

