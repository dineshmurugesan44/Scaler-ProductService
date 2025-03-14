package com.scaler.productservice.advice;

import com.scaler.productservice.dto.ErrorDTO;
import com.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArugumentException(){
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setErrorCode("400");
        errorDTO.setErrorMsg("Bad Request");

        return ResponseEntity.badRequest().body(errorDTO);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(){
        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setErrorCode("404");
        errorDTO.setErrorMsg("Product Not Found");

        return ResponseEntity.badRequest().body(errorDTO);

    }

}
