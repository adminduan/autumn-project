package com.white.bean.vo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 简单的用户信息
 * @author duanlsh
 */
@Data
public class SimpleUserVo {

    private Integer id;

    /**
     * 用户名
     */
    private String userName;

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

    /**
     * 用户 锁定 true锁定 false 禁用
     */
    private Boolean userLock;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
