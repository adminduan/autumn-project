package com.white.bean.model.ext;

import com.white.bean.model.SysMenuModel;
import lombok.Data;

/**
 * @author duanlsh
 * @description 获取菜单列表对象
 * @date 2019/8/12
 */
@Data
public class SysMenuQueryExtModel extends SysMenuModel {

    private String parentName;
}
