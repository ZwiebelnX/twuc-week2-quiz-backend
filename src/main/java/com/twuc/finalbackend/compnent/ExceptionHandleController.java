package com.twuc.finalbackend.compnent;

import com.twuc.finalbackend.models.dto.ErrorDto;
import com.twuc.finalbackend.models.exception.CustomBasicException;
import com.twuc.finalbackend.models.exception.CustomCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> argumentErrorHandler(MethodArgumentNotValidException e) {
        ErrorDto errorDto = ErrorDto.builder().errorCode(CustomCode.JSON_ARGUMENT_ILLEGAL.getCode()).message(e.getMessage()).build();
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> errorHandler(Exception e) {
        ErrorDto errorDto;
        if (e instanceof CustomBasicException) {
            CustomBasicException customBasicException = (CustomBasicException) e;
            errorDto = ErrorDto.builder().errorCode(customBasicException.getErrorCode().getCode()).message(e.getMessage()).build();
            return ResponseEntity.status(customBasicException.getHttpStatus()).body(errorDto);
        } else {
            errorDto = ErrorDto.builder().errorCode("50000").message(e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
        }

    }

}
