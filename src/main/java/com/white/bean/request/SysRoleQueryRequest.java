package com.white.bean.request;

import com.white.bean.common.BasePageRequest;
import lombok.Data;

/**
 * @author duanlsh
 * @description 角色查询请求对象
 * @date 2019/8/14
 */
@Data
public class SysRoleQueryRequest extends BasePageRequest {


    private String roleName;

    private Integer roleUsing;

    private Integer roleLock;
}
