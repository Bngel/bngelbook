package cn.bngel.bngelbookaccountconsumer9003.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketTimeoutException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SocketTimeoutException.class)
    public ResponseEntity<CommonResult> socketTimeOutExceptionHandler(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        Exception e) {
        log.debug("Read Timed out");
        return ResponseEntity.accepted().body(CommonResult.commonFailureResult());
    }

}
