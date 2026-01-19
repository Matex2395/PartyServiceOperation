package com.fisa.bian.partyserviceoperation.infrastructure.config;

import com.fisa.bian.partyserviceoperation.infrastructure.adapters.input.rest.dtos.ErrorCustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String SERVICE_NAME = "PartyServiceOperation";

    // Error de Comunicación con Mapper
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorCustomResponse> handleUpstreamError(RestClientException e, HttpServletRequest request) {
        log.error("Error llamando al microservicio Mapper: {}", e.getMessage());

        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "DependencyError",
                "El Mapper Service respondió con error: " + e.getMessage(),
                request
        );
    }

    // Errores de Validación (@Valid en el Controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCustomResponse> handleValidationErrors(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorDetail = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "Datos inválidos";

        return buildResponse(
                HttpStatus.BAD_REQUEST, // 400
                "ValidationError",
                errorDetail,
                request
        );
    }

    // 3. Errores Generales (Bug, NullPointer, BD interna del Service Operation)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCustomResponse> handleGeneralException(Exception e, HttpServletRequest request) {
        log.error("Error interno en PartyService: {}", e.getMessage());

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "InternalPartyError",
                e.getMessage(),
                request
        );
    }

    private ResponseEntity<ErrorCustomResponse> buildResponse(HttpStatus status, String type, String message, HttpServletRequest request) {
        ErrorCustomResponse error = ErrorCustomResponse.builder()
                .origin(SERVICE_NAME) //El Party Service Operation se identifica como el causante del error
                .errorType(type)
                .message(message)
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, status);
    }
}