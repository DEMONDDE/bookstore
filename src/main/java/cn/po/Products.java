package cn.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Products {
    int id;
    /**
     * 商品名称
     */
    String name;
    /**
     * 价格    由于需要精确表示所以用大数类,但只是测试所以统一采用整数
     */
    int price;
    /**
     * 种类
     */
    String category;
    /**
     * 库存
     */
    int pnum;
    /**
     * 图片url
     */
    String imgurl;
    /**
     * 商品描述
     */
    String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Products products = (Products) o;
        return id == products.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
