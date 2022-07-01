package com.atguigu.servicebase.exception;

import com.atguigu.commonutils.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody   // 响应异常数据
    public CommonResult catchGlobalException(Exception e){
        e.printStackTrace();
        return CommonResult.error().message(e.getMessage());
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody   // 响应异常数据
    public CommonResult GuliException(GuliException e){
        e.printStackTrace();
        return CommonResult.error().code(e.getCode()).message(e.getMsg());
    }


    /**
     * 处理参数校验异常
     */
    @ResponseBody   // 响应异常数据
    @ExceptionHandler(MethodArgumentNotValidException.class)    // MethodArgumentNotValidException 无效参数异常
    public CommonResult MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return CommonResult.error().message(error.getDefaultMessage());
    }
}
