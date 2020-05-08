package cn.mapper;


import cn.po.OrderItem;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ClientMapper {

    //查询是否存在重名
    public Integer checkName(String name);

    //注册用户
    public int register(User user);

    //用户登陆
    public User longin( String username);

    //查询数量
    public Integer findTotalCategoryBook(String category);

    //查找书籍
    public List<Products> findBook(@Param("category") String category,
                                   @Param("start")int start,@Param("currentCount") int currentCount);

    //通过id查找商品
    Products findProductById(int id);

    //存储订单信息
    void addOrder(Orders order);

    //存储订单物品信息
    void addOrderItems(List<OrderItem> items);

    //通过名称查找书籍数量
    Integer findTotalCategoryBookByName(String textfield);

    //通过名称查找书籍
    List<Products> findBookByName(@Param("textfield") String textfield,@Param("start") int start,@Param("rows") int currentCount);
}
