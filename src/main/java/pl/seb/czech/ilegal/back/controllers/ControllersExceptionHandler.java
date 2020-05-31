package pl.seb.czech.ilegal.back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.seb.czech.ilegal.back.services.ElementNotFound;

@Slf4j
@ControllerAdvice
public class ControllersExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ElementNotFound.class)
    public void handleNotFound(ElementNotFound e) {
        log.error(e.getMessage(), e);
    }
}
