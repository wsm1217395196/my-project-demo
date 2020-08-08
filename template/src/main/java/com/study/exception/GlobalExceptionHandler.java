package com.study.exception;

import com.study.result.ResultEnum;
import com.study.result.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultView runtimeException(RuntimeException e) {
        e.printStackTrace();
        printlnLog(e);
        return ResultView.error(ResultEnum.CODE_666);
    }

    /**
     * 自定义运行时异常
     */
    @ExceptionHandler(value = MyRuntimeException.class)
    public ResultView myRuntimeException(MyRuntimeException e) {
        e.printStackTrace();
        return e.getResultView();
    }

    /**
     * service层的参数校验异常,controller层的参数校验异常
     */
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResultView constraintViolationExceptionHandler(Exception e) {
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();

        if (e instanceof ConstraintViolationException) {
            for (ConstraintViolation<?> constraintViolation : ((ConstraintViolationException) e).getConstraintViolations()) {
                // 使用 ; 分隔多个错误
                if (detailMessage.length() > 0) {
                    detailMessage.append(";");
                }
                // 拼接内容到其中
                detailMessage.append(constraintViolation.getMessage());
            }
        }

        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                // 使用 ; 分隔多个错误
                if (detailMessage.length() > 0) {
                    detailMessage.append(";");
                }
                // 拼接内容到其中
                detailMessage.append(fieldError.getDefaultMessage());
            }
        }

        // 包装结果
        return ResultView.validError(ResultEnum.CODE_400.getMsg() + "：" + detailMessage);
    }

    /**
     * 打印关键log信息
     *
     * @param e
     */
    private void printlnLog(Exception e) {
        log.info(e.getMessage());
        log.info(e.getStackTrace()[0].toString());

        log.error(e.getMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.error(stackTraceElement.toString());
        }
    }
}
