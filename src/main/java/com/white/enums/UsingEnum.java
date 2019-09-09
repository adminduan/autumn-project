package com.white.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanlsh
 * @description 启用状态
 * @date 2019/8/13
 */
@Getter
@AllArgsConstructor
public enum UsingEnum {

    /**
     *
     */
    USING(true, "启用"),
    FORBIDDEN(false, "禁用");


    private Boolean type;
    private String message;
}
