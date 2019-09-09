package com.white.bean.request;


import lombok.Data;

import java.util.Set;


/**
 * @author duanlsh
 * 菜单角色对象
 */
@Data
public class SysMenuRoleRequest {

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 角色id列表
     */
    private Set<Integer> roleIdSet;
}
