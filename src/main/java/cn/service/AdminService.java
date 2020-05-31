package cn.service;

import cn.po.Orders;
import cn.po.Products;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * admin的service类
 */

public interface AdminService {

    /**
     * 查找所有商品
     * @return
     */
   public List<Products> findAll();

    /**
     *   按条件查询
     * @param id
     * @param name
     * @param category
     * @param minprice
     * @param maxprice
     * @return
     */
   public List<Products> findProductByManyCondition(Integer id, String name, String category, Integer minprice, Integer maxprice);

    /**
     *  添加商品
     * @param product
     * @return
     */
   public Boolean addProduct(Products product);

    /**
     * 删除商品
     * @param id
     * @return
     */
   public boolean deleteProductById(int id);

    /**
     * 修改产品
     * @param product
     */
   public void editProduct(Products product);

    /**
     * 查找
     * @param id
     * @return
     */
   public Products findProductById(int id);

    /**
     * 获取销售榜单
     * @param year
     * @param month
     * @return
     */
   public List<Object[]> download(String year, String month);

    /**
     * 获取订单
     * @return
     */
    public List<Orders> findOrders();

    /**
     * 按条件查询订单
     * @param id
     * @param receiverName
     * @return
     */
    public List<Orders> findOrderByManyCondition(String id, String receiverName);

    /**
     * 按id查询订单
     * @param id
     * @return
     */
    public Orders findOrderById(String id);

    /**
     * 删除订单
     * @param id
     */
    void delOrderById(String id);


}
