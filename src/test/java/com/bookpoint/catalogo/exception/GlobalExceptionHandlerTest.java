package com.bookpoint.catalogo.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    // Clase dummy para simular validación
    static class DummyRequest {
        @NotBlank(message = "nombre no puede estar vacío")
        private String nombre;
    }

    @Test
    void manejoErroresValidacion_deberiaRetornarErrores() {

        DummyRequest obj = new DummyRequest();

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(obj, "dummyRequest");

        bindingResult.addError(
                new FieldError("dummyRequest", "nombre", "nombre no puede estar vacío")
        );

        MethodArgumentNotValidException ex =
                new MethodArgumentNotValidException(null, bindingResult);

        Map<String, String> response =
                handler.manejoErroresValidacion(ex);

        assertNotNull(response);
        assertTrue(response.containsKey("nombre"));
        assertEquals("nombre no puede estar vacío", response.get("nombre"));
    }
}