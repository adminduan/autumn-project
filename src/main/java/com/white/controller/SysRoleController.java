package com.white.controller;


import com.white.bean.common.ListCountVo;
import com.white.bean.common.ResponseListVo;
import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysRoleQueryRequest;
import com.white.bean.request.SysRoleRequest;
import com.white.service.SysRoleMenuService;
import com.white.service.SysRoleService;
import com.white.service.SysUserRoleService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统角色信息 前端控制器
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 新增角色信息
     * @param sysRoleRequest
     * @return
     */
    @PostMapping("/add-sys-role")
    public ResponseVo addSysRole(@RequestBody SysRoleRequest sysRoleRequest){
        return sysRoleService.addSysRole(sysRoleRequest) ? ResponseUtil.success() : ResponseUtil.fail();
    }

    /**
     * 修改角色信息
     * @param sysRoleRequest
     * @return
     */
    @PostMapping("/update-sys-role")
    public ResponseVo updateSysRole(@RequestBody SysRoleRequest sysRoleRequest){
        return sysRoleService.updateSysRole(sysRoleRequest, null) ? ResponseUtil.success() : ResponseUtil.fail();
    }

    /**
     * 删除角色信息
     * @param sysRoleRequest
     * @return
     */
    @PostMapping("/delete-sys-role")
    public ResponseVo deleteSysRole(@RequestBody SysRoleRequest sysRoleRequest){
        return sysRoleService.deleteSysRole(sysRoleRequest) ? ResponseUtil.success() : ResponseUtil.fail();
    }


    /**
     * 获取角色列表
     * @param sysRoleQueryRequest
     * @return
     */
    @GetMapping("/list-sys-role")
    public ResponseListVo listSysRole(SysRoleQueryRequest sysRoleQueryRequest){
        ListCountVo listCountVo = sysRoleService.listSysRole(sysRoleQueryRequest);
        return new ResponseListVo(listCountVo.getList(), listCountVo.getCount());
    }


    @GetMapping("/list-sys-role-all")
    public ResponseVo listSysRoleAll() {
        return ResponseUtil.success(sysRoleService.listSysRoleAll());
    }


    /**
     * 根据菜单id获取角色列表
     * @param menuId
     * @return
     */
    @GetMapping("/list-sys-role-by-menu-id/{menuId}")
    public ResponseVo listSysRoleByMenuId(@PathVariable("menuId") Integer menuId){
        return ResponseUtil.success(sysRoleMenuService.listSysRoleByMenuId(menuId));
    }

    /**
     * 获取用户下的所有角色信息
     * @param userId
     * @return
     */
    @GetMapping("/list-sys-role-by-user-id/{userId}")
    public ResponseVo listSysRoleByUserId(@PathVariable("userId") Integer userId){
        return ResponseUtil.success(sysUserRoleService.listSysRoleByUserId(userId));
    }
}

