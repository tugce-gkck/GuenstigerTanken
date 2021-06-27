package htwberlin.guenstigertanken;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OnlyReporterAdvice {

    @ResponseBody
    @ExceptionHandler(OnlyReporterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String onlyReporterHandler(OnlyReporterException ex) {
        return ex.getMessage();
    }
}
