package com.white.bean.request;


import lombok.Data;


/**
 * 登录请求对象
 */
@Data
public class SysLoginRequest {

    private String userName;

    private String password;
}
