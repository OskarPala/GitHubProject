package com.githubproject.githubrepositories.domain.model;

import java.util.List;

public record AllInfoResult(String repositoryName, String owner, List<Branch> branch) {
}
