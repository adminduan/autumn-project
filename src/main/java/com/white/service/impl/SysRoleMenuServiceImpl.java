package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.white.bean.model.SysMenuModel;
import com.white.bean.model.SysRoleMenuModel;
import com.white.bean.request.SysMenuRoleQueryRequest;
import com.white.bean.request.SysMenuRoleRequest;
import com.white.bean.vo.SysMenuVo;
import com.white.bean.vo.SysRoleVo;
import com.white.enums.StatusEnum;
import com.white.enums.WhiteExceptionEnum;
import com.white.mapper.SysRoleMenuMapper;
import com.white.service.SysMenuService;
import com.white.service.SysRoleMenuService;
import com.white.service.SysRoleService;
import com.white.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色菜单表 服务实现类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuModel> implements SysRoleMenuService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    private List<SysRoleMenuModel> getSysRoleMenuModel(Integer menuId) {
        LambdaQueryWrapper<SysRoleMenuModel> wrapper = Wrappers.lambdaQuery(new SysRoleMenuModel());
        wrapper.eq(SysRoleMenuModel::getStatus, StatusEnum.VALIDATE.getStatus());
        wrapper.eq(SysRoleMenuModel::getMenuId, menuId);
        return super.list(wrapper);
    }

    @Override
    public List<SysRoleVo> listSysRoleByMenuId(Integer menuId){

        List<SysRoleMenuModel> sysRoleMenuModelList = this.getSysRoleMenuModel(menuId);
        Set<Integer> roleIdSet = sysRoleMenuModelList.stream().map(SysRoleMenuModel::getRoleId).collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(roleIdSet)){
            return sysRoleService.listSysRoleByIds(roleIdSet);
        }
        return new ArrayList<>();
    }


    private void distributeMenuRole(Integer menuId, Set<Integer> roleIdSet){
        //查看角色是否存在
        List<SysRoleVo> sysRoleVoList = sysRoleService.listSysRoleByIds(roleIdSet);
        ValidateUtil.isTrue(sysRoleVoList.size() != roleIdSet.size(), WhiteExceptionEnum.DATA_EXPIRE);
        Set<Integer> requestRoleId = sysRoleVoList.stream().map(SysRoleVo::getId).collect(Collectors.toSet());
        //查询当前所有的角色
        List<SysRoleMenuModel> sysRoleMenuModelList = this.getSysRoleMenuModel(menuId);
        Set<Integer> existRoleId = sysRoleMenuModelList.stream().map(SysRoleMenuModel::getRoleId).collect(Collectors.toSet());

        //删除多余的角色
        Set<Integer> deleteRoleId = new HashSet<>(existRoleId);
        deleteRoleId.removeAll(requestRoleId);

        if (!CollectionUtils.isEmpty(deleteRoleId)) {
            LambdaQueryWrapper lambdaQueryWrapper = Wrappers.lambdaQuery(new SysRoleMenuModel())
                    .eq(SysRoleMenuModel::getMenuId, menuId)
                    .in(SysRoleMenuModel::getRoleId, deleteRoleId);
            ValidateUtil.isTrue(!super.remove(lambdaQueryWrapper), WhiteExceptionEnum.FAIL);
        }

        //新增不存在的角色
        Set<Integer> addRoleId = new HashSet<>(requestRoleId);
        addRoleId.removeAll(existRoleId);

        if (!CollectionUtils.isEmpty(addRoleId)) {
            for (Integer roleId : addRoleId) {
                SysRoleMenuModel sysRoleMenuModel = new SysRoleMenuModel();
                sysRoleMenuModel.setMenuId(menuId);
                sysRoleMenuModel.setRoleId(roleId);
                sysRoleMenuModel.setStatus(StatusEnum.VALIDATE.getStatus());
                sysRoleMenuModel.setUpdateTime(LocalDateTime.now());
                sysRoleMenuModel.setCreateTime(LocalDateTime.now());
                ValidateUtil.isTrue(!super.save(sysRoleMenuModel), WhiteExceptionEnum.FAIL);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addMenuRole(SysMenuRoleRequest sysMenuRoleRequest) {
        if (sysMenuRoleRequest.getRoleIdSet() == null) {
            sysMenuRoleRequest.setRoleIdSet(new HashSet<>());
        }
        //查看菜单是否存在
        SysMenuModel sysMenuModel = sysMenuService.getById(sysMenuRoleRequest.getMenuId());
        ValidateUtil.isTrue(sysMenuModel == null, WhiteExceptionEnum.DATA_NOT_FOUND);
        ValidateUtil.isTrue(!sysMenuModel.getStatus(), WhiteExceptionEnum.DATA_EXPIRE);
        if (sysMenuModel.getParentId() != null && sysMenuModel.getParentId() > 0) {
            SysMenuModel sysMenuParentModel = sysMenuService.getById(sysMenuModel.getParentId());
            ValidateUtil.isTrue(sysMenuParentModel == null, WhiteExceptionEnum.DATA_NOT_FOUND);
            ValidateUtil.isTrue(!sysMenuParentModel.getStatus(), WhiteExceptionEnum.DATA_EXPIRE);
            this.distributeMenuRole(sysMenuParentModel.getId(), sysMenuRoleRequest.getRoleIdSet());
        }

        this.distributeMenuRole(sysMenuModel.getId(), sysMenuRoleRequest.getRoleIdSet());

        return true;
    }


    @Override
    public Set<Integer> listMenuIdByRoleIdList(Set<Integer> roleIdSetRaw){
        //根据角色id获取角色列表
        List<SysRoleVo> sysRoleVoList = sysRoleService.listSysRoleByIds(roleIdSetRaw);
        Set<Integer> roleIdSet = sysRoleVoList.stream().map(SysRoleVo::getId).collect(Collectors.toSet());
        //获取菜单列表
        LambdaQueryWrapper<SysRoleMenuModel> wrapper = Wrappers.lambdaQuery(new SysRoleMenuModel())
                .eq(SysRoleMenuModel::getStatus, StatusEnum.VALIDATE.getStatus())
                .in(SysRoleMenuModel::getRoleId, roleIdSet);
        List<SysRoleMenuModel> sysRoleMenuModelList = super.list(wrapper);
        return sysRoleMenuModelList.stream().map(SysRoleMenuModel::getMenuId).collect(Collectors.toSet());
    }



    @Override
    public List<SysMenuVo> listMenuByRoleIdList(SysMenuRoleQueryRequest sysMenuRoleQueryRequest) {
        return sysMenuService.listSysMenuBymenuIdSet(this.listMenuIdByRoleIdList(sysMenuRoleQueryRequest.getRoleIdSet()));
    }
}
