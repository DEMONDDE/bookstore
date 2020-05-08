package cn.mapper;

import cn.po.Products;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * admin的mapper类
 */
public interface AdminMapper {
    //查找所有商品
    public List<Products> findAll();

    //条件查询
    public List<Products> findProductByManyCondition(@Param("id") Integer id, @Param("name")String name,
                                                     @Param("category")String category,
                                                     @Param("minprice")Integer minprice, @Param("maxprice")Integer maxprice);

    //添加商品
    public void addProduct(Products product);

    //删除商品
    public int deleteProductById(int id);

    //修改商品
    public void editProduct(Products product);

    //按id查询
    public Products findProductById(Integer id);
}
