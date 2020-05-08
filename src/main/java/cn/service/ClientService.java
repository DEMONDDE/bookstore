package cn.service;


import cn.domain.PageBean;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;

public interface ClientService {
    //检测用户名是否重复
    public Boolean checkUsername(String name);
    //注册用户
    public void register(User user);
    //用户登陆
    public User login(String username, String password);

    //进行分页查询
    public PageBean<Products> finProductByPage(int currentPage, int currentCount, String category);

    //通过id查找商品信息
    public Products findProductById(int id);

    //创建订单
    public void addOrder(Orders order);

    //搜索图书
    PageBean<Products> findProductByName(int currentCount, int currentPage, String textfield);
}
