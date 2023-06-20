package az.iamusayev.exception;

import static az.iamusayev.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static az.iamusayev.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

import az.iamusayev.model.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ExceptionDto handleException(Exception ex) {
        log.error("Exception occurred: ", ex);
        return new ExceptionDto(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(IexCloudClientException.class)
    public ExceptionDto handleException(IexCloudClientException ex) {
        log.error("IexCloudClientException occurred: ", ex);
        return new ExceptionDto(ex.getMessage());
    }

    @ExceptionHandler(StockDataProcessingException.class)
    public ExceptionDto handleException(StockDataProcessingException ex) {
        log.error("StockDataProcessingException occurred: ", ex);
        return new ExceptionDto(ex.getMessage());
    }
}