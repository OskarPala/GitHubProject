package com.githubproject.githubrepositories.domain.repository;

import com.githubproject.githubrepositories.domain.model.GitHubRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GitHubDbRepository extends Repository <GitHubRepository,Long> {
}
