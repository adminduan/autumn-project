package com.white.bean.request;

import lombok.Data;

/**
 * @author duanlsh
 * @description
 * @date 2019/8/14
 */
@Data
public class SysRoleRequest {

    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 是否启用 true 启用 false禁用
     */
    private Boolean roleUsing;

}
