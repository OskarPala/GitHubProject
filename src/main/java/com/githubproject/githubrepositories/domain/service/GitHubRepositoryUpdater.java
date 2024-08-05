package com.githubproject.githubrepositories.domain.service;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import com.githubproject.githubrepositories.domain.repository.GitHubDbRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class GitHubRepositoryUpdater {
    private final GitHubDbRepository gitHubDbRepository;
    private final GitHubFromDbRetriever gitHubFromDbRetriever;

    public void updateById(Long id, GitHubRepository newRepository) {
        gitHubFromDbRetriever.existById(id);
        gitHubDbRepository.updateById(id, newRepository);
    }

    public GitHubRepository updatePartiallyById(Long id, GitHubRepository repositoryFromRequest) {
        GitHubRepository repositoryFromDatabase = gitHubFromDbRetriever.findById(id);
        GitHubRepository.GitHubRepositoryBuilder builder = GitHubRepository.builder();
        builder.id(repositoryFromDatabase.getId());
        if (repositoryFromRequest.getOwner() != null) {
            builder.owner(repositoryFromRequest.getOwner());
        } else {
            builder.owner(repositoryFromDatabase.getOwner());
        }
        if (repositoryFromRequest.getName() != null) {
            builder.name(repositoryFromRequest.getName());
        } else {
            builder.name(repositoryFromDatabase.getName());
        }

        GitHubRepository toSave = builder.build();
        updateById(id, toSave);
        return toSave;

    }
}


