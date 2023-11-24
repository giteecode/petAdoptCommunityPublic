package com.lian.pet.common.basic.exception;

import com.lian.pet.common.basic.response.AppResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    public AppResp handleServiceException(AppException e) {
        log.error(e.getMessage());
        return AppResp.failed(e);
    }
}
