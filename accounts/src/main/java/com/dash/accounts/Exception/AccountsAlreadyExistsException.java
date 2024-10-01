package com.dash.accounts.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountsAlreadyExistsException extends RuntimeException{
    public AccountsAlreadyExistsException(String message){
        super(message);
    }
}
