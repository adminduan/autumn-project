package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysUserModel;
import com.white.bean.request.SysLoginRequest;
import com.white.bean.request.SysUserPasswordRequest;
import com.white.bean.request.SysUserQueryRequest;
import com.white.bean.request.SysUserRequest;
import com.white.bean.vo.SimpleUserVo;
import com.white.bean.vo.SysLoginVo;
import com.white.enums.StatusEnum;

/**
 * <p>
 * 系统用户信息 服务类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysUserService extends IService<SysUserModel> {


    /**
     * 登录
     * @param sysLoginRequest
     * @return
     */
    SysLoginVo login(SysLoginRequest sysLoginRequest);


    /**
     * 新增用户信息
     * @param sysUserRequest
     * @return
     */
    Boolean addUser(SysUserRequest sysUserRequest);


    /**
     * 修改用户信息
     * @param sysUserRequest
     * @param statusEnum
     * @return
     */
    Boolean updateUser(SysUserRequest sysUserRequest, StatusEnum statusEnum);


    /**
     * 删除用户信息
     * @param sysUserRequest
     * @return
     */
    Boolean deleteUser(SysUserRequest sysUserRequest);


    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    SimpleUserVo getUserById(Integer userId);

    /**
     * 根据用户id获取用户列表信息
     * @param sysUserQueryRequest
     * @return
     */
    ListCountVo listUser(SysUserQueryRequest sysUserQueryRequest);


    /**
     * 退出登录
     * @param token
     * @return
     */
    boolean loginOut(String token);


    /**
     * 修改密码
     * @param sysUserPasswordRequest
     * @return
     */
    boolean modifyPassword(SysUserPasswordRequest sysUserPasswordRequest);
}
