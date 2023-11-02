package com.diatoz.task1.exceptionHandler;

import com.diatoz.task1.customException.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler({IdException.class})
    public ResponseEntity<String> showException(IdException IdException) {
        return new ResponseEntity<>(IdException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotProper.class)
    public ResponseEntity<String> getDataNotProper(DataNotProper dataNotProper) {

        return new ResponseEntity<>(dataNotProper.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> databaseException(DataAccessException dataAccessException) {

        return new ResponseEntity<>(Objects.requireNonNull(dataAccessException.getRootCause()).getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> databaseException(JsonProcessingException jsonProcessingException) {

        return new ResponseEntity<>(Objects.requireNonNull(jsonProcessingException.getCause()).getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentDataException.class)
    public ResponseEntity<Map<String, String>> getStudentDataException(StudentDataException studentDataException) {
        return new ResponseEntity<>(studentDataException.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginDataException.class)
    public ResponseEntity<Map<String, String>> getLoginDataException(LoginDataException loginDataException) {
        return new ResponseEntity<>(loginDataException.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PasswordException.class})
    public ResponseEntity<String> getPassException(PasswordException passwordException) {
        return new ResponseEntity<>(passwordException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> getExceptionMessage(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
