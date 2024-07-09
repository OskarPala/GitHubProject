package com.githubproject;

import feign.FeignException;
import feign.RetryableException;
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

    public List<AllInfoResult> makeRequestToGithubEndpoint(String userName) {
        List<AllInfoResult> resultList = new ArrayList<>();


        try {

            return getAllInfoResults(userName);


        } catch (FeignException.FeignClientException feignException) {
            log.error("client exception: {}", feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
        } catch (RetryableException retryableException) {
            System.out.println("retryable exception: " + retryableException.status());
        } catch (FeignException feignException) {
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        }
        return resultList;
    }

    private List<AllInfoResult> getAllInfoResults(String userName) {
        List<AllInfoResult> resultList1 = new ArrayList<>();
        List<RepositoryDto> filteredResult = getAllFilteredRepository(userName);


        for (RepositoryDto repositoryDto : filteredResult) {

            String owner = repositoryDto.owner().login();
            String repositoryName = repositoryDto.name();

            List<Branch> branch = getBranchDtos(owner, repositoryDto.name());
            AllInfoResult allInfoResult = new AllInfoResult(repositoryName, owner, branch);
            resultList1.add(allInfoResult);




        }
        return resultList1;
    }

    private List<RepositoryDto> getAllFilteredRepository(String username) {
        List<RepositoryDto> results = gitHubApiProxy.getRepos(username);
        return results.stream()
                .filter(repositoryDto -> !repositoryDto.fork())
                .collect(Collectors.toList());
    }

    private List<Branch> getBranchDtos(String owner, String repoName) {
        List<BranchDto> result = gitHubApiProxy.getRepoDetails(owner, repoName);
        return MapFromBranchesDtoToBranches(result);
    }

    private  List<Branch> MapFromBranchesDtoToBranches(List<BranchDto> result) {
        return result.stream()
                .map(r -> new Branch(r.name(), r.commit().sha()))
                .toList();
    }


}
