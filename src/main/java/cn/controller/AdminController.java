package cn.controller;

import cn.po.Orders;
import cn.po.Products;
import cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

/**
 * admin的controller
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 查询所有商品
     * @param model
     * @return
     */
    @RequestMapping("/listProduct")
    public String listProduct(Model model){
        List<Products> list = adminService.findAll();
        model.addAttribute("ps", list);
        return "/admin/products/list.jsp";
    }

    /**
     * 按条件查询所有商品
     * @param id
     * @param name
     * @param category
     * @param minprice
     * @param maxprice
     * @param model
     * @return
     */
    @RequestMapping("/findProductByManyCondition")
    public String findProductByManyCondition(Integer id, String name, String category, Integer minprice, Integer maxprice, Model model){
        //将空值转换为null以便查询
        if(name == ""){
            name = null;
        }
        if(category == ""){
            category = null;
        }
        List<Products> list = adminService.findProductByManyCondition(id, name, category, minprice, maxprice);
        model.addAttribute("ps",list);
        return "/admin/products/list.jsp";
    }

    /**
     * 上传商品
     * @param upload
     * @param product
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/addProduct")
    public String addProduct(MultipartFile upload, Products product, HttpServletRequest request) throws IOException {
        //文件保存路径
        String path = request.getSession().getServletContext().getRealPath("/client/bookcover/");
        //数据库保存路径
        String dataUrl = "/client/bookcover/";
        //获取文件名
        String fileName = upload.getOriginalFilename();
        //完成上传
        upload.transferTo(new File(path,fileName));
        //拼接url
        dataUrl += fileName;
        product.setImgurl(dataUrl);
        //保存信息
        Boolean result = adminService.addProduct(product);
        if(result == false){
            //返回错误信息,但由于后期进行改造所以省略
        }
        return "/listProduct";
    }


    /**
     * 删除商品
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("deleteProduct")
    public String deleteProduct(int id,HttpServletRequest request){
        //删除图片
        Products products = adminService.findProductById(id);
        String imgUrl = products.getImgurl();
        String url = request.getSession().getServletContext().getRealPath("");
        url = url + imgUrl;
        File imgFile = new File(url);
        imgFile.delete();
        //删除操作
        adminService.deleteProductById(id);
        return "/listProduct";
    }

    /**
     * 按id查询
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("adminfindProductById")
    public String findProductById(int id,Model model){
        Products products = adminService.findProductById(id);
        model.addAttribute("p",products);
        return "/admin/products/edit.jsp";
    }

    /**
     * 修改商品
     * @param upload
     * @param product
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("editProduct")
    public String editProduct(MultipartFile upload,Products product,HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/client/bookcover/");
        //数据库保存路径
        String dataUrl = "/client/bookcover/";
        //获取文件名
        String fileName = upload.getOriginalFilename();
        //完成上传
        upload.transferTo(new File(path,fileName));
        //拼接url
        dataUrl += fileName;
        product.setImgurl(dataUrl);
        //更新
        adminService.editProduct(product);
        return "/listProduct";
    }

    /**
     *  榜单下载（未测试）
     * @param year
     * @param month
     * @param response
     * @param servletContext
     * @throws IOException
     */
    @RequestMapping("/download")
    public void download(String year, String month, HttpServletResponse response, ServletContext servletContext) throws IOException {
        //查询销售数据
        List<Object[]> ps = adminService.download(year, month);

        //拼接文件名
        String fileName = year + "年" + month + "月销售榜单";
        response.setContentType(servletContext.getMimeType(fileName));

        //设置文件名
        response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("GBK"),"iso8859-1"));
        response.setCharacterEncoding("gbk");

        //写入数据
        PrintWriter out = response.getWriter();
        for(int i = 0; i < ps.size(); i++){
            Object[] arr = ps.get(i);
            out.println(arr[0]+","+arr[i]);
        }
        out.flush();
        out.close();
    }

    /**
     * 订单查询
     * @param model
     * @return
     */
    @RequestMapping("/findOrders")
    public String findOrders(Model model){
        List<Orders> orders = adminService.findOrders();
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    /**
     * 订单按条件查询
     * @param model
     * @param id
     * @param receiverName
     * @return
     */
    @RequestMapping("/findOrderByManyCondition")
    public String findOrderByManyCondition(Model model,String id, String receiverName){

        List<Orders> orders = adminService.findOrderByManyCondition(id, receiverName);
        model.addAttribute("orders",orders);
        return "/admin/orders/list.jsp";
    }

    /**
     *  订单按id查询
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findOrderById")
    public String findOrderById(String id, Model model){
        Orders order = adminService.findOrderById(id);
        model.addAttribute("order",order);
        return "/admin/orders/view.jsp";
    }

    /**
     * 订单删除
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("/delOrderById")
    public String delOrderById(String id,String type){
        if(type != null && type.trim().length() > 0){
            adminService.delOrderById(id);
        }
        return "/findorders";
    }
}
