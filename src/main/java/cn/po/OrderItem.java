package cn.po;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderItem实体类
 */
@Getter
@Setter
public class OrderItem {
    String order_id;
    int product_id;//商品编号
    int buynum;//购买数量
}
