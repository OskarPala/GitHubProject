package com.githubproject.apivalidation;

import com.githubproject.githubrepositories.infrastructure.controller.GithubProjectRestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = GithubProjectRestController.class)
public class ApiValidationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationResponseDto handleValidationException(MethodArgumentNotValidException exception){
        List<String> messages = getErrorsFromException(exception);
        return new ApiValidationResponseDto(messages,HttpStatus.BAD_REQUEST);
    }
    private List<String> getErrorsFromException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
