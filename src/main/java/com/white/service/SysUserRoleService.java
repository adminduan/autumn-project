package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysUserRoleModel;
import com.white.bean.request.SysUserQueryRequest;
import com.white.bean.request.SysUserRoleRequest;
import com.white.bean.vo.SysRoleVo;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联信息 服务类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysUserRoleService extends IService<SysUserRoleModel> {


    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     */
    List<SysUserRoleModel> getSysUserRoleModel(Integer userId);


    /**
     *  获取用户 角色列表信息
     * @param sysUserQueryRequest
     * @return
     */
    ListCountVo listUserRole(SysUserQueryRequest sysUserQueryRequest);


    /**
     * 根据用户 获取角色列表消息
     * @param userId
     * @return
     */
    List<SysRoleVo> listSysRoleByUserId(Integer userId);


    /**
     * 新增用户角色请求对象
     * @param sysUserRoleRequest
     * @return
     */
    Boolean addSysUserRole(SysUserRoleRequest sysUserRoleRequest);
}
