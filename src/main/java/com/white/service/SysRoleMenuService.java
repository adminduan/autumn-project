package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.bean.model.SysRoleMenuModel;
import com.white.bean.request.SysMenuRoleQueryRequest;
import com.white.bean.request.SysMenuRoleRequest;
import com.white.bean.vo.SysMenuVo;
import com.white.bean.vo.SysRoleVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统角色菜单表 服务类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysRoleMenuService extends IService<SysRoleMenuModel> {


    /**
     * 根据菜单id获取角色列表
     * @param menuId
     * @return
     */
    List<SysRoleVo> listSysRoleByMenuId(Integer menuId);


    /**
     * 新增菜单角色关系
     * @param sysMenuRoleRequest
     * @return
     */
    Boolean addMenuRole(SysMenuRoleRequest sysMenuRoleRequest);


    /**
     * 根据角色id获取菜单id列表
     * @param roleIdSetRaw
     * @return
     */
    Set<Integer> listMenuIdByRoleIdList(Set<Integer> roleIdSetRaw);

    /**
     * 根据角色Id列表获取菜单列表
     * @param sysMenuRoleQueryRequest
     * @return
     */
    List<SysMenuVo> listMenuByRoleIdList(SysMenuRoleQueryRequest sysMenuRoleQueryRequest);
}
