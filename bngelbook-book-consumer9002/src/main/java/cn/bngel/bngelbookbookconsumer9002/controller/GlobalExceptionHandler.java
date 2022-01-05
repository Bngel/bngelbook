package cn.bngel.bngelbookbookconsumer9002.controller;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import cn.bngel.bngelbookcommonapi.exceptionHandler.CommonExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketTimeoutException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends CommonExceptionHandler {
}
