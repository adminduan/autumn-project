package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysRoleMenuModel;
import com.white.bean.model.SysUserRoleModel;
import com.white.bean.model.ext.SysUserRoleQueryExtModel;
import com.white.bean.model.ext.request.SysUserRoleQueryExtRequest;
import com.white.bean.request.SysUserQueryRequest;
import com.white.bean.request.SysUserRoleRequest;
import com.white.bean.vo.SimpleUserVo;
import com.white.bean.vo.SysRoleVo;
import com.white.enums.StatusEnum;
import com.white.enums.WhiteExceptionEnum;
import com.white.mapper.SysUserRoleMapper;
import com.white.service.SysRoleService;
import com.white.service.SysUserRoleService;
import com.white.service.SysUserService;
import com.white.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户角色关联信息 服务实现类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleModel> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<SysUserRoleModel> getSysUserRoleModel(Integer userId) {
        LambdaQueryWrapper<SysUserRoleModel> wrapper = Wrappers.lambdaQuery(new SysUserRoleModel());
        wrapper.eq(SysUserRoleModel::getStatus, StatusEnum.VALIDATE.getStatus());
        wrapper.eq(SysUserRoleModel::getUserId, userId);
        return super.list(wrapper);
    }

    @Override
    public ListCountVo listUserRole(SysUserQueryRequest sysUserQueryRequest){
        SysUserRoleQueryExtRequest sysUserRoleQueryExtRequest = new SysUserRoleQueryExtRequest();
        if (StringUtils.isEmpty(sysUserQueryRequest)){
            sysUserRoleQueryExtRequest.setUserName(sysUserQueryRequest.getUserName());
        }
        if (sysUserQueryRequest.getSex() != null){
            sysUserRoleQueryExtRequest.setSex(sysUserQueryRequest.getSex());
        }
        if (sysUserQueryRequest.getUserLock() != null){
            sysUserRoleQueryExtRequest.setUserLock(sysUserQueryRequest.getUserLock());
        }
        if (sysUserQueryRequest.getUserUsing() != null){
            sysUserRoleQueryExtRequest.setUserUsing(sysUserQueryRequest.getUserUsing());
        }
        PageHelper.startPage(sysUserQueryRequest.getPageNumber(), sysUserQueryRequest.getPageSize());
        List<SysUserRoleQueryExtModel> sysUserRoleQueryExtModelList =  sysUserRoleMapper.listUserRole(sysUserRoleQueryExtRequest);
        PageInfo<SysUserRoleQueryExtModel> pageInfo = new PageInfo<>(sysUserRoleQueryExtModelList);
        return new ListCountVo<>(pageInfo.getList(), pageInfo.getTotal());
    }


    @Override
    public List<SysRoleVo> listSysRoleByUserId(Integer userId){
        List<SysUserRoleModel> sysUserRoleModelList = this.getSysUserRoleModel(userId);
        Set<Integer> roleIdSet = sysUserRoleModelList.stream().map(SysUserRoleModel::getRoleId).collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(roleIdSet)){
            return sysRoleService.listSysRoleByIds(roleIdSet);
        }
        return new ArrayList<>();
    }


    @Override
    public Boolean addSysUserRole(SysUserRoleRequest sysUserRoleRequest){

        SimpleUserVo simpleUserVo = sysUserService.getUserById(sysUserRoleRequest.getUserId());
        ValidateUtil.isTrue(simpleUserVo == null, WhiteExceptionEnum.DATA_NOT_FOUND);
        //查看角色是否存在
        List<SysRoleVo> sysRoleVoList = sysRoleService.listSysRoleByIds(sysUserRoleRequest.getRoleIdSet());
        ValidateUtil.isTrue(sysRoleVoList.size() != sysUserRoleRequest.getRoleIdSet().size(), WhiteExceptionEnum.DATA_EXPIRE);
        Set<Integer> requestRoleId = sysRoleVoList.stream().map(SysRoleVo::getId).collect(Collectors.toSet());

        List<SysUserRoleModel> sysUserRoleModelList = this.getSysUserRoleModel(sysUserRoleRequest.getUserId());
        Set<Integer> existRoleId = sysUserRoleModelList.stream().map(SysUserRoleModel::getRoleId).collect(Collectors.toSet());

        //删除多余的角色
        Set<Integer> deleteRoleId = new HashSet<>(existRoleId);
        deleteRoleId.removeAll(requestRoleId);
        if (!CollectionUtils.isEmpty(deleteRoleId)) {
            LambdaQueryWrapper lambdaQueryWrapper = Wrappers.lambdaQuery(new SysUserRoleModel())
                    .eq(SysUserRoleModel::getUserId, sysUserRoleRequest.getUserId())
                    .in(SysUserRoleModel::getRoleId, deleteRoleId);
            ValidateUtil.isTrue(!super.remove(lambdaQueryWrapper), WhiteExceptionEnum.FAIL);
        }

        //新增不存在的角色
        Set<Integer> addRoleId = new HashSet<>(requestRoleId);
        addRoleId.removeAll(existRoleId);

        if (!CollectionUtils.isEmpty(addRoleId)) {
            for (Integer roleId : addRoleId) {
                SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
                sysUserRoleModel.setUserId(sysUserRoleRequest.getUserId());
                sysUserRoleModel.setRoleId(roleId);
                sysUserRoleModel.setStatus(StatusEnum.VALIDATE.getStatus());
                sysUserRoleModel.setUpdateTime(LocalDateTime.now());
                sysUserRoleModel.setCreateTime(LocalDateTime.now());
                ValidateUtil.isTrue(!super.save(sysUserRoleModel), WhiteExceptionEnum.FAIL);
            }
        }

        return true;
    }


}
