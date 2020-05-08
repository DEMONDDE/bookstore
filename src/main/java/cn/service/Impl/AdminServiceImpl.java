package cn.service.Impl;

import cn.mapper.AdminMapper;
import cn.po.Products;
import cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * adminService的实现类
 */

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<Products> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public List<Products> findProductByManyCondition(Integer id, String name, String category, Integer minprice, Integer maxprice) {
        return adminMapper.findProductByManyCondition(id, name, category, minprice, maxprice);
    }

    @Override
    public Boolean addProduct(Products product) {
        //判断数据库是否已存在
        List<Products> p = adminMapper.findProductByManyCondition(null,product.getName(),product.getCategory(),null,null);
        if(p.size() > 0){
            return false;
        }
        //添加
        adminMapper.addProduct(product);
        return true;
    }

    @Override
    public boolean deleteProductById(int id) {
        int a = adminMapper.deleteProductById(id);
        if(a > 0){
            return true;
        }
        return false;
    }

    @Override
    public void editProduct(Products product) {
        adminMapper.editProduct(product);
    }

    @Override
    public Products findProductById(int id) {

        return adminMapper.findProductById(id);
    }
}
