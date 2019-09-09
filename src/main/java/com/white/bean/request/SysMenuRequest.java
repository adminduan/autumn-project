package com.white.bean.request;


import lombok.Data;


/**
 * @author duanlsh
 *
 * 系统菜单
 */
@Data
public class SysMenuRequest {

    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 菜单图标
     */
    private String menuPic;

    /**
     * 菜单启用 1 启用 0 禁用
     */
    private Boolean menuUsing;

//    /**
//     * 菜单类型 1 父级菜单 2子集菜单 3 请求地址
//     */
//    private Integer menuType;
}
