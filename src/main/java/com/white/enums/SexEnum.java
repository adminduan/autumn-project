package com.white.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanlsh
 *  性别
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    /**
     *
     */
    UNKNOWN(0, "未知"),
    MAN(1 ,"男"),
    WOMEN(2, "女");

    private Integer type;
    private String message;
}
