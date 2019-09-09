package com.white.exception;

import com.white.enums.WhiteExceptionEnum;
import lombok.Getter;

/**
 * @author duanlsh
 * @description 自定义系统异常
 * @date 2019/7/15
 */
@Getter
public class WhiteException extends RuntimeException {


    private WhiteExceptionEnum whiteExceptionEnum;
    private String[] param;


    public WhiteException(WhiteExceptionEnum whiteExceptionEnum, String... param){
        super("code:[" + whiteExceptionEnum.getCode() + "] desc: [" + whiteExceptionEnum.getMessage() + "]");
        this.whiteExceptionEnum = whiteExceptionEnum;
        this.param = param;
    }



}
