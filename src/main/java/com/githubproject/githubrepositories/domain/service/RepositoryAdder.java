package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Transactional
public class RepositoryAdder {
    private final GitHubDbRepository gitHubDbRepository;

    public RepositoryAdder(GitHubDbRepository gitHubDbRepository) {
        this.gitHubDbRepository = gitHubDbRepository;
    }

    public GitHubRepository addRepository(GitHubRepository repository) {
        log.info("Adding new repository: " + repository);
        return gitHubDbRepository.save(repository);
    }

    public void addAllRepositoriesToDatabase(List<GitHubRepository> repositories) {
        log.info("Adding all fetched repositories");
        repositories.forEach(this::addRepository);
    }
}
