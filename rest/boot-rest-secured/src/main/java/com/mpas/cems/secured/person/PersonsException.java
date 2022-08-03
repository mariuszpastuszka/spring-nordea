
package com.mpas.cems.secured.person;

import org.springframework.http.HttpStatus;


public class PersonsException extends RuntimeException{
    private HttpStatus status;

    public PersonsException(String message) {
        super(message);
    }

    public PersonsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public PersonsException(HttpStatus status,Throwable cause) {
        super(cause);
        this.status = status;
    }

    public PersonsException(String message, Throwable cause) {
        super(message, cause);
    }

    public String errorMessage(){
        return status.value() + ":".concat(getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }
}
