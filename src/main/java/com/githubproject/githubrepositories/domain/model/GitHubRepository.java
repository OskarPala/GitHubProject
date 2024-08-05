package com.githubproject.githubrepositories.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name = "repo")
public class GitHubRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String owner;

    @Column(nullable = false)
    String name;

    public GitHubRepository() {
    }

    public GitHubRepository(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public GitHubRepository(Long id, String owner, String name) {
        this.id = id;
        this.owner = owner;
        this.name = name;
    }
}
