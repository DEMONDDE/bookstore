package cn.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * OrderItem实体类
 */
@Data
public class OrderItem implements Serializable {
    String order_id;
    /**
     *  商品编号
     */
    int product_id;
    /**
     * 购买数量
     */
    int buynum;
}
