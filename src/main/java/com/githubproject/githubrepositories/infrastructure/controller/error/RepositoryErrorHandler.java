package com.githubproject.githubrepositories.infrastructure.controller.error;

import com.githubproject.githubrepositories.domain.model.RepositoryNotFoundException;
import com.githubproject.githubrepositories.infrastructure.controller.GithubProjectRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(assignableTypes = GithubProjectRestController.class)
@Log4j2
public class RepositoryErrorHandler {
    @ExceptionHandler(RepositoryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRepositoryResponseDto handleException(RepositoryNotFoundException exception) {
        log.warn("RepositoryNotFoundException while accesing song");
        return new ErrorRepositoryResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
