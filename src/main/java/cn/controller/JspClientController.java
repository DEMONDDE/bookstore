package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于跳转jsp页面
 */
@Controller
@RequestMapping("/client")
public class JspClientController {

    @RequestMapping("/register")
    public String toRegister(){
        return "/client/register.jsp";
    }

    @RequestMapping("/registersuccess")
    public String toRegistersuccess(){
        return "/client/registersuccess.jsp";
    }
}
