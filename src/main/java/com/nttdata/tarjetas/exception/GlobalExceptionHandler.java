package com.nttdata.tarjetas.exception;

import com.nttdata.tarjetas.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoEncontradoException.class)
    public ResponseEntity<ApiResponse<Object>> manejarNoEncontrado(NoEncontradoException ex) {
        ApiResponse<Object> respuesta = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ApiResponse<Object>> manejarValidacion(ValidacionException ex) {
        ApiResponse<Object> respuesta = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(ConflictoException.class)
    public ResponseEntity<ApiResponse<Object>> manejarConflicto(ConflictoException ex) {
        ApiResponse<Object> respuesta = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        ApiResponse<Object> respuesta = new ApiResponse<>(false, "El tipo solo puede ser CREDITO o DEBITO", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> manejarExcepcionGeneral(Exception ex) {
        ApiResponse<Object> respuesta = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
}
