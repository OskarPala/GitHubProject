package com.githubproject;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class GitHubService {
    @Autowired
    GitHubApiProxy gitHubApiProxy;



    public List<AllInfoResult> getAllInfoResults(String userName) {
        List<AllInfoResult> resultList1 = new ArrayList<>();
        List<ServiceRepositoryDto> filteredResult = getAllFilteredRepository(userName);


        for (ServiceRepositoryDto serviceRepositoryDto : filteredResult) {

            String owner = serviceRepositoryDto.owner().login();
            String repositoryName = serviceRepositoryDto.name();

            List<Branch> branch = getBranchDtos(owner, serviceRepositoryDto.name());
            AllInfoResult allInfoResult = new AllInfoResult(repositoryName, owner, branch);
            resultList1.add(allInfoResult);


        }
        return resultList1;
    }

    private List<ServiceRepositoryDto> getAllFilteredRepository(String username) {
        List<ServiceRepositoryDto> results = gitHubApiProxy.getRepos(username);
        return results.stream()
                .filter(serviceRepositoryDto -> !serviceRepositoryDto.fork())
                .collect(Collectors.toList());
    }

    private List<Branch> getBranchDtos(String owner, String repoName) {
        List<BranchDto> result = gitHubApiProxy.getRepoDetails(owner, repoName);
        return MapFromBranchesDtoToBranches(result);
    }

    private List<Branch> MapFromBranchesDtoToBranches(List<BranchDto> result) {
        return result.stream()
                .map(r -> new Branch(r.name(), r.commit().sha()))
                .toList();
    }


}
