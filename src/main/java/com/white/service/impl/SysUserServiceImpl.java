package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysUserModel;
import com.white.bean.model.SysUserRoleModel;
import com.white.bean.request.SysLoginRequest;
import com.white.bean.request.SysUserPasswordRequest;
import com.white.bean.request.SysUserQueryRequest;
import com.white.bean.request.SysUserRequest;
import com.white.bean.vo.SimpleUserVo;
import com.white.bean.vo.SysLoginVo;
import com.white.constants.Constants;
import com.white.enums.LockEnum;
import com.white.enums.SexEnum;
import com.white.enums.StatusEnum;
import com.white.enums.WhiteExceptionEnum;
import com.white.exception.WhiteException;
import com.white.mapper.SysUserMapper;
import com.white.service.SysUserRoleService;
import com.white.service.SysUserService;
import com.white.util.CryptoUtil;
import com.white.util.JwtUtil;
import com.white.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户信息 服务实现类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserModel> implements SysUserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * userModel to simpleUserModel
     * @param sysUserModel
     * @return
     */
    private SimpleUserVo packageSimpleUser(SysUserModel sysUserModel) {
        SimpleUserVo simpleUserVo = new SimpleUserVo();
        simpleUserVo.setId(sysUserModel.getId());
        simpleUserVo.setUserName(sysUserModel.getUserName());
        simpleUserVo.setName(sysUserModel.getName());
        simpleUserVo.setPicUrl(sysUserModel.getPicUrl());
        simpleUserVo.setSex(sysUserModel.getSex());
        simpleUserVo.setUserUsing(sysUserModel.getUserUsing());
        simpleUserVo.setUserLock(sysUserModel.getUserLock());
        simpleUserVo.setCreateTime(sysUserModel.getCreateTime());
        simpleUserVo.setUpdateTime(sysUserModel.getUpdateTime());
        return simpleUserVo;
    }


    @Override
    public SysLoginVo login(SysLoginRequest sysLoginRequest) {

        Wrapper<SysUserModel> wrapper = Wrappers.lambdaQuery(new SysUserModel())
                .eq(SysUserModel::getUserName, sysLoginRequest.getUserName());

        SysUserModel sysUserModel = super.getOne(wrapper);
        ValidateUtil.isTrue(sysUserModel == null, WhiteExceptionEnum.USER_NOT_EXISTS);
        ValidateUtil.isTrue(!sysUserModel.getUserUsing(), WhiteExceptionEnum.USER_FORBIDDEN);
        ValidateUtil.isTrue(!sysUserModel.getStatus(), WhiteExceptionEnum.USER_INVALIDATE);

        ValidateUtil.isTrue(!CryptoUtil.verifyPassword(sysUserModel.getPassword(), sysLoginRequest.getPassword(), sysUserModel.getSalt()),
                WhiteExceptionEnum.USER_LOGIN_PASSWORD_ERROR);
        List<SysUserRoleModel> sysUserRoleModelList = sysUserRoleService.getSysUserRoleModel(sysUserModel.getId());
        Set<Integer> roleIdSet = sysUserRoleModelList.stream().map(SysUserRoleModel::getRoleId).collect(Collectors.toSet());

        String token = jwtUtil.generateToken(sysUserModel.getUserName(), sysUserModel.getId());
        SimpleUserVo simpleUserVo = this.packageSimpleUser(sysUserModel);
        SysLoginVo sysLoginVo = new SysLoginVo();
        sysLoginVo.setToken(token);
        sysLoginVo.setSimpleUser(simpleUserVo);
        sysLoginVo.setRoleIdList(roleIdSet);

        Constants.sessionMap.put(token, simpleUserVo);
        System.out.println(Constants.sessionMap);
        return sysLoginVo;
    }



    @Override
    public Boolean addUser(SysUserRequest sysUserRequest){
        Wrapper<SysUserModel> wrapper = Wrappers.lambdaQuery(new SysUserModel())
                .eq(SysUserModel::getUserName, sysUserRequest.getUserName());
        SysUserModel sysUserModelParam = super.getOne(wrapper);

        ValidateUtil.isTrue(sysUserModelParam != null, WhiteExceptionEnum.USER_EXISTS);

        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setId(null);
        sysUserModel.setUserName(sysUserRequest.getUserName());
        sysUserModel.setSalt(CryptoUtil.createRandomSalt());
        sysUserModel.setName(sysUserRequest.getName());
        sysUserModel.setPassword(CryptoUtil.hashPassword(sysUserRequest.getPassword(), sysUserModel.getSalt()));
        if (StringUtils.isEmpty(sysUserRequest.getName())){
            sysUserModel.setName(sysUserModel.getUserName());
        }
        sysUserModel.setPicUrl("http://t.cn/RCzsdCq");
        sysUserModel.setSex(sysUserRequest.getSex() != null ? sysUserRequest.getSex() : SexEnum.UNKNOWN.getType());
        sysUserModel.setUserUsing(sysUserRequest.getUserUsing());
        sysUserModel.setUserLock(LockEnum.EDIT.getType());
        sysUserModel.setStatus(StatusEnum.VALIDATE.getStatus());
        sysUserModel.setUpdateTime(LocalDateTime.now());
        sysUserModel.setCreateTime(LocalDateTime.now());
        return this.save(sysUserModel);
    }


    /**
     * 获取有效的用户信息
     * @param userId
     * @return
     */
    private SysUserModel getSysUserModelById(Integer userId){
        SysUserModel sysUserModelParam = super.getById(userId);
        ValidateUtil.isTrue(sysUserModelParam == null, WhiteExceptionEnum.USER_NOT_EXISTS);
        ValidateUtil.isTrue(!sysUserModelParam.getStatus(), WhiteExceptionEnum.USER_INVALIDATE);
        return sysUserModelParam;
    }

    @Override
    public Boolean updateUser(SysUserRequest sysUserRequest, StatusEnum statusEnum){

        SysUserModel sysUserModelParam = this.getSysUserModelById(sysUserRequest.getId());

        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setId(sysUserRequest.getId());

        if (!StringUtils.isEmpty(sysUserRequest.getPassword())) {
            sysUserModel.setPassword(CryptoUtil.hashPassword(sysUserRequest.getPassword(), sysUserModelParam.getSalt()));
        }
        if (!StringUtils.isEmpty(sysUserRequest.getName())) {
            sysUserModel.setName(sysUserRequest.getName());
        }
        if (!StringUtils.isEmpty(sysUserRequest.getPicUrl())) {
            sysUserModel.setPicUrl(sysUserRequest.getPicUrl());
        }

        if (sysUserRequest.getUserUsing() != null) {
            sysUserModel.setUserUsing(sysUserRequest.getUserUsing());
        }
        if (statusEnum != null) {
            sysUserModel.setStatus(statusEnum.getStatus());
        }
        sysUserModel.setSex(sysUserRequest.getSex() != null ? sysUserRequest.getSex() : SexEnum.UNKNOWN.getType());
        sysUserModel.setUpdateTime(LocalDateTime.now());
        return this.updateById(sysUserModel);
    }

    @Override
    public Boolean deleteUser(SysUserRequest sysUserRequest){
        SysUserModel sysUserModelParam = this.getSysUserModelById(sysUserRequest.getId());
        return super.removeById(sysUserModelParam.getId());
    }

    @Override
    public SimpleUserVo getUserById(Integer userId){
        SysUserModel sysUserModelParam = this.getSysUserModelById(userId);
        return this.packageSimpleUser(sysUserModelParam);
    }


    @Override
    public ListCountVo listUser(SysUserQueryRequest sysUserQueryRequest){
        return sysUserRoleService.listUserRole(sysUserQueryRequest);
    }

    @Override
    public boolean loginOut(String token) {
        SimpleUserVo simpleUserVo = Constants.get();
        Constants.sessionMap.remove(token);
        return true;
    }


    @Override
    public boolean modifyPassword(SysUserPasswordRequest sysUserPasswordRequest) {
        SimpleUserVo simpleUserVo = Constants.get();
        ValidateUtil.isTrue(simpleUserVo == null, WhiteExceptionEnum.HEADER_TOKEN_ERROR);

        SysUserModel sysUserModel = super.getById(simpleUserVo.getId());
        ValidateUtil.isTrue(sysUserModel == null, WhiteExceptionEnum.USER_NOT_EXISTS);

        ValidateUtil.isTrue(!CryptoUtil.verifyPassword(sysUserModel.getPassword(), sysUserPasswordRequest.getRawPassword(), sysUserModel.getSalt()),
                WhiteExceptionEnum.USER_LOGIN_PASSWORD_ERROR);

        SysUserModel sysUserModelNew = new SysUserModel();
        sysUserModelNew.setId(simpleUserVo.getId());
        sysUserModelNew.setSalt(CryptoUtil.createRandomSalt());
        sysUserModelNew.setPassword(CryptoUtil.hashPassword(sysUserPasswordRequest.getNewPassword(), sysUserModelNew.getSalt()));

        return super.updateById(sysUserModelNew);
    }

}
