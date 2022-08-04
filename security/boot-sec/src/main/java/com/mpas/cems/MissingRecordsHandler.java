
package com.mpas.cems;

import com.mpas.cems.ex.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class MissingRecordsHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView notFound(HttpServletRequest req, NotFoundException nfe) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", nfe.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
