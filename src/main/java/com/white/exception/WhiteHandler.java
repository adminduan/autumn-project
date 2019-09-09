package com.white.exception;

import com.white.bean.common.ResponseVo;
import com.white.enums.WhiteExceptionEnum;
import com.white.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author duanlsh
 * @description 统一异常处理
 * @date 2019/7/15
 */
@RestControllerAdvice
@Slf4j
public class WhiteHandler {


    @ExceptionHandler(WhiteException.class)
    public ResponseVo whiteDewException(WhiteException whiteException){
        log.error("==>> whiteException", whiteException);
        return ResponseUtil.fail(whiteException.getWhiteExceptionEnum(), whiteException.getParam());
    }

    @ExceptionHandler(WhiteMessageException.class)
    public ResponseVo whiteDewException(WhiteMessageException whiteMessageException){
        log.error("==>> whiteException", whiteMessageException);
        return ResponseUtil.fail(WhiteExceptionEnum.PARAM_MISS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseVo globalException(Exception exception){
        log.error("==>> global exception", exception);
        return ResponseUtil.fail(WhiteExceptionEnum.FAIL);
    }
}
