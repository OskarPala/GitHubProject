package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class RepositoryDeleter {
    private final GitHubDbRepository gitHubDbRepository;
    private final RepositoryFromDbRetriever repositoryFromDbRetriever;

    public RepositoryDeleter(GitHubDbRepository gitHubDbRepository, RepositoryFromDbRetriever repositoryFromDbRetriever) {
        this.gitHubDbRepository = gitHubDbRepository;
        this.repositoryFromDbRetriever = repositoryFromDbRetriever;
    }

    public void deleteById(Long id){
        repositoryFromDbRetriever.existById(id);
        log.info("deleting song by Id: "+id);
        gitHubDbRepository.deleteById(id);
    }
}
