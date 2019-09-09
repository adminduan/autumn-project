package com.white.controller.page;

import com.white.bean.vo.SysMenuVo;
import com.white.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/menu/page")
@Controller
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/index")
    public String menuIndex() {
        return "menu/menu";
    }

    /**
     * 跳转 菜单修改页面
     * @return
     */
    @RequestMapping("/edit-menu/{id}")
    public String editMenu(@PathVariable("id") Integer id, Model model) {
        SysMenuVo sysMenuVo = sysMenuService.getSysMenuVoById(id);
        model.addAttribute("menuObj", sysMenuVo);
        return "menu/edit-menu";
    }

    /**
     * 新增菜单
     * @return
     */
    @RequestMapping("/add-menu")
    public String addMenu() {
        return "menu/add-menu";
    }


    /**
     * 菜单分配角色
     * @return
     */
    @RequestMapping("/menu-transfer-role/{menuId}")
    public String menuTransferRole(@PathVariable("menuId") Integer menuId, Model model){
        SysMenuVo sysMenuVo = sysMenuService.getSysMenuVoById(menuId);
        model.addAttribute("menuObj", sysMenuVo);
        return "menu/menu-role";
    }
}
