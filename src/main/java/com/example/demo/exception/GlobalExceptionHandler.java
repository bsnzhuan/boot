package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e){
        ModelAndView model = new ModelAndView();
        model.addObject("exception",e);
        model.addObject("url",request.getRequestURL());
        System.out.println("====================================");
        System.out.println("55555555555555555555gggg     error");
        System.out.println("====================================");
        model.setViewName("error");
        return model;
    }
}
