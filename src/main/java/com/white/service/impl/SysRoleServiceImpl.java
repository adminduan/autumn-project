package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysRoleModel;
import com.white.bean.request.SysRoleQueryRequest;
import com.white.bean.request.SysRoleRequest;
import com.white.bean.vo.SysRoleVo;
import com.white.enums.LockEnum;
import com.white.enums.StatusEnum;
import com.white.enums.UsingEnum;
import com.white.enums.WhiteExceptionEnum;
import com.white.mapper.SysRoleMapper;
import com.white.service.SysRoleService;
import com.white.util.ValidateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统角色信息 服务实现类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleModel> implements SysRoleService {


    /**
     *  sysRoleModel 2 sysRoleVo
     * @param sysRoleModel
     * @return
     */
    private SysRoleVo packageSysRoleVo(SysRoleModel sysRoleModel){
        SysRoleVo sysRoleVo = new SysRoleVo();
        sysRoleVo.setId(sysRoleModel.getId());
        sysRoleVo.setRoleName(sysRoleModel.getRoleName());
        sysRoleVo.setRoleUsing(sysRoleModel.getRoleUsing());
        sysRoleVo.setRoleLock(sysRoleModel.getRoleLock());
        sysRoleVo.setUpdateTime(sysRoleModel.getUpdateTime());
        sysRoleVo.setCreateTime(sysRoleModel.getCreateTime());
        return sysRoleVo;
    }


    @Override
    public Boolean addSysRole(SysRoleRequest sysRoleRequest){
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleModel.setId(null);
        sysRoleModel.setRoleName(sysRoleRequest.getRoleName());
        sysRoleModel.setRoleUsing(sysRoleRequest.getRoleUsing());
        sysRoleModel.setRoleLock(LockEnum.EDIT.getType());
        sysRoleModel.setStatus(StatusEnum.VALIDATE.getStatus());
        sysRoleModel.setUpdateTime(LocalDateTime.now());
        sysRoleModel.setCreateTime(LocalDateTime.now());
        return super.save(sysRoleModel);
    }

    /**
     * 获取角色对象信息
     * @param roleId
     * @return
     */
    private SysRoleModel getSysRoleModelById(Integer roleId){
        SysRoleModel sysRoleModel = super.getById(roleId);
        ValidateUtil.isTrue(sysRoleModel == null, WhiteExceptionEnum.DATA_NOT_FOUND);
        ValidateUtil.isTrue(!sysRoleModel.getStatus(), WhiteExceptionEnum.DATA_EXPIRE);
        return sysRoleModel;
    }

    @Override
    public Boolean updateSysRole(SysRoleRequest sysRoleRequest, StatusEnum statusEnum){

        this.getSysRoleModelById(sysRoleRequest.getId());
        SysRoleModel sysRoleModelParam = new SysRoleModel();
        sysRoleModelParam.setId(sysRoleRequest.getId());
        if (!StringUtils.isEmpty(sysRoleRequest.getRoleName())){
            sysRoleModelParam.setRoleName(sysRoleRequest.getRoleName());
        }
        if (sysRoleRequest.getRoleUsing() != null){
            sysRoleModelParam.setRoleUsing(sysRoleRequest.getRoleUsing());
        }
        if (statusEnum != null){
            sysRoleModelParam.setStatus(statusEnum.getStatus());
        }
        sysRoleModelParam.setUpdateTime(LocalDateTime.now());
        return super.updateById(sysRoleModelParam);
    }


    @Override
    public Boolean deleteSysRole(SysRoleRequest sysRoleRequest){
        SysRoleRequest sysRoleModelParam = new SysRoleRequest();
        sysRoleModelParam.setId(sysRoleRequest.getId());
        return this.updateSysRole(sysRoleRequest, StatusEnum.INVALIDATE);
    }


    @Override
    public SysRoleVo getSysRoleById(Integer roleId){
        SysRoleModel sysRoleModel = this.getSysRoleModelById(roleId);
        return this.packageSysRoleVo(sysRoleModel);
    }


    @Override
    public ListCountVo listSysRole(SysRoleQueryRequest sysRoleQueryRequest){

        PageHelper.startPage(sysRoleQueryRequest.getPageNumber(), sysRoleQueryRequest.getPageSize());
        LambdaQueryWrapper<SysRoleModel> lambdaQueryWrapper = Wrappers.lambdaQuery(new SysRoleModel());
        lambdaQueryWrapper.eq(SysRoleModel::getStatus, StatusEnum.VALIDATE.getStatus());

        if (!StringUtils.isEmpty(sysRoleQueryRequest.getRoleName())) {
            lambdaQueryWrapper.like(SysRoleModel::getRoleName, sysRoleQueryRequest.getRoleName());
        }

        if (sysRoleQueryRequest.getRoleUsing() != null) {
            lambdaQueryWrapper.eq(SysRoleModel::getRoleUsing, sysRoleQueryRequest.getRoleUsing() == 1);
        }

        if (sysRoleQueryRequest.getRoleLock() != null) {
            lambdaQueryWrapper.eq(SysRoleModel::getRoleLock, sysRoleQueryRequest.getRoleLock() == 1);
        }

        List<SysRoleModel> sysRoleModelList = super.list(lambdaQueryWrapper);
        PageInfo<SysRoleModel> pageInfo = new PageInfo<>(sysRoleModelList);
        List<SysRoleVo> sysRoleVoList = new ArrayList<>(pageInfo.getPageSize());
        pageInfo.getList().forEach(e -> {
            SysRoleVo sysRoleVo = this.packageSysRoleVo(e);
            sysRoleVoList.add(sysRoleVo);
        });
        return new ListCountVo<>(sysRoleVoList, pageInfo.getTotal());
    }


    private List<SysRoleVo> commonListSysRole(LambdaQueryWrapper<SysRoleModel> lambdaQueryWrapper) {
        lambdaQueryWrapper.eq(SysRoleModel::getStatus, StatusEnum.VALIDATE.getStatus());
        lambdaQueryWrapper.eq(SysRoleModel::getRoleUsing, UsingEnum.USING.getType());
        List<SysRoleModel> sysRoleModelList = super.list(lambdaQueryWrapper);
        List<SysRoleVo> sysRoleVoList = new ArrayList<>(sysRoleModelList.size());
        sysRoleModelList.forEach(e -> sysRoleVoList.add(this.packageSysRoleVo(e)));
        return sysRoleVoList;
    }


    @Override
    public List<SysRoleVo> listSysRoleAll(){
        LambdaQueryWrapper<SysRoleModel> lambdaQueryWrapper = Wrappers.lambdaQuery(new SysRoleModel());
        lambdaQueryWrapper.eq(SysRoleModel::getStatus, StatusEnum.VALIDATE.getStatus());
        return this.commonListSysRole(lambdaQueryWrapper);
    }


    @Override
    public List<SysRoleVo> listSysRoleByIds(Set<Integer> roleIds){
        LambdaQueryWrapper<SysRoleModel> lambdaQueryWrapper = Wrappers.lambdaQuery(new SysRoleModel());
        lambdaQueryWrapper.in(SysRoleModel::getId, roleIds);
        lambdaQueryWrapper.eq(SysRoleModel::getStatus, StatusEnum.VALIDATE.getStatus());
        return this.commonListSysRole(lambdaQueryWrapper);
    }

}
