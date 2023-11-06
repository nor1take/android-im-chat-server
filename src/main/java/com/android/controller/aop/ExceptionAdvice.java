package com.android.controller.aop;

import com.android.controller.Code;
import com.android.controller.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        return new Result(Code.EXCEPTION_ERR, ex.getMessage(), "出现异常");
    }
}
