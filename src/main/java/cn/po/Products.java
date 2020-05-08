package cn.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Products实体类
 */

@ToString
@Getter
@Setter
public class Products {
    int id;
    String name;//商品名称
    int price;//价格    由于需要精确表示所以用大数类,但只是测试所以统一采用整数
    String category;//种类
    int pnum;//库存
    String imgurl;//图片url
    String description;//商品描述

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return id == products.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
