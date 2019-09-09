package com.white.bean.request;


import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author duanlsh
 * 修改密码请求对象
 *
 */
@Getter
@Setter
public class SysUserPasswordRequest {

    private String rawPassword;

    private String newPassword;
}
