package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Transactional
public class GitHubRepositoryAdder {
    public GitHubDbRepository gitHubDbRepository;

    public GitHubRepositoryAdder(GitHubDbRepository gitHubDbRepository) {
        this.gitHubDbRepository = gitHubDbRepository;
    }

    public GitHubRepository addRepository(GitHubRepository repository){
        log.info("Adding new repository: "+ repository);
        return gitHubDbRepository.save(repository);
    }
}
