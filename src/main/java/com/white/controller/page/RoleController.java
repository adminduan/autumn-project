package com.white.controller.page;


import com.white.bean.vo.SysRoleVo;
import com.white.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 角色 controller 页面跳转
 * @author duanlsh
 */
@RequestMapping("/role/page")
@Controller
public class RoleController {


    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/index")
    public String roleIndex() {
        return "role/role";
    }

    /**
     * 跳转 菜单修改页面
     * @return
     */
    @RequestMapping("/edit-role/{id}")
    public String editRole(@PathVariable("id") Integer id, Model model) {
        SysRoleVo sysRoleVo = sysRoleService.getSysRoleById(id);
        model.addAttribute("roleVo", sysRoleVo);
        return "role/edit-role";
    }

    /**
     * 新增菜单
     * @return
     */
    @RequestMapping("/add-role")
    public String addRole() {
        return "role/add-role";
    }
}
