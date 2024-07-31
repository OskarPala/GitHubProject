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

    public GitHubRepository(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public GitHubRepository(Long id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
}
