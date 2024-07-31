package com.githubproject.githubrepositories.domain.repository;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GitHubDbRepository extends Repository<GitHubRepository, Long> {
    @Query("SELECT r FROM GitHubRepository r")
    List<GitHubRepository> findAll(Pageable pageable);

    Optional<GitHubRepository> findById(Long id);

    @Modifying
    @Query("DELETE from GitHubRepository r WHERE r.id = :id")
    void deleteById(Long id);

    GitHubRepository save(GitHubRepository repository);

    boolean existsById(Long id);
}
