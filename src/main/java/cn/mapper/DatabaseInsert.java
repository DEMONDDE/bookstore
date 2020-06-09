package cn.mapper;

import cn.po.Products;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 向数据库插入必要数据
 * @author 胡建德
 */
public interface DatabaseInsert {
    /**
     * 插入书籍名称
     * @param names
     */
    public void insertBook(String names);

    /**
     * 插入书籍信息
     * @param list
     */
    void insertBook(List<Products> list);
}
