
package com.mpas.cems;

import com.mpas.cems.person.BetterPersonsController;
import com.mpas.cems.person.PersonsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//@ControllerAdvice(basePackages = "com.mpas.cems.person")
@ControllerAdvice(basePackageClasses = BetterPersonsController.class)
public class RestExceptionsHandler {

    /*@ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(PersonsException pe) {
            return new ResponseEntity<>(pe.errorMessage(), pe.getStatus());
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Unexpected Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

   /* @ExceptionHandler
    @ResponseBody
    public ResponseEntity<JsonError> handleException(HttpServletRequest req, Exception e) {
        String errorURL = req.getRequestURL().toString();
        if(e instanceof PersonsException) {
            PersonsException pe = (PersonsException) e;
            return new ResponseEntity<>(new JsonError(errorURL, pe.getMessage()), pe.getStatus());
        }
        return new ResponseEntity<>(new JsonError(errorURL,"Unexpected Exception: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        if(e instanceof PersonsException) {
            PersonsException pe = (PersonsException) e;
            return new ResponseEntity<>(pe.errorMessage(), pe.getStatus());
        }
        return new ResponseEntity<>("Unexpected Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
