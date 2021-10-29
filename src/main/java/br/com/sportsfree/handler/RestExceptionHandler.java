package br.com.sportsfree.handler;

import br.com.sportsfree.error.RequestParamException;
import br.com.sportsfree.error.ResourceNotFoundExeption;
import br.com.sportsfree.error.details.GenericErrorDetails;
import br.com.sportsfree.error.details.ResourceNotFoundDetails;
import br.com.sportsfree.error.details.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundExeption resourceNotFoundExeption) {
        ResourceNotFoundDetails details = ResourceNotFoundDetails.builder()
                .titulo("Recurso não encontrado")
                .detalhes(resourceNotFoundExeption.getMessage())
                .mensagem(resourceNotFoundExeption.getClass().getName())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestParamException.class)
    public ResponseEntity<?> handleBusinessValidationException(RequestParamException bvException) {
        GenericErrorDetails details = GenericErrorDetails.builder()
                .titulo("Erro!")
                .detalhes(bvException.getMessage())
                .mensagem(bvException.getClass().getName())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleFieldValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining(","));
        ValidationErrorDetails details = ValidationErrorDetails.builder()
                .titulo("Erro na validação dos campos")
                .fields(fields)
                .detalhes("Erro na validação dos campos")
                .mensagem(methodArgumentNotValidException.getClass().getName())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
