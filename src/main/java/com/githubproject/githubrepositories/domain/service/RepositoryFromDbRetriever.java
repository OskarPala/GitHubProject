package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.model.RepositoryNotFoundException;
import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RepositoryFromDbRetriever {
    private final GitHubDbRepository githubDbRepository;

    public RepositoryFromDbRetriever(GitHubDbRepository githubDbRepository) {
        this.githubDbRepository = githubDbRepository;
    }

    public List<GitHubRepository> findAll(Pageable pageable) {
        log.info("Find all repositories from database");
        return githubDbRepository.findAll(pageable);
    }

    public GitHubRepository findById(Long id) {
        return githubDbRepository.findById(id)
                .orElseThrow(() -> new RepositoryNotFoundException("Song with id: " + id + " not found"));
    }

    public void existById(Long id) {
        if (!githubDbRepository.existsById(id)) {
            throw new RepositoryNotFoundException("Song with id: " + id + " not found");
        }
    }
}
