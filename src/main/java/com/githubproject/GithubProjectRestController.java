package com.githubproject;

import com.githubproject.dto.response.GetAllRepositoriesResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.githubproject.GitHubRepositoryMapper.mapFromAllInfoResulToGetAllRepositoryResponseDto;

@RestController("/")
@Log4j2
public class GithubProjectRestController {
    GitHubService gitHubService;

    public GithubProjectRestController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/{userName}")
    public ResponseEntity<GetAllRepositoriesResponseDto> getAllRepositories(@PathVariable String userName) {

        List<AllInfoResult> results = gitHubService.getAllInfoResults(userName);
        GetAllRepositoriesResponseDto response = mapFromAllInfoResulToGetAllRepositoryResponseDto(results);
        return ResponseEntity.ok(response);
    }
}
