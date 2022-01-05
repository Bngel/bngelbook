package cn.bngel.bngelbookcommonapi.exceptionHandler;

import cn.bngel.bngelbookcommonapi.bean.CommonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.SocketTimeoutException;

public class CommonExceptionHandler {

    @ExceptionHandler(value = SocketTimeoutException.class)
    public ResponseEntity<CommonResult> socketTimeOutExceptionHandler(HttpServletRequest httpServletRequest,
                                                                      HttpServletResponse httpServletResponse,
                                                                      Exception e) {
        return ResponseEntity.accepted().body(CommonResult.timeOutResult());
    }

}
