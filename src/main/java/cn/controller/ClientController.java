package cn.controller;


import cn.domain.PageBean;
import cn.po.Products;
import cn.po.User;
import cn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 * client用的controller
 */
@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    //用户注册
    @RequestMapping("register")
    public String register(HttpSession session, User user,HttpServletResponse response) throws IOException {
        String code = (String) session.getAttribute("CHECKCODE_SERVER");
        if(!(user.getActiviecode().equals(code))){
            return "/client/register.jsp";
        }
        //用户名不允许重复
        if (clientService.checkUsername(user.getUsername())){
            System.out.println("2");
            return "/client/register.jsp";
        }
        clientService.register(user);
        return "/client/registersuccess.jsp";
    }


    //分页查询
    @RequestMapping("showProductByPage")
    public String showProductByPage(Integer user_currentPage, Integer user_currentCount, String user_category, Model model){
        int currentPage = 1;//当前页面默认为1
        int currentCount = 4;//每页显示商品个数默认为4
        String category = "全部商品";//默认商品种类
        if(user_currentPage!=null){
            currentPage = user_currentPage;
        }
        if(user_currentCount != null){
            currentCount = user_currentCount;
        }
        if(user_category != null){
            category = user_category;
        }
        //查询商品
        PageBean<Products> products = clientService.finProductByPage(currentPage, currentCount, category);
        //封装
        model.addAttribute("bean", products);
        return "/client/product_list.jsp";
    }
    //用户登陆
    @RequestMapping("login")
    public String login(String username, String password, String remember, String autologin, Model model, HttpServletResponse response) {
        User user = clientService.login(username, password);
        if (user == null) {
            model.addAttribute("register_message", "账号不存在");
        }else if (user.getState().equals("登陆")) {
            //判断是否记住用户和自动登陆
            if(remember != null && remember.equals("checkbox01")){
                Cookie ucook = new Cookie("username",username);
                Cookie pcook = new Cookie("password",password);
                if(autologin != null && autologin.equals("checkbox02")){
                    Cookie scook = new Cookie("autologin","true");
                    response.addCookie(scook);
                }
                response.addCookie(ucook);
                response.addCookie(pcook);
            }
            //判断登陆角色来决定登陆成功显示的页面
            if (user.getRole().equals("管理员")) {
                return "/admin/login/home.jsp";
            } else if (user.getRole().equals("普通用户")) {
                return "/client/myAccount.jsp";
            }
        }else {
            model.addAttribute("register_message","账号或密码错误");
        }
        return "/client/login.jsp";
    }


    //显示验证码
    @RequestMapping("imageCode")
    public void imageCode(HttpServletResponse response , HttpSession session) throws IOException {
//设置验证码的大小
        int width = 100;
        int height = 50;
        StringBuffer code = new StringBuffer();

        //在内存中创建验证么图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //美化图片
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);

        //画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width-1, height-1);

        //写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random r = new Random();
        for(int i = 1;i <= 4; i++){
            int index = r.nextInt(str.length());
            char c = str.charAt(index);
            code.append(c);
            g.drawString(c+"", width/5*i, height/2);
        }

//        //增加干扰线
//        g.setColor(Color.green);
//        for(int i = 0; i < 10; i++){
//            int x1 = r.nextInt(width);
//            int x2 = r.nextInt(width);
//            int y1 = r.nextInt(height);
//            int y2 = r.nextInt(height);
//            g.drawLine(x1, y1,x2, y2);
//        }

        //将图片输出
        ImageIO.write(image, "jpg",response.getOutputStream());

        //将验证码存入session
        String checkcode = code.toString();
        session.setAttribute("CHECKCODE_SERVER", checkcode);
    }
}
