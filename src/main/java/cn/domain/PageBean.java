package cn.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * 分页类
 * @author 胡建德
 */
@Setter
@Getter
public class PageBean <T>{
    /**
     * 当前页码
     */
    int currentPage;
    /**
     * 每页显示商品数
     */
    int pageCount;
    /**
     * 商品详情
     */
    List<T> ps;
    /**
     * 商品总页数
     */
    int totalPage;
    /**
     * 商品总记录数
     */
    int totalCount;
    /**
     * 种类名称
     */
    String category;



}
