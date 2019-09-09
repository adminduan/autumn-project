package com.white.controller.pub;


import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysLoginRequest;
import com.white.service.SysUserService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户非登录访问
 */
@RequestMapping("/pub/sys-user")
@RestController
public class SysUserPubController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public ResponseVo login(@RequestBody SysLoginRequest sysLoginRequest) {
        return ResponseUtil.success(sysUserService.login(sysLoginRequest));
    }

}
