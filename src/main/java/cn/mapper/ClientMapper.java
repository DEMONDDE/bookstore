package cn.mapper;


import cn.po.OrderItem;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClientMapper {

    /**
     * 查询是否存在重名
     * @param name
     * @return
     */
    public Integer checkName(String name);

    /**
     * 注册用户
     * @param user
     * @return
     */
    public int register(User user);

    /**
     * 用户登陆
     * @param username
     * @return
     */
    public User longin( String username);

    /**
     * 查询数量
     * @param category
     * @return
     */
    public Integer findTotalCategoryBook(String category);

    /**
     * 查找书籍
     * @param category
     * @param start
     * @param currentCount
     * @return
     */
    public List<Products> findBook(@Param("category") String category,
                                   @Param("start")int start,@Param("currentCount") int currentCount);
    /**
     * 通过id查找商品
     * @param id
     * @return
     */
    Products findProductById(int id);

    /**
     *   存储订单信息
     * @param order
     */
    void addOrder(Orders order);

    /**
     * 存储订单物品信息
     * @param items
     */
    void addOrderItems(List<OrderItem> items);

    /**
     * 通过名称查找书籍数量
     * @param textfield
     * @return
     */
    Integer findTotalCategoryBookByName(String textfield);

    /**
     * 通过名称查找书籍
     * @param textfield
     * @param start
     * @param currentCount
     * @return
     */
    List<Products> findBookByName(@Param("textfield") String textfield,@Param("start") int start,@Param("rows") int currentCount);

    /**
     * 根据用户查找订单
     * @param id
     * @return
     */
    List<Orders> findOrderByUser(int id);
}
