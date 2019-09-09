package com.white.bean.model.ext.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author White
 * @description 用户 和用户角色信息
 * @date 2019/8/19
 */
@Getter
@Setter
public class SysUserRoleQueryExtRequest {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别 0 未知 1 男 2 女
     */
    private Integer sex;

    /**
     * 是否启用 true 启用 false 禁用
     */
    private Boolean userUsing;

    /**
     * 用户锁定 true 锁定 false未锁定
     */
    private Boolean userLock;
}
