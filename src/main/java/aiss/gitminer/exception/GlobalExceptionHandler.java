package aiss.gitminer.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, List<String>> res = new HashMap<>();
        res.put("errors", errors);


        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> handleTypeMismatch(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        String message;

        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            String field = ife.getPath().get(0).getFieldName();
            String expectedType = ife.getTargetType().getSimpleName();
            Object value = ife.getValue();
            message = String.format("El campo '%s' recibió el valor '%s', pero se esperaba un tipo '%s'.",
                    field, value, expectedType);
        } else {
            message = "Error de formato en el cuerpo de la petición.";
        }

        Map<String, List<String>> res = new HashMap<>();
        res.put("errors", List.of(message));

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}