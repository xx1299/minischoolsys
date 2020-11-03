package com.s1mple.minischoolsys.web.controller;


import com.s1mple.minischoolsys.domain.AjxsResponse;
import com.s1mple.minischoolsys.exception.CustomException;
import com.s1mple.minischoolsys.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice(basePackages = "com.s1mple.minischoolsys.web.controller")
@Slf4j
public class ExceptionController {

    @ExceptionHandler(UnauthorizedException.class)
    public AjxsResponse unauthorizedException(Exception exception){
        exception.printStackTrace();
        log.error("权限不足");
        return AjxsResponse.error(new CustomException(ExceptionType.OTHER_ERROR),"权限不足");
    }


    /**
     * 处理自定义异常，并封装为AjxsResponse返回
     * @param exception 异常
     * @return Ajxs通用返回类
     */
    @ExceptionHandler(CustomException.class)
    public AjxsResponse customException(CustomException exception){
        exception.printStackTrace();
        if (exception.getCode()== ExceptionType.SYSTEM_ERROR.getCode()){
            log.error("代码出错,错误信息:"+exception.getMessage());
        }
        return AjxsResponse.error(exception);
    }



    /**
     * 处理其他异常，并封装为AjxsResponse返回
     * @param exception 异常
     * @return Ajxs通用返回类
     */
    @ExceptionHandler(Exception.class)
    public AjxsResponse exception(Exception exception){
        exception.printStackTrace();
        log.error("代码出错,错误信息:"+exception.getMessage());
        return AjxsResponse.error(new CustomException(ExceptionType.OTHER_ERROR));
    }


}
