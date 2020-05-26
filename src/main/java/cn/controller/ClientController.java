package cn.controller;


import cn.domain.ActivateCode;
import cn.domain.PageBean;
import cn.po.OrderItem;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;
import cn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;


/**
 * client用的controller
 */
@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    //用户注册
    @RequestMapping("register")
    @ResponseBody
    public String register(HttpSession session, User user,HttpServletResponse response) throws IOException {
        String code = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println(user.getActiviecode());
        if(!(user.getActiviecode().equalsIgnoreCase(code))){
            return "ERRORCode";
        }
        //用户名不允许重复
        if (clientService.checkUsername(user.getUsername())){
            return "ERRORName";
        }
        clientService.register(user);
        return "OK";
    }

    //公告版和本周热卖
    @RequestMapping("ShowIndexServlet")
    public String ShowIndexServlet(){
        return "/client/index.jsp";
    }

    //向购物车添加商品
    @RequestMapping("/addCart")
    public String addCartServlet(int id,HttpSession session){
        Products product = clientService.findProductById(id);
        //获取购物车
        Map<Products,Integer> cart =(Map<Products,Integer>) session.getAttribute("cart");
        //如果没有购物车则创建
         if(cart == null){
             cart = new HashMap<Products,Integer>();
         }
         //向购物车添加
        Integer count = cart.put(product,1);
         if(count != null){
             cart.put(product,count+1);
         }
         session.setAttribute("cart",cart);
        return "/client/cart.jsp";
    }

    //查询商品信息
    @RequestMapping("findProductById")
    public String findProductById(int id, Model model){
        Products product = clientService.findProductById(id);
        model.addAttribute("p", product);
        return "/client/info.jsp";
    }


    //删除购物车商品
    @RequestMapping("/changeCart")
    public String changeCart(int id, int count, HttpSession session){
        Map<Products,Integer> cart = (Map<Products,Integer>)session.getAttribute("cart");
        Products product = new Products();
        product.setId(id);
        if(count != 0){
            cart.put(product,count);
        }else {
            cart.remove(product);
        }
        return "/client/cart.jsp";
    }


    //分页查询
    @RequestMapping("/showProductByPage")
    public String showProductByPage(Integer currentPage, Integer currentCount, String category, Model model){
        int user_currentPage = 1;//当前页面默认为1
        int user_currentCount = 4;//每页显示商品个数默认为4
        String user_category = "全部商品";//默认商品种类
        if(currentPage!=null){
            user_currentPage = currentPage;
        }
        if(currentCount != null){
            user_currentCount = currentCount;
        }
        if(category != null){
            user_category = category;
        }
        //查询商品
        PageBean<Products> products = clientService.finProductByPage(user_currentPage, user_currentCount, user_category);
        products.setCategory(user_category);
        //封装
        model.addAttribute("bean", products);
        return "/client/product_list.jsp";
    }




    //用户登陆
    @RequestMapping("login")
    public String login(String username, String password, String remember, String autologin, Model model, HttpServletResponse response, HttpSession session) {
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
            //保存用户
            session.setAttribute("user",user);
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

    //存储订单
    @RequestMapping("createOrder")
    public String createOrderServlet(Orders order,HttpSession session){
        //获取用户
        User user = (User)session.getAttribute("user");
        //获取订单
        Map<Products,Integer> cart = (Map<Products,Integer>) session.getAttribute("cart");
        //封装订单
        String id = UUID.randomUUID().toString();

        order.setId(ActivateCode.getActivateCode());
        order.setUser_id(user.getId());
        order.setOrderTime(new Date());
        for(Products p : cart.keySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(order.getId());
            orderItem.setBuynum(cart.get(p));
            orderItem.setProduct_id(p.getId());
            order.getOrderitem().add(orderItem);
        }
        clientService.addOrder(order);
        return "/client/createOrderSuccess.jsp";
    }


    //图书搜索
    @RequestMapping("MenuSearchServlet")
    public String menuSearchServlet(Integer currentPage,Integer currentCount, String textfield, Model model){
        int user_currentPage = 1;//当前页面默认为1
        int user_currentCount = 4;//每页显示商品个数默认为4
        if(currentPage!=null){
            user_currentPage = currentPage;
        }
        if(currentCount != null){
            user_currentCount = currentCount;
        }
        if(textfield.equals("请输入书名")){
            return "/showProductByPage";
        }
        PageBean<Products> bean = clientService.findProductByName(user_currentCount,user_currentPage,textfield);
        model.addAttribute("bean",bean);
        return "/client/product_search_list.jsp";
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

    //转到order
    @RequestMapping("/client/order")
    public String toOrder(){
        return "/client/login.jsp";
    }


}
