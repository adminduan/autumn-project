package com.white.controller.page;


import com.white.bean.vo.SimpleUserVo;
import com.white.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author duanlsh
 * 调整用户页面
 */
@RequestMapping("/user/page")
@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String userIndex() {
        return "user/user";
    }


    /**
     * 新增用户页面
     * @return
     */
    @RequestMapping("/add-user")
    public String addUser() {
        return "user/add-user";
    }


    /**
     * 修改用户
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/edit-user/{userId}")
    public String editUser(@PathVariable("userId") Integer userId, Model model) {
        SimpleUserVo simpleUserVo = sysUserService.getUserById(userId);
        model.addAttribute("user", simpleUserVo);
        return "user/edit-user";
    }

    /**
     * 用户详情
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/user-info/{userId}")
    public String userInfo(@PathVariable("userId") Integer userId, Model model) {
        SimpleUserVo simpleUserVo = sysUserService.getUserById(userId);
        model.addAttribute("user", simpleUserVo);
        return "user/user-info";
    }

    /**
     * 用户分配角色
     * @return
     */
    @RequestMapping("/user-transfer-role/{userId}")
    public String menuTransferRole(@PathVariable("userId") Integer userId, Model model){
        SimpleUserVo simpleUserVo = sysUserService.getUserById(userId);
        model.addAttribute("user", simpleUserVo);
        return "user/user-role";
    }

    /**
     * 修改用户密码
     * @return
     */
    @RequestMapping("/user-password")
    public String userPassword() {
        return "user/user-password";
    }
}
