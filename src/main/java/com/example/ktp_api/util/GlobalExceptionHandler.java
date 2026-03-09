package com.example.ktp_api.util;

import com.example.ktp_api.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handler validasi @Valid — mengembalikan field mana yang salah
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>
    handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field = ((FieldError) err).getField();
            errors.put(field, err.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, "Validasi gagal", errors));
    }

    // Handler nomor KTP duplikat / illegal argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>>
    handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ex.getMessage()));
    }

    // Handler data tidak ditemukan
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Object>>
    handleNotFound(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // Handler semua error lainnya
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>>
    handleGeneral(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error("Terjadi kesalahan server: " + ex.getMessage()));
    }

    // Tambahkan method ini di dalam class
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>>
    handleNoHandler(NoHandlerFoundException ex) {
        // Abaikan request favicon.ico
        if (ex.getRequestURL().contains("favicon.ico")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Endpoint tidak ditemukan: " + ex.getRequestURL()));
    }
}
