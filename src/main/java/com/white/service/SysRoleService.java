package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysRoleModel;
import com.white.bean.request.SysRoleQueryRequest;
import com.white.bean.request.SysRoleRequest;
import com.white.bean.vo.SysRoleVo;
import com.white.enums.StatusEnum;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统角色信息 服务类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysRoleService extends IService<SysRoleModel> {


    /**
     * 新增角色
     * @param sysRoleRequest
     * @return
     */
    Boolean addSysRole(SysRoleRequest sysRoleRequest);


    /**
     * 修改角色
     * @param sysRoleRequest
     * @return
     */
    Boolean updateSysRole(SysRoleRequest sysRoleRequest, StatusEnum statusEnum);


    /**
     * 删除角色
     * @param sysRoleRequest
     * @return
     */
    Boolean deleteSysRole(SysRoleRequest sysRoleRequest);


    /**
     * 根据id获取角色信息
     * @param roleId
     * @return
     */
    SysRoleVo getSysRoleById(Integer roleId);

    /**
     * 获取菜单列表
     * @param sysRoleQueryRequest
     * @return
     */
    ListCountVo listSysRole(SysRoleQueryRequest sysRoleQueryRequest);


    /**
     * 获取所有有效角色列表
     * @return
     */
    List<SysRoleVo> listSysRoleAll();

    /**
     * 根据角色id获取角色信息
     * @param roleIds
     * @return
     */
    List<SysRoleVo> listSysRoleByIds(Set<Integer> roleIds);
}
