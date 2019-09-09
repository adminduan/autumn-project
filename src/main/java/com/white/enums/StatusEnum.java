package com.white.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanlsh
 *
 * 总状态信息
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     *
     */
    VALIDATE(true, "有效"),
    INVALIDATE(false, "无效");

    private Boolean status;
    private String message;
}
