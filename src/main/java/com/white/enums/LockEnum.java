package com.white.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanlsh
 * @description 锁定状态
 * @date 2019/8/14
 */
@AllArgsConstructor
@Getter
public enum  LockEnum {

    /**
     *
     */
    LOCK(true, "锁定"),
    EDIT(false, "可编辑");


    private Boolean type;
    private String message;
}
