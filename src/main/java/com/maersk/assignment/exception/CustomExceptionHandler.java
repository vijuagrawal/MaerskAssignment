package com.maersk.assignment.exception;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	   
   
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorDetails error = new ErrorDetails(new Date(),"Validation Failed", details);
        logger.error(getExceptionMessage(ex));
        logger.error(error.toString());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Object> handleCassandraExceptions(DataAccessException ex, WebRequest request) {
    	   List<String> details = new ArrayList<>();
        details.add("Sorry there was a problem processing your request");
        ErrorDetails error = new ErrorDetails(new Date(),"INTERNAL SERVER ERROR", details);
        logger.error(getExceptionMessage(ex));   
        logger.error(error.toString());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    	   List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error(getExceptionMessage(ex));
        logger.error(ex.getClass().getSimpleName());
        ErrorDetails error = new ErrorDetails(new Date(),"Sorry there was a problem processing your request", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public static String getExceptionMessage(Exception ex) {
    	  String result = "";
    	  StackTraceElement[] stes = ex.getStackTrace();
    	  for (int i = 0; i < stes.length; i++) {
    	    result = result + stes[i].getClassName() + "." + stes[i].getMethodName() + "  " + stes[i].getLineNumber()
    	        + "line" + "\r\n";
    	  }
    	  return result;
    	}

 
}