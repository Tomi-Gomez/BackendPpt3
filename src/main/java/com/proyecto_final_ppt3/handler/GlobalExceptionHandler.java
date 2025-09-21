package com.proyecto_final_ppt3.handler;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMedicoNotFound(MedicoNotFoundException ex, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MedicosNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMedicosNotFound(MedicosNotFoundException ex, WebRequest webRequest) {
        ErrorResponse error = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AdministrativoInsertException.class)
    public ResponseEntity<ErrorResponse> handlerAdministrativoInsert
            (AdministrativoInsertException ex, WebRequest webRequest){
        ErrorResponse error = ErrorResponse.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(webRequest.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
