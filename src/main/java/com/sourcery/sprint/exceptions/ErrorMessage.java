package com.sourcery.sprint.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Component
@Data
public class ErrorMessage {

    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private String message;
    private WebRequest path;
}
