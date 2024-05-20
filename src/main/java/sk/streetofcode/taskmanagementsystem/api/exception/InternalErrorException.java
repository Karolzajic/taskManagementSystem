package sk.streetofcode.taskmanagementsystem.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String message){
        super (message);
    }
}
