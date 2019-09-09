package com.white.bean.request;


import com.white.bean.common.BasePageRequest;
import lombok.Data;

/**
 * @author duanlsh
 * 用户对象信息
 */
@Data
public class SysUserQueryRequest extends BasePageRequest {


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
