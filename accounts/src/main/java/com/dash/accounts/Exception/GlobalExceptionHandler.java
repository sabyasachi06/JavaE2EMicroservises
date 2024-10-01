package com.dash.accounts.Exception;

import com.dash.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountsAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountsAlreadyExistsException(AccountsAlreadyExistsException exception, WebRequest request) {
        ErrorResponseDto responseDto = new ErrorResponseDto(request.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
