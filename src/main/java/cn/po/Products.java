package cn.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Products实体类
 */
@Getter
@Setter
public class Products {
    int id;
    String name;//商品名称
    BigDecimal price;//价格    由于需要精确表示所以用大数类
    String category;//种类
    int prum;//库存
    String imgurl;//图片url
    String description;//商品描述

    //由于价格采用大数类所以自己重写get和set
    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public String getPrice() {
        return price.toString();
    }
}
