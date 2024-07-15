package com.githubproject.githubrepositories.validation;

import com.githubproject.githubrepositories.infrastructure.controller.GithubProjectRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = GithubProjectRestController.class)
@Log4j2
class BadRequestFormatErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public BadRequestFormatErrorResponseDto handleException(BadRequestException exception) {
        log.warn(exception.getMessage());
        return new BadRequestFormatErrorResponseDto(exception.getStatus().value(), exception.getMessage());
    }
}
