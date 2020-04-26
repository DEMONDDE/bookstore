package cn.po;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderItem实体类
 */
@Getter
@Setter
public class OrderItem {
    int order_id;
    int product_id;//商品编号
    int buynum;//购买数量
}
