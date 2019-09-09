package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.bean.model.SysUserRoleModel;
import com.white.bean.model.ext.SysUserRoleQueryExtModel;
import com.white.bean.model.ext.request.SysUserRoleQueryExtRequest;

import java.util.List;

/**
 * <p>
 * 系统用户角色关联信息 Mapper 接口
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleModel> {


    /**
     * 用户角色列表
     * @param sysUserRoleQueryExtRequest
     * @return
     */
    List<SysUserRoleQueryExtModel> listUserRole(SysUserRoleQueryExtRequest sysUserRoleQueryExtRequest);
}
