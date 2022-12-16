package com.q7g.nucoj_spring.handler;

import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.util.Utils;
import com.q7g.nucoj_spring.vo.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Objects;

import static com.q7g.nucoj_spring.enums.ResultCodeEnum.*;


/**
 * 全局异常处理
 **/
@Log4j2
@RestControllerAdvice
public class ControllerAdviceHandler {

    /**
     * 处理服务异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = BizException.class)
    public Result<?> errorHandler(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> errorHandler(MethodArgumentNotValidException e) {
        return Result.fail(VALID_ERROR.getCode(), Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 手动捕获权限不足异常
     * @param e 异常
     * @throws AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail(FAIL.getCode(), "权限不足");
    }

    /**
     * 处理密码错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result<?> errorHandler(BadCredentialsException e) {
        return Result.fail(FAIL.getCode(), e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result<?> errorHandler(Exception e) {
        System.out.println(Utils.now());
        System.out.println(e.getMessage());
        System.out.println(e.getCause().getMessage());
        return Result.fail(SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
