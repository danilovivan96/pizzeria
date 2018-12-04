package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mail.danilov.pizzeria.controllers.properties.PageProperties;
import ru.mail.danilov.pizzeria.service.exceptions.ItemNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {
    private final PageProperties properties;

    @Autowired
    public AppExceptionHandler(PageProperties properties) {
        this.properties = properties;
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
        request.setAttribute("exception", e);
        request.setAttribute("url", request.getRequestURL());
        return properties.getErrorPagePath();
    }
}
