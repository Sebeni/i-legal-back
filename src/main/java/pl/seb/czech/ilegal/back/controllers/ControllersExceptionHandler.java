package pl.seb.czech.ilegal.back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.seb.czech.ilegal.back.domain.ElementNotFoundLog;
import pl.seb.czech.ilegal.back.repositories.ElementNotFoundLogRepository;
import pl.seb.czech.ilegal.back.services.ElementNotFound;

@Slf4j
@ControllerAdvice
public class ControllersExceptionHandler {
    @Autowired
    private ElementNotFoundLogRepository repository;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ElementNotFound.class)
    public void handleNotFound(ElementNotFound e) {
        log.error(e.getMessage(), e);
        repository.save(new ElementNotFoundLog(e.getMessage()));
    }
}
