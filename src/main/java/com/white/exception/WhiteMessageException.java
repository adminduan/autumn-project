package com.white.exception;

import lombok.Getter;

/**
 * @author duanlsh
 * @description 自定义系统异常
 * @date 2019/7/15
 */
@Getter
public class WhiteMessageException extends RuntimeException {


    private String message;
    private String[] param;


    public WhiteMessageException(String message, String... param){
        super("code:[" + message + "] desc: [" + message + "]");
        this.message = message;
        this.param = param;
    }



}
