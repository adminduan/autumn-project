package com.white.bean.request;

import com.white.bean.common.BasePageRequest;
import lombok.Data;


/**
 * 菜单列表查询
 * @author duanlsh
 */
@Data
public class SysMenuQueryRequest extends BasePageRequest {

    /**
     * 父节点
     */
    private String parentMenuName;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单访问地址
     */
    private String menuUrl;

    /**
     * 是否启用 0 否 1 启用
     */
    private Integer menuUsing;

}
