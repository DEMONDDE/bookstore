package cn.controller;

import cn.po.Products;
import cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * admin的controller
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    //查询所有商品
    @RequestMapping("/listProduct")
    public String listProduct(Model model){
        List<Products> list = adminService.findAll();
        model.addAttribute("ps", list);
        return "/admin/products/list.jsp";
    }

    //按条件查询所有商品
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

    //上传商品(未检测)
    @RequestMapping("/addProduct")
    public String addProduct(MultipartFile upload, Products product, HttpServletRequest request) throws IOException {
        //文件保存路径
        String path = request.getSession().getServletContext().getRealPath("/client/bookcover/");
        //数据库保存路径
        String dataUrl = "/client/bookcover/";
        //获取文件名
        String fileName = upload.getName();
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


    //删除商品(未检测)
    @RequestMapping("deleteProduct")
    public String deleteProduct(int id){
        //删除操作
        adminService.deleteProductById(id);
        return "/listProduct";
    }

    //按id查询(未检测)
    @RequestMapping("findProductById")
    public String findProductById(int id,Model model){
        Products products = adminService.findProductById(id);
        model.addAttribute("p",products);
        return "/admin/products/edit.jsp";
    }
    //修改商品(未检测)
    @RequestMapping("editProduct")
    public String editProduct(MultipartFile upload,Products product,HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/client/bookcover/");
        //数据库保存路径
        String dataUrl = "/client/bookcover/";
        //获取文件名
        String fileName = upload.getName();
        //完成上传
        upload.transferTo(new File(path,fileName));
        //拼接url
        dataUrl += fileName;
        product.setImgurl(dataUrl);
        //更新
        adminService.editProduct(product);
        return "/listProduct";
    }

}
