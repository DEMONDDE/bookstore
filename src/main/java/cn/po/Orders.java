package cn.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Orders 实体类
 */
@Getter
@Setter
public class Orders {
    int id;
    String receiverName;//接收用户名
    String receiverAddress;//接受地址
    String receiverPhone;//接受电话
    String payState;//支付状态
    Date orderTime;//订单时间
    int user_id;//订单用户id
}
