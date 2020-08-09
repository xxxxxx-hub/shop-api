package com.fh.shop.api.exception;

import com.fh.shop.api.brand.common.ResponseEnum;
import com.fh.shop.api.brand.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHander {
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public ServerResponse handleGlobalException(GlobalException e){
        ResponseEnum responseEnum = e.getResponseEnum();
        return ServerResponse.error(responseEnum);
    }
}
