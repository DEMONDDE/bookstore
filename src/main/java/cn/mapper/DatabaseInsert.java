package cn.mapper;

import cn.po.Products;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 向数据库插入必要数据
 */
public interface DatabaseInsert {
    public void insertBook(String[] names);

    void insertBook(List<Products> list);
}
