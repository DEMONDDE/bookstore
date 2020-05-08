package cn.service;

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
}
