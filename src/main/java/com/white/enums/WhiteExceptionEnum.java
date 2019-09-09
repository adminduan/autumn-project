package com.white.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanlsh
 * @description 异常code码
 * @date 2019/7/15
 */
@Getter
@AllArgsConstructor
public enum WhiteExceptionEnum {
    /**
     * 成功
     */
    SUCCESS(1, "success"),
    PARAM_MISS(200001, "参数缺失"),
    PARAM_ERROR(200002, "参数有误"),
    /**
     * 数据不存在
     */
    DATA_NOT_FOUND(300001, "数据不存在"),
    DATA_EXPIRE(300002, "数据失效,请刷新页面"),
    USER_EXISTS(300003, "用户已经存在"),
    USER_LOGIN_PASSWORD_ERROR(300004, "密码错误"),
    USER_NOT_EXISTS(300005, "用户不存在"),
    USER_FORBIDDEN(300006, "用户被禁用"),
    USER_INVALIDATE(300007, "用户无效"),

    HEADER_TOKEN_ERROR(300008, "token无效或者不存在"),
    USER_LOGIN_EXPIRE(300010, "用户登录已经过期"),

    MENU_CAN_NOT_DELETE(400001, "存在子节点不可删除"),
//    DATA_UPDATE_FAIL(300002, "更新失败"),
//    DATA_DELETE_FAIL(300003, "删除失败"),
//
//    USER_LOGIN_PASSWORD_ERROR(400001, "密码错误"),
//    USER_REGISTER_FOUND(400002, "用户已经注册"),
//
//    PRODUCT_UNDER_SHELF(500001, "产品下架"),
    FAIL(999, "fail"),
    ;

    private int code;
    private String message;
}
