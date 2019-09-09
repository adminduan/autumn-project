package com.white.controller;


import com.white.bean.common.ListCountVo;
import com.white.bean.common.ResponseListVo;
import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysUserPasswordRequest;
import com.white.bean.request.SysUserQueryRequest;
import com.white.bean.request.SysUserRequest;
import com.white.bean.vo.SimpleUserVo;
import com.white.constants.Constants;
import com.white.service.SysUserService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户信息 前端控制器
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;


    /**
     * 新增用户信息
     * @param sysUserRequest
     * @return
     */
    @PostMapping("/add-user")
    public ResponseVo addUser(@RequestBody SysUserRequest sysUserRequest) {
        return sysUserService.addUser(sysUserRequest) ? ResponseUtil.success() : ResponseUtil.fail();
    }


    /**
     * 修改用户信息
     * @param sysUserRequest
     * @return
     */
    @PostMapping("/update-user")
    public ResponseVo updateUser(@RequestBody SysUserRequest sysUserRequest) {
        return sysUserService.updateUser(sysUserRequest, null) ? ResponseUtil.success() : ResponseUtil.fail();
    }

    /**
     * 删除用户信息
     * @param sysUserRequest
     * @return
     */
    @PostMapping("/delete-user")
    public ResponseVo deleteUser(@RequestBody SysUserRequest sysUserRequest){
        return sysUserService.deleteUser(sysUserRequest) ? ResponseUtil.success() : ResponseUtil.fail();
    }


    /**
     * 获取用户列表
     * @param sysUserQueryRequest
     * @return
     */
    @GetMapping("/list-user")
    public ResponseListVo listUser(SysUserQueryRequest sysUserQueryRequest) {
        ListCountVo listCountVo = sysUserService.listUser(sysUserQueryRequest);
        return new ResponseListVo(listCountVo.getList(), listCountVo.getCount());
    }

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserById/{userId}")
    public ResponseVo getUserById(@PathVariable("userId") Integer userId) {
        SimpleUserVo simpleUserVo = sysUserService.getUserById(userId);
        return ResponseUtil.success(simpleUserVo);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/login-out")
    public ResponseVo loginOut(HttpServletRequest request) {
        return sysUserService.loginOut(request.getHeader(Constants.HEADER_TOKEN))
                ? ResponseUtil.success() : ResponseUtil.fail();
    }


    /**
     * 修改密码
     * @param sysUserPasswordRequest
     * @return
     */
    @PostMapping("/modify-password")
    public ResponseVo modifyPassword(@RequestBody SysUserPasswordRequest sysUserPasswordRequest) {
        return sysUserService.modifyPassword(sysUserPasswordRequest)
            ? ResponseUtil.success() : ResponseUtil.fail();
    }

}

