package com.study.exception;

import com.study.result.ResultEnum;
import com.study.result.ResultView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MyRuntimeException.class)
    public ResultView defaultErrorHandler(MyRuntimeException e) {
        logger.error(e.getMessage());
        logger.info(e.getMessage());
        logger.error(String.valueOf(e.getStackTrace()[0]));
        logger.info(String.valueOf(e.getStackTrace()[0]));
        e.printStackTrace();
        return e.getResultView();
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResultView defaultErrorHandler(RuntimeException e) {
        logger.error(e.getMessage());
        logger.info(e.getMessage());
        logger.error(String.valueOf(e.getStackTrace()[0]));
        logger.info(String.valueOf(e.getStackTrace()[0]));
        e.printStackTrace();
        if (e instanceof DuplicateKeyException) {
            return ResultView.error("外键异常");
        }
        return ResultView.error(ResultEnum.CODE_666);
    }
}
