package it.wineoclock.exceptions.handler;

import it.wineoclock.exceptions.WineoClockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handlerException(Exception e){
      log.info("GlobalExceptionHandler.handlerException ", e);
      HttpStatus err = HttpStatus.INTERNAL_SERVER_ERROR;
      ResponseError responseError = new ResponseError(
              "Errore generico",
              err.value(),
              LocalDateTime.now(),
              err.getReasonPhrase()
      );
      return ResponseEntity.status(err).body(responseError);
    }

    @ExceptionHandler(WineoClockException.class)
    public ResponseEntity<ResponseError> handlerWocException(WineoClockException e){
        log.info("GlobalExceptionHandler.handlerWocException", e);
        ResponseError responseError = new ResponseError(
                e.getMessage(),
                e.getStatus().value(),
                LocalDateTime.now(),
                e.getStatus().getReasonPhrase()
        );
        return ResponseEntity.status(e.getStatus()).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("GlobalExceptionHandler.handleMethodArgumentNotValidException", e);
        HttpStatus status = (HttpStatus) e.getStatusCode();
        List<String> validationErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ResponseError error = new ResponseError(
                String.join("; ", validationErrors),
                status.value(),
                LocalDateTime.now(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseError> handleMethodValidationException(HandlerMethodValidationException e) {
        log.error("GlobalExceptionHandler.handleMethodValidationException", e);
        HttpStatus status = (HttpStatus) e.getStatusCode();
        List<String> validationErrors = e.getParameterValidationResults()
                .stream()
                .map(result -> result.getResolvableErrors().stream())
                .reduce(Stream.empty(), Stream::concat)
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
        ResponseError error = new ResponseError(
                String.join("; ", validationErrors),
                status.value(),
                LocalDateTime.now(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(error);
    }
}
