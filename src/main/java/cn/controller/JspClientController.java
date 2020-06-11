package cn.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/mycount/modifyuserinfo")
    public String toModifyuserinfo(){
        return "/client/modifyuserinfo.jsp";
    }
    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request){
        String name = request.getParameter("name");
        return "/client/login.jsp?name = "+name;
    }

    @RequestMapping("/order")
    public String toOrder(){
        return "/client/order.jsp";
    }

    @RequestMapping("/myAccount")
    public String tomyAccount(){
        return "/client/myAccount.jsp";
    }
}
