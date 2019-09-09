package com.white.controller;


import com.white.bean.common.ListCountVo;
import com.white.bean.common.ResponseListVo;
import com.white.bean.common.ResponseVo;
import com.white.bean.request.SysMenuQueryRequest;
import com.white.bean.request.SysMenuRequest;
import com.white.enums.StatusEnum;
import com.white.service.SysMenuService;
import com.white.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author duanlsh
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 新增菜单信息
     * @param sysMenuRequest
     * @return
     */
    @PostMapping("/add-sys-menu")
    public ResponseVo addSysMenu(@RequestBody SysMenuRequest sysMenuRequest){
        return sysMenuService.addSysMenu(sysMenuRequest) ? ResponseUtil.success() : ResponseUtil.fail();
    }

    /**
     * 更新菜单信息
     * @param sysMenuRequest
     * @return
     */
    @PostMapping("/update-sys-menu")
    public ResponseVo updateSysMenu(@RequestBody SysMenuRequest sysMenuRequest){
        return sysMenuService.updateSysMenu(sysMenuRequest) ?
                ResponseUtil.success() : ResponseUtil.fail();
    }


    /**
     * 删除菜单信息
     * @param sysMenuRequest
     * @return
     */
    @PostMapping("/delete-sys-menu")
    public ResponseVo deleteSysMenu(@RequestBody SysMenuRequest sysMenuRequest){
        return sysMenuService.deleteSysMenu(sysMenuRequest, StatusEnum.INVALIDATE) ?
        ResponseUtil.success() : ResponseUtil.fail();
    }

    /**
     * 登录获取菜单列表
     * @return
     */
    @GetMapping("/list-user-sys-menu")
    public ResponseVo listUserSysMenu() {
        return ResponseUtil.success(sysMenuService.listUserSysMenu());
    }


    /**
     * 管理菜单类别
     * @param sysMenuQueryRequest
     * @return
     */
    @GetMapping("/list-sys-menu")
    public ResponseListVo listSysMenu(SysMenuQueryRequest sysMenuQueryRequest) {
        ListCountVo listCountVo = sysMenuService.listSysMenu(sysMenuQueryRequest);
        return new ResponseListVo(listCountVo.getList(), listCountVo.getCount());
    }

    /**
     * 获取父节点列表
     * @return
     */
    @GetMapping("/list-parent-sys-menu/{parentId}")
    public ResponseVo listParentSysMenu(@PathVariable("parentId") Integer parentId){
        return ResponseUtil.success(sysMenuService.listParentSysMenu(parentId));
    }
}

