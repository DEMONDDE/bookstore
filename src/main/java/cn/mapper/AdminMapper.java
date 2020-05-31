package cn.mapper;

import cn.po.OrderItem;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * admin的mapper类
 */
public interface AdminMapper {
    /**
     * 查找所有商品
     * @return
     */
    public List<Products> findAll();

    /**
     * 条件查询
     * @param id
     * @param name
     * @param category
     * @param minprice
     * @param maxprice
     * @return
     */
    public List<Products> findProductByManyCondition(@Param("id") Integer id, @Param("name")String name,
                                                     @Param("category")String category,
                                                     @Param("minprice")Integer minprice, @Param("maxprice")Integer maxprice);

    /**
     * 添加商品
     * @param product
     */
    public void addProduct(Products product);

    /**
     * 删除商品
     * @param id
     * @return
     */
    public int deleteProductById(int id);

    /**
     * 修改商品
     * @param product
     */
    public void editProduct(Products product);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public Products findProductById(Integer id);

    /**
     * 查询榜单
     * @param year
     * @param month
     * @return
     */
    public List<Object[]> salesList(String year, String month);

    /**
     * 查询订单
     * @return
     */
    public List<Orders> findOrders();

    /**
     * 按条件查询订单
     * @param id
     * @param receiverName
     * @return
     */
    public List<Orders> findOrderByManyCondition(@Param("id") String id, @Param("receiverName") String receiverName);

    /**
     * 按id查询订单
     * @param id
     * @return
     */
    public Orders findOrderById(String id);

    /**
     * 根据订单查询商品
     * @param order
     * @return
     */
    public List<OrderItem> findOrderItemByOrder(Orders order);

    /**
     * 删除订单
     * @param id
     */
    void delOrderById(String id);

    //删除物品
    void delOrderItemById(String id);
}
