
package com.mpas.cems.sec.problem;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MissingExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof NoHandlerFoundException) {
            ModelAndView model = new ModelAndView("error");
            model.addObject("problem","URL not supported : " + request.getRequestURI());
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return model;
        }
        return null;
    }
}
