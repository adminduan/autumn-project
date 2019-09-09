package com.white.controller;


import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysMenuRoleQueryRequest;
import com.white.bean.request.SysMenuRoleRequest;
import com.white.service.SysRoleMenuService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统菜单角色信息 前端控制器
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/sys-role-menu")
public class SysRoleMenuController {


    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 新增角色菜单
     * @param sysMenuRoleRequest
     * @return
     */
    @PostMapping("/add-menu-role")
    public ResponseVo addMenuRole(@RequestBody SysMenuRoleRequest sysMenuRoleRequest) {
        return ResponseUtil.success(sysRoleMenuService.addMenuRole(sysMenuRoleRequest));
    }


    /**
     * 根据角色id列表获取菜单列表
     * @param sysMenuRoleQueryRequest
     * @return
     */
    @PostMapping("/list-menu-by-roleId-list")
    public ResponseVo listMenuByRoleIdList(@RequestBody SysMenuRoleQueryRequest sysMenuRoleQueryRequest) {
        return ResponseUtil.success(sysRoleMenuService.listMenuByRoleIdList(sysMenuRoleQueryRequest));
    }

}

