package com.mytest.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:GlobalExceptionHandler
 * Package: com.mytest.handler
 * Description:
 *
 * @Date: 2021/5/15 15:39
 * @Author: 惜名
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView doException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ex",ex.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
