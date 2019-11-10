package com.mayh.blog.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//处理标注为Controller的异常
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //让这个方法可以处理异常，并且处理Exception级别的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        StringBuffer url = request.getRequestURL();
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        logger.error("Requset URL : {},eXCEPTION : {}", url, e);
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", url);
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
