package com.villageroad.exception.handler;

import com.villageroad.exception.FeignException;
import com.villageroad.model.BizError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author houshengbin
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(value = WebException.class)
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    public Response<String> bizError(HttpServletRequest request, ToolchainBusinessException exception) {
//        log.error("businessException, req:{}", JsonUtil.toJson(request), exception);
//        return Response.error(exception.getStatusCode(), exception.getMessage());
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = {DistributedLockException.class, LockNotAvailableException.class})
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    public Response<String> repeatSubmitError(HttpServletRequest request, DistributedLockException exception) {
//        log.error("repeat submission, req:{}", JsonUtil.toJson(request), exception);
//        return Response.error(StatusCode.REPEAT_SUBMISSION, StatusCode.REPEAT_SUBMISSION.getMessage());
//    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> baseException(HttpServletRequest request, Exception exception) {
        if (exception instanceof MissingServletRequestParameterException) {
            log.error("parameters are not valid!", exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception);
        } else if (exception instanceof FeignException) {
            log.error("dependence service failed!", exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception);
        }
        log.error("Unexpected exception occurred!", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }
}
