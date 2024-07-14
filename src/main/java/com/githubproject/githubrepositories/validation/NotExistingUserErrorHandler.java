package com.githubproject.githubrepositories.validation;

import com.githubproject.githubrepositories.infrastructure.controller.GithubProjectRestController;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubProjectRestController.class)
@Log4j2
public class NotExistingUserErrorHandler {
    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundErrorResponseDto handleException(FeignException.NotFound exception) {
        log.warn(exception.getMessage() + "User not found");
        return new NotFoundErrorResponseDto(exception.status(), "Not existing github User");
    }
}
