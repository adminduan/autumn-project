package com.white.bean.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * 根据角色id查询菜单列表
 */
@Getter
@Setter
public class SysMenuRoleQueryRequest {

    /**
     * 角色Id
     */
    private Set<Integer> roleIdSet;
}
