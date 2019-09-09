package com.white.bean.vo;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 系统菜单信息
 */
@Data
public class SysMenuVo {

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
     * 菜单启用和禁用
     */
    private Boolean menuUsing;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private List<SysMenuVo> childSysMenu;
}
