package com.white.bean.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * 用户角色请求对象
 */
@Getter
@Setter
public class SysUserRoleRequest {

    private Integer userId;

    private Set<Integer> roleIdSet;
}
