package com.white.controller;


import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysUserRoleRequest;
import com.white.service.SysUserRoleService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户角色关联信息 前端控制器
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/sys-user-role")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @PostMapping("/add-user-role")
    public ResponseVo addUserRole(@RequestBody SysUserRoleRequest sysUserRoleRequest) {
        return sysUserRoleService.addSysUserRole(sysUserRoleRequest) ? ResponseUtil.success()
                : ResponseUtil.fail();
    }
}

