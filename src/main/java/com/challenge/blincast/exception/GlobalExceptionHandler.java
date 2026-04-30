package com.challenge.blincast.exception;

import com.challenge.blincast.dto.DocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity<DocumentResponse> handleKeyAlreadyExists(KeyAlreadyExistsException ex) {
        log.warn("Conflito: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // HTTP 409
                .body(DocumentResponse.conflict());
    }

    @ExceptionHandler(KeyNotFoundException.class)
    public ResponseEntity<DocumentResponse> handleKeyNotFound(KeyNotFoundException ex) {
        log.warn("Não encontrado: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // HTTP 404
                .body(DocumentResponse.notFound());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DocumentResponse> handleMalformedJson(HttpMessageNotReadableException ex) {
        log.error("JSON malformado: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // HTTP 400
                .body(DocumentResponse.badRequest());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DocumentResponse> handleGenericException(Exception ex) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)  // HTTP 500
                .body(DocumentResponse.internalError());
    }
}