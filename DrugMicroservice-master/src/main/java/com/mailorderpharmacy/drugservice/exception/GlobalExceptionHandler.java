package com.mailorderpharmacy.drugservice.exception;

import feign.RetryableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import com.mailorderpharmacy.drugservice.entity.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ InvalidTokenException.class })
	public ResponseEntity<ExceptionResponse> invalidTokenException(final InvalidTokenException invalidTokenException) {
		return (ResponseEntity<ExceptionResponse>) new ResponseEntity(
				(Object) new ExceptionResponse(invalidTokenException.getMessage(), LocalDateTime.now(),
						HttpStatus.UNAUTHORIZED),
				HttpStatus.UNAUTHORIZED);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler({ DrugNotFoundException.class })
	public ResponseEntity<ExceptionResponse> drugNotFoundException(final DrugNotFoundException drugNotFoundException) {
		return (ResponseEntity<ExceptionResponse>) new ResponseEntity((Object) new ExceptionResponse(
				drugNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler({ StockNotFoundException.class })
	public ResponseEntity<ExceptionResponse> stockNotFoundException(
			final StockNotFoundException stockNotFoundException) {
		return (ResponseEntity<ExceptionResponse>) new ResponseEntity((Object) new ExceptionResponse(
				stockNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unchecked")
	@ExceptionHandler({ RetryableException.class })
	public ResponseEntity<ExceptionResponse> microServiceUnavailableException() {
		return (ResponseEntity<ExceptionResponse>) new ResponseEntity(
				(Object) new ExceptionResponse("MicroServiceUnavailable", LocalDateTime.now(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
}