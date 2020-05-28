package cn.service;


import cn.domain.PageBean;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;

import java.util.List;

public interface ClientService {

    /**
     * 检测用户名是否重复
     * @param name
     * @return
     */
    public Boolean checkUsername(String name);

    /**
     * 注册用户
     * @param user
     */
    public void register(User user);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 进行分页查询
     * @param currentPage
     * @param currentCount
     * @param category
     * @return
     */
    public PageBean<Products> finProductByPage(int currentPage, int currentCount, String category);


    /**
     *  通过id查找商品信息
     * @param id
     * @return
     */
    public Products findProductById(int id);


    /**
     * 创建订单
     * @param order
     */
    public void addOrder(Orders order);


    /**
     * 搜索图书
     * @param currentCount
     * @param currentPage
     * @param textfield
     * @return
     */
    PageBean<Products> findProductByName(int currentCount, int currentPage, String textfield);

    /**
     * 根据用户获取订单
     * @param id
     * @return
     */
    List<Orders> findOrderByUser(int id);
}
