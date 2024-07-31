package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class GitHubRepositoryDeleter {
    private final GitHubDbRepository gitHubDbRepository;
    private final GitHubFromDbRetriever gitHubFromDbRetriever;

    public GitHubRepositoryDeleter(GitHubDbRepository gitHubDbRepository, GitHubFromDbRetriever gitHubFromDbRetriever) {
        this.gitHubDbRepository = gitHubDbRepository;
        this.gitHubFromDbRetriever = gitHubFromDbRetriever;
    }

    public void deleteById(Long id){
        gitHubFromDbRetriever.existById(id);
        log.info("deleting song by Id: "+id);
        gitHubDbRepository.deleteById(id);
    }
}
