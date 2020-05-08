package cn.po;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Orders 实体类
 */
@Getter
@Setter
public class Orders {
    String id;
    String receiverName;//接收用户名
    String receiverAddress;//接受地址
    String receiverPhone;//接受电话
    String payState;//支付状态
    Date orderTime;//订单时间
    int user_id;//订单用户id
    List<OrderItem> items;//产品信息

    public Orders (){
        this.items = new ArrayList<OrderItem>();
    }
}
