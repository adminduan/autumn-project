package com.white.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.white.bean.common.ListCountVo;
import com.white.bean.model.SysMenuModel;
import com.white.bean.model.SysUserRoleModel;
import com.white.bean.model.ext.SysMenuQueryExtModel;
import com.white.bean.request.SysMenuQueryRequest;
import com.white.bean.request.SysMenuRequest;
import com.white.bean.vo.SimpleUserVo;
import com.white.bean.vo.SysMenuVo;
import com.white.constants.Constants;
import com.white.enums.MenuTypeEnum;
import com.white.enums.UsingEnum;
import com.white.enums.StatusEnum;
import com.white.enums.WhiteExceptionEnum;
import com.white.mapper.SysMenuMapper;
import com.white.service.SysMenuService;
import com.white.service.SysRoleMenuService;
import com.white.service.SysUserRoleService;
import com.white.util.ValidateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuModel> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    private SysMenuVo packageSysMenuVo(SysMenuModel sysMenuModel){
        SysMenuVo sysMenuVo = new SysMenuVo();
        sysMenuVo.setId(sysMenuModel.getId());
        sysMenuVo.setParentId(sysMenuModel.getParentId());
        sysMenuVo.setMenuName(sysMenuModel.getMenuName());
        sysMenuVo.setMenuUrl(sysMenuModel.getMenuUrl());
        sysMenuVo.setMenuPic(sysMenuModel.getMenuPic());
        sysMenuVo.setMenuUsing(sysMenuModel.getMenuUsing());
        sysMenuVo.setUpdateTime(sysMenuModel.getUpdateTime());
        sysMenuVo.setCreateTime(sysMenuModel.getCreateTime());
        return sysMenuVo;
    }

    @Override
    public Boolean addSysMenu(SysMenuRequest sysMenuRequest) {
        SysMenuModel sysMenuModel = new SysMenuModel();
        BeanUtils.copyProperties(sysMenuRequest, sysMenuModel);
        sysMenuModel.setId(null);
        sysMenuModel.setStatus(StatusEnum.VALIDATE.getStatus());
        sysMenuModel.setMenuType(MenuTypeEnum.PARENT.getStatus());
        sysMenuModel.setMenuUsing(UsingEnum.USING.getType());
        //设置为空值
        sysMenuModel.setMenuPic("");
        sysMenuModel.setCreateTime(LocalDateTime.now());
        sysMenuModel.setUpdateTime(LocalDateTime.now());
        return super.save(sysMenuModel);
    }

    @Override
    public Boolean updateSysMenu(SysMenuRequest sysMenuRequest) {

        this.getSysMenuModelById(sysMenuRequest.getId());

        SysMenuModel sysMenuModelParam = new SysMenuModel();
        sysMenuModelParam.setId(sysMenuRequest.getId());
        sysMenuModelParam.setParentId(sysMenuRequest.getParentId());
        sysMenuModelParam.setMenuName(sysMenuRequest.getMenuName());
        sysMenuModelParam.setMenuUrl(sysMenuRequest.getMenuUrl());
        sysMenuModelParam.setMenuPic("");
        sysMenuModelParam.setMenuUsing(sysMenuRequest.getMenuUsing());
        sysMenuModelParam.setUpdateTime(LocalDateTime.now());
        return super.updateById(sysMenuModelParam);
    }


    private SysMenuModel getSysMenuModelById(Integer id){
        SysMenuModel sysMenuModel = super.getById(id);
        ValidateUtil.isTrue(sysMenuModel == null, WhiteExceptionEnum.DATA_NOT_FOUND);
        ValidateUtil.isTrue(!sysMenuModel.getStatus(), WhiteExceptionEnum.DATA_EXPIRE);
        return sysMenuModel;
    }

    private List<SysMenuModel> getSysMenuModelByParentId(Integer parentId){
        LambdaQueryWrapper<SysMenuModel> lambdaQueryWrapper =
                Wrappers.lambdaQuery(new SysMenuModel())
                        .eq(SysMenuModel::getStatus, StatusEnum.VALIDATE.getStatus())
                        .eq(SysMenuModel::getParentId, parentId);
        List<SysMenuModel> sysMenuModelList = super.list(lambdaQueryWrapper);
        return sysMenuModelList;
    }

    @Override
    public Boolean deleteSysMenu(SysMenuRequest sysMenuRequest, StatusEnum statusEnum){

        SysMenuModel sysMenuModel = this.getSysMenuModelById(sysMenuRequest.getId());
        //查看菜单下是否有子菜单
        List<SysMenuModel> sysMenuModelList = this.getSysMenuModelByParentId(sysMenuRequest.getId());
        ValidateUtil.isTrue(!CollectionUtils.isEmpty(sysMenuModelList), WhiteExceptionEnum.MENU_CAN_NOT_DELETE);

        SysMenuModel sysMenuModelParam = new SysMenuModel();
        sysMenuModelParam.setId(sysMenuModel.getId());
        sysMenuModelParam.setStatus(statusEnum.getStatus());
        sysMenuModelParam.setUpdateTime(LocalDateTime.now());
        return super.updateById(sysMenuModelParam);
    }


    @Override
    public SysMenuVo getSysMenuVoById(Integer id){
        SysMenuModel sysMenuModel = this.getSysMenuModelById(id);
        SysMenuVo sysMenuVo = new SysMenuVo();
        BeanUtils.copyProperties(sysMenuModel, sysMenuVo);
        return sysMenuVo;
    }



    @Override
    public ListCountVo listSysMenu(SysMenuQueryRequest sysMenuQueryRequest) {
        PageHelper.startPage(sysMenuQueryRequest.getPageNumber(), sysMenuQueryRequest.getPageSize());
        List<SysMenuQueryExtModel> result = sysMenuMapper.listSysMenu(sysMenuQueryRequest);
        PageInfo<SysMenuQueryExtModel> pageInfo = new PageInfo<>(result);
        return new ListCountVo<>(pageInfo.getList(), pageInfo.getTotal());
    }


    @Override
    public List<SysMenuVo> listUserSysMenu() {
        List<SysMenuVo> sysMenuVoList = new ArrayList<>();

        SimpleUserVo simpleUserVo = Constants.get();
        List<SysUserRoleModel> sysUserRoleModelList = sysUserRoleService.getSysUserRoleModel(simpleUserVo.getId());
        Set<Integer> roleIdSet = sysUserRoleModelList.stream().map(SysUserRoleModel::getRoleId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(roleIdSet)) {
            return sysMenuVoList;
        }

        //根据角色id获取菜单列表
        Set<Integer> menuIdSet = sysRoleMenuService.listMenuIdByRoleIdList(roleIdSet);
        if (CollectionUtils.isEmpty(menuIdSet)) {
            return sysMenuVoList;
        }

//        LambdaQueryWrapper<SysMenuModel> lambdaQueryWrapper = Wrappers.lambdaQuery(new SysMenuModel())
//                        .eq(SysMenuModel::getStatus, StatusEnum.VALIDATE.getStatus())
//                        .eq(SysMenuModel::getMenuUsing, UsingEnum.USING.getType());
//        List<SysMenuModel> list = super.list(lambdaQueryWrapper);
        List<SysMenuModel> list = this.listSysMenuModelBymenuIdSet(menuIdSet);

        if (!CollectionUtils.isEmpty(list)) {
            Map<Integer, List<SysMenuModel>> groupMapResult =
                    list.stream().collect(Collectors.groupingBy(SysMenuModel::getParentId, Collectors.toList()));
            groupMapResult.get(Constants.PARENT_ID).forEach(e -> {
                SysMenuVo sysMenuVo = new SysMenuVo();
                BeanUtils.copyProperties(e, sysMenuVo);

                List<SysMenuModel> sysMenuModeChildList = groupMapResult.get(e.getId());
                List<SysMenuVo> sysMenuVoChildList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(sysMenuModeChildList)) {
                    sysMenuModeChildList.forEach(c -> {
                        SysMenuVo sysMenuVoChild = new SysMenuVo();
                        BeanUtils.copyProperties(c, sysMenuVoChild);
                        sysMenuVoChildList.add(sysMenuVoChild);
                    });
                }

                sysMenuVo.setChildSysMenu(sysMenuVoChildList);
                sysMenuVoList.add(sysMenuVo);
            });
        }
        return sysMenuVoList;
    }



    @Override
    public List<SysMenuVo> listParentSysMenu(Integer parentId){
        LambdaQueryWrapper<SysMenuModel> lambdaQueryWrapper =
                Wrappers.lambdaQuery(new SysMenuModel())
                        .eq(SysMenuModel::getStatus, StatusEnum.VALIDATE.getStatus())
                        .eq(SysMenuModel::getMenuUsing, UsingEnum.USING.getType())
                        .eq(SysMenuModel::getParentId, parentId);
        List<SysMenuModel> list = super.list(lambdaQueryWrapper);

        List<SysMenuVo> sysMenuVoList = new ArrayList<>(list.size());
        list.forEach(e -> sysMenuVoList.add(this.packageSysMenuVo(e)));
        return sysMenuVoList;
    }


    @Override
    public List<SysMenuVo> listSysMenuBymenuIdSet(Set<Integer> menuIdSet) {
        List<SysMenuModel> list = this.listSysMenuModelBymenuIdSet(menuIdSet);
        List<SysMenuVo> sysMenuVoList = new ArrayList<>(list.size());
        list.forEach(e -> sysMenuVoList.add(this.packageSysMenuVo(e)));
        return sysMenuVoList;
    }


    private List<SysMenuModel> listSysMenuModelBymenuIdSet(Set<Integer> menuIdSet) {
        LambdaQueryWrapper<SysMenuModel> lambdaQueryWrapper =
                Wrappers.lambdaQuery(new SysMenuModel())
                        .eq(SysMenuModel::getStatus, StatusEnum.VALIDATE.getStatus())
                        .eq(SysMenuModel::getMenuUsing, UsingEnum.USING.getType())
                        .in(SysMenuModel::getId, menuIdSet);
        return super.list(lambdaQueryWrapper);
    }
}
