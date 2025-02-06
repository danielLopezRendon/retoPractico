package co.prueba.tecnica.module.client.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResponseAdviceException  extends ResponseEntityExceptionHandler{
	
	private static final String ERROR_LABEL = "error";

	

    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return handleExceptionInternal(ex, ecosystemError, headers, ecosystemError.getStatus(), request);
    }

    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return handleExceptionInternal(ex, ecosystemError, headers, ecosystemError.getStatus(), request);
    }


    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        errors.add( ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType());

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        errors.add(ex.getRequestPartName() + " part is missing");
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }
    
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        errors.add( ex.getParameterName() + " parameter is missing");
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());        
        final List<String> errors = new ArrayList<>();
        
        String name = "";
        if(ex.getRequiredType() != null) {
        	var requiredType = ex.getRequiredType();
        	if(requiredType != null) {
        		name = requiredType.getName();
        	}
        	else {
        		name = "unknown";
        	}
        }
        else {
        	name = "unknown";
        }
        errors.add( ex.getName() + " should be of type " + name);

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }


    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }	

 
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        errors.add( "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

      
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        final List<String> errors = new ArrayList<>();
        
        builder.append(ex.getMethod());
        errors.add( builder.append(" method is not supported for this request. Supported methods are ").toString());        
        var http = ex.getSupportedHttpMethods();
        if(http != null) http.forEach(t -> builder.append(t + " "));
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

       
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        errors.add(   builder.append(" media type is not supported. Supported media types are ").toString());
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), errors,null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

  
    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<Object> handleSQLException(final SQLException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error(ERROR_LABEL, ex);
        final List<String> errors = new ArrayList<>();
        errors.add(ex.getSQLState());
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, 
        		ex.getLocalizedMessage(), 
        		errors,
        		null);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

    @ExceptionHandler({ EcosystemException.class })
    public ResponseEntity<Object> handleEcosystemExceptionException(final EcosystemException ex, final WebRequest request) {        
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, 
        		ex.getLocalizedMessage(), 
        		ex.getErrors(),
        		ex.getSolutions());
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

   
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    	 final List<String> errors = new ArrayList<>();
         errors.add("error occurred");
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),errors,null );
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }	
}
