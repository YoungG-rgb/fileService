package kg.it.fileservice;

import kg.it.fileservice.response.ResponseModel;
import kg.it.fileservice.rest.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionhandler extends BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseModel<String> handleAnyException(Exception exception) {
        log.error(exception.getMessage());
        return exceptionResponse(exception.getMessage());
    }
}
