package com.githubproject.githubrepositories.infrastructure.controller;

import com.githubproject.githubrepositories.domain.model.AllInfoResult;
import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.service.GitHubFromDbRetriever;
import com.githubproject.githubrepositories.domain.service.GitHubRepositoryAdder;
import com.githubproject.githubrepositories.domain.service.GitHubRepositoryDeleter;
import com.githubproject.githubrepositories.domain.service.GitHubRetriever;
import com.githubproject.githubrepositories.infrastructure.controller.dto.request.CreateRepositoryRequestDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.CreateRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.DeleteRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.GetAllRepositoryResponseDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.db.GitHubRepositoryDto;
import com.githubproject.githubrepositories.infrastructure.controller.dto.response.proxy.GetAllRepositoriesResponseDto;
import com.githubproject.githubrepositories.validation.BadRequestException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.githubproject.githubrepositories.infrastructure.controller.GitHubRepositoryMapper.*;

@RestController
@Log4j2
@AllArgsConstructor
public class GithubProjectRestController {
    private final GitHubRetriever gitHubProxyService;
    private final GitHubFromDbRetriever gitHubFromDbRetriever;
    private final GitHubRepositoryDeleter gitHubRepositoryDeleter;
    private final GitHubRepositoryAdder gitHubRepositoryAdder;


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
    public ResponseEntity<GetAllRepositoryResponseDto> getAllRepositoriesFromDb(Pageable pageable) {
        List<GitHubRepository> allRepos = gitHubFromDbRetriever.findAll(pageable);
        GetAllRepositoryResponseDto allRepositoryDto = mapFromGitHubRepositoryToGetAllRepositoryResponseDto(allRepos);
        return ResponseEntity.ok(allRepositoryDto);
    }


    @GetMapping("/database/{id}")
    public ResponseEntity<GitHubRepositoryDto> getRepositoryFromDbById(@PathVariable Long id) {
        GitHubRepository repositoryById = gitHubFromDbRetriever.findById(id);
        GitHubRepositoryDto repositoryDto = mapFromGitHubRepositoryToGitHubRepositoryDto(repositoryById);
        return ResponseEntity.ok(repositoryDto);
    }


    @DeleteMapping("/database/{id}")
    public ResponseEntity<DeleteRepositoryResponseDto> deleteRepositoryFromDbByUsingId(@PathVariable Long id) {
        gitHubRepositoryDeleter.deleteById(id);
        DeleteRepositoryResponseDto body = mapFromGitHubRepositoryToDeleteRepositoryResponseDto(id);
        return ResponseEntity.ok(body);

    }

    @PostMapping("/database")
    public ResponseEntity<CreateRepositoryResponseDto> postRepository(@RequestBody @Valid CreateRepositoryRequestDto request) {
        GitHubRepository repository = mapFromCreateRepositoryRequestDtoToGitHubRepository(request);
        GitHubRepository savedRepository = gitHubRepositoryAdder.addRepository(repository);
        CreateRepositoryResponseDto body = mapFromGitHubRepositoryToCreateRepositoryResponseDto(savedRepository);
        return ResponseEntity.ok(body);
    }
}

