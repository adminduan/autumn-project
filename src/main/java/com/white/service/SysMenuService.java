package com.white.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysMenuModel;
import com.white.bean.request.SysMenuQueryRequest;
import com.white.bean.request.SysMenuRequest;
import com.white.bean.vo.SysMenuVo;
import com.white.enums.StatusEnum;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysMenuService extends IService<SysMenuModel> {


    /**
     * 新增系统菜单
     * @param sysMenuRequest
     * @return
     */
    Boolean addSysMenu(SysMenuRequest sysMenuRequest);


    /**
     * 修改系统菜单
     * @param sysMenuRequest
     * @return
     */
    Boolean updateSysMenu(SysMenuRequest sysMenuRequest);


    /**
     * 删除系统菜单信息
     * @param sysMenuRequest
     * @param statusEnum
     * @return
     */
    Boolean deleteSysMenu(SysMenuRequest sysMenuRequest, StatusEnum statusEnum);


    /**
     * 根据id获取菜单信息
     * @param id
     * @return
     */
    SysMenuVo getSysMenuVoById(Integer id);

    /**
     *
     * 获取系统菜单列表
     *
     * @return
     */
    ListCountVo listSysMenu(SysMenuQueryRequest sysMenuQueryRequest);


    /**
     * 获取用户菜单
     * @return
     */
    List<SysMenuVo> listUserSysMenu();

    /**
     * 获取父级菜单
     * @param parentId 父id
     * @return
     */
    List<SysMenuVo> listParentSysMenu(Integer parentId);


    /**
     * 根据菜单id列表获取 菜单列表
     * @param menuIdSet
     * @return
     */
    List<SysMenuVo> listSysMenuBymenuIdSet(Set<Integer> menuIdSet);

}
