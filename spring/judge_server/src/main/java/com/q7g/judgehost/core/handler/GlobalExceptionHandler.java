package com.q7g.judgehost.core.handler;

import com.q7g.judgehost.core.configuration.ExceptionCodeConfiguration;
import com.q7g.judgehost.exception.http.HttpException;
import com.q7g.judgehost.core.common.UnifiedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 实现全局异常处理
 * 统一返回值 errorCode method url 给前端
 *
 * @author yuzhanglong
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionCodeConfiguration exceptionCodeConfiguration;

    public GlobalExceptionHandler(ExceptionCodeConfiguration exceptionCodeConfiguration) {
        this.exceptionCodeConfiguration = exceptionCodeConfiguration;
    }

    /**
     * 拦截并处理程序内部发生的异常
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifiedResponse handleException(HttpServletRequest request, Exception exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String requestString = getRequestUrlString(method, requestUrl);
        exception.printStackTrace();
        //TODO: 日志记录
        return new UnifiedResponse("B0001", "SERVER_ERROR", requestString);
    }

    /**
     * 拦截自定义的异常处理（例如数据不存在等）
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(HttpException.class)
    @ResponseBody
    public ResponseEntity<UnifiedResponse> handleHttpException(HttpServletRequest request, HttpException exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String errorCode = exception.getCode();
        String message = getMessageByExceptionCode(errorCode);

        // 初始化unifyResponse
        UnifiedResponse unifiedResponse = new UnifiedResponse(errorCode, message, getRequestUrlString(method, requestUrl));

        // statusCode 到 HttpStatus
        HttpStatus httpStatus = HttpStatus.resolve(exception.getHttpStatusCode());
        assert httpStatus != null;
        return new ResponseEntity<>(unifiedResponse, null, httpStatus);
    }

    /**
     * 拦截请求错误 MethodNotAllowed
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public UnifiedResponse handleMethodNotAllowedException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String message = exception.getMessage();
        // 初始化unifyResponse
        return new UnifiedResponse("A0001", message, getRequestUrlString(method, requestUrl));
    }

    /**
     * 拦截参数验证的异常
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifiedResponse handleArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 获取验证失败的信息
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        String message = getMessageStringByValidateExceptionList(errorList);

        // 初始化unifyResponse
        return new UnifiedResponse("A0002", message, getRequestUrlString(method, requestUrl));
    }

    /**
     * 拦截参数验证的异常
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifiedResponse handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();

        // 获取验证失败的信息
        String message = exception.getMessage();

        // 初始化unifyResponse
        return new UnifiedResponse("A0002", message, getRequestUrlString(method, requestUrl));
    }

    /**
     * 拦截post请求内容为空的异常
     *
     * @param request   请求参数
     * @param exception 抛出的异常
     * @author yzl
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifiedResponse handleConstraintViolationException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        // 初始化unifyResponse
        return new UnifiedResponse("A0006", "请求内容为空", getRequestUrlString(method, requestUrl));
    }

    /**
     * 拼接RequestUrl字符串，以供返回到前端
     *
     * @param method 请求方法
     * @param url    请求的接口链接
     * @author yzl
     */
    private String getRequestUrlString(String method, String url) {
        return method + " " + url;
    }

    /**
     * 通过自定义的错误代码，来获取其对应的信息，以供返回给前端，这些信息来源于配置文件
     * 对于所有的错误信息请参考：resources/config/exception-codes
     *
     * @param code 自定义的错误代码
     * @author yzl
     */
    private String getMessageByExceptionCode(String code) {
        return exceptionCodeConfiguration.getMessage(code);
    }

    /**
     * 传入一个 参数验证的错误信息list，将错误信息拼接成字符串，以供返回给前端
     * 对于所有的错误信息请参考：resources/config/exception-codes
     *
     * @param errorList List<ObjectError> 错误信息list
     * @author yzl
     */
    private String getMessageStringByValidateExceptionList(List<ObjectError> errorList) {
        return errorList.get(0).getDefaultMessage();
    }
}