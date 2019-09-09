package com.white.bean.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author duanlsh
 * @description 角色返回对象信息
 * @date 2019/8/14
 */
@Data
public class SysRoleVo {

    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 是否启用 true 启用 false禁用
     */
    private Boolean roleUsing;

    /**
     * 是否锁定 true 锁定 false 无锁
     */
    private Boolean roleLock;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
