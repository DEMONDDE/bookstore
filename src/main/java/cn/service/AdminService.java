package cn.service;

import cn.po.Orders;
import cn.po.Products;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * admin的service类
 */

public interface AdminService {

    //查找所有商品
   public List<Products> findAll();

   //按条件查询
   public List<Products> findProductByManyCondition(Integer id, String name, String category, Integer minprice, Integer maxprice);

   //添加商品
   public Boolean addProduct(Products product);

   //删除商品
   public boolean deleteProductById(int id);

   //修改产品
   public void editProduct(Products product);

   //查找
   public Products findProductById(int id);

   //获取销售榜单
   public List<Object[]> download(String year, String month);

   //获取订单
    public List<Orders> findOrders();

    //按条件查询订单
    public List<Orders> findOrderByManyCondition(String id, String receiverName);

    //按id查询订单
    public Orders findOrderById(String id);

    //删除订单
    void delOrderById(String id);


}
