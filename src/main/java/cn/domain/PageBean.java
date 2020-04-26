package cn.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * 分页类
 */
@Setter
@Getter
public class PageBean <T>{
    int currentPage;//当前页码
    int pageCount;//每页显示商品数
    List<T> list;//商品详情
    int totalPages;//商品总页数
    int totalCount;//商品总记录数

}
