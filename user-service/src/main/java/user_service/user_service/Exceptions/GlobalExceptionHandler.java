package user_service.user_service.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import user_service.user_service.ApiResponse.ApiResponse;

import user_service.user_service.ApiResponse.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse<?>> handleValidationErrors(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	        );

	        return ResponseEntity.badRequest()
	                .body(ApiResponse.error("Validation failed", errors));
	    }
	 
	 @ExceptionHandler(BadRequest.class)
	 public ResponseEntity<ApiResponse<?>> handleBadRequest(BadRequest ex)
	 {
		 return ResponseEntity.badRequest()
		            .body(ApiResponse.error(ex.getMessage(), null));
	 }
}
