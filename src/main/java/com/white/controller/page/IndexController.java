package com.white.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author k
 * @description TODO
 * @date 2019/8/9
 */
@RequestMapping("/")
@Controller
public class IndexController {


    @GetMapping("/")
    public String login(){
        return "login";
    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
