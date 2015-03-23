package basar.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessage> handleServletRequestBindingException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<ErrorMessage.Error> errors = new ArrayList<ErrorMessage.Error>(fieldErrors.size() + globalErrors.size());
        for (FieldError fieldError : fieldErrors) {
            ErrorMessage.Error error = new ErrorMessage.Error(fieldError.getField(), fieldError.getField() + ", " + fieldError.getDefaultMessage());
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            ErrorMessage.Error error = new ErrorMessage.Error(objectError.getObjectName(), objectError.getObjectName() + ", " + objectError.getDefaultMessage());
            errors.add(error);
        }
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(errors), HttpStatus.BAD_REQUEST);
    }

}
