package com.white.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.white.bean.model.SysMenuModel;
import com.white.bean.model.ext.SysMenuQueryExtModel;
import com.white.bean.request.SysMenuQueryRequest;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
public interface SysMenuMapper extends BaseMapper<SysMenuModel> {


    List<SysMenuQueryExtModel> listSysMenu(SysMenuQueryRequest sysMenuQueryRequest);
}
