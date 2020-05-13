package cn.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Orders 实体类
 */
@Getter
@Setter
@ToString
public class Orders {
    String id;
    String receiverName;//接收用户名
    String receiverAddress;//接受地址
    String receiverPhone;//接受电话
    String payState;//支付状态
    Date orderTime;//订单时间
    int user_id;//订单用户id
    double money;//订单价格
    User user;
    List<OrderItem> orderitem;//产品信息

    public Orders (){
        this.orderitem = new ArrayList<OrderItem>();
    }
}
