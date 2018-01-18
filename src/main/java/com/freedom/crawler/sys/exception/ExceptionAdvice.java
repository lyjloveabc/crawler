package com.freedom.crawler.sys.exception;

import com.freedom.crawler.bean.common.ResultCode;
import com.freedom.crawler.core.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Thor on 2018/1/18.
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * 配合hibernate valition的@Valid注解，抛出异常后，在这里处理
     *
     * @return 错误信息（约束不满足）
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleContraintViolation(ConstraintViolationException e, WebRequest request) {
        ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();//取第一个不符合的值返回
        System.out.println(11111);
        log.error(e.getMessage());
        return new ResponseEntity<>(
                ResultUtil.error(ResultCode.PARAM_ERROR, constraintViolation == null ? "" : constraintViolation.getMessage()),
                HttpStatus.OK
        );
    }

    /**
     * 配合hibernate valition的@Valid注解，抛出异常后，在这里处理
     *
     * @return 错误信息（类型不符）
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest request) {
        System.out.println(22222);
        log.error(e.getMessage());
        return new ResponseEntity<>(
                ResultUtil.error(ResultCode.PARAM_ERROR, e.getName() + " should be of type " + e.getRequiredType().getName()),
                HttpStatus.OK
        );
    }

    /**
     * 缺少必要参数
     *
     * @return 错误信息（缺少参数）
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        System.out.println(33333);
        log.error(e.getMessage());
        return new ResponseEntity<>(
                ResultUtil.error(ResultCode.PARAM_ERROR, e.getMessage()),
                HttpStatus.OK
        );
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<Object> handlBindException(BindException e) {
        System.out.println(44444);
        log.error(e.getMessage());

        Map<String, String> msg = e.getAllErrors().stream().map(er -> (FieldError) er)
                .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
        return new ResponseEntity<>(
                ResultUtil.error(ResultCode.PARAM_ERROR, msg),
                HttpStatus.OK
        );
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        System.out.println(55555);
        log.error("未处理异常", e);

        String msg = e.getMessage();//默认错误信息

        if (e instanceof NullPointerException) {
            msg = "系统异常，未找到关键数据";
        } else if (e instanceof DataIntegrityViolationException) {
            String[] causeMsgArray = e.getCause().getMessage().split("'");
            msg = "[" + causeMsgArray[1] + "]输入太长了";
        }

        return new ResponseEntity<>(ResultUtil.defaultError(msg), HttpStatus.OK);
    }
}
