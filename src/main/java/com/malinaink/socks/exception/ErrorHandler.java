package com.malinaink.socks.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handInSufficientSockQuantityException(Exception e) {
        log.error(e.getMessage(), e);
        System.out.println("Выход за лимит максимально возможного количества");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Не достаточное количество носков на складе!");
    }

    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidSockRequestException(Exception e) {
        log.error(e.getMessage(), e);
        System.out.println("Не корректный ввод данных");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Данные введены не корректно!");
    }
}

