
package com.mpas.cems.sec.problem;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class MissingRecordsHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", "Not Supported " + req.getRequestURI());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView accessDenied(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("problem", "Forbidden access to: " + req.getRequestURI());
        mav.setViewName("error");
        return mav;
    }
}
