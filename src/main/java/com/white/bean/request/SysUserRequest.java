package com.white.bean.request;


import lombok.Data;

/**
 * @author duanlsh
 * 用户对象信息
 */
@Data
public class SysUserRequest {


    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 显示名
     */
    private String name;

    /**
     * 头像地址
     */
    private String picUrl;

    /**
     * 性别 0 未知 1 男 2 女
     */
    private Integer sex;

    /**
     * 是否启用 true 启用 false 禁用
     */
    private Boolean userUsing;
}
