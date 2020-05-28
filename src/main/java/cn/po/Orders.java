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
    private String id;
    /**
     * 接收用户名
     */
    private String receiverName;
    /**
     * 接受地址
     */
    private String receiverAddress;
    /**
     * 接受电话
     */
    private String receiverPhone;
    /**
     * 支付状态
     */
    private int payState;
    /**
     * 订单时间
     */
    private Date orderTime;
    /**
     * 订单用户id
     */
    private int user_id;
    /**
     *订单价格
     */
    private double money;
    /**
     * 订单数量
     */
    private int size;
    User user;
    /**
     * 产品信息
     */
    List<OrderItem> orderitem;

    public Orders (){
        this.orderitem = new ArrayList<OrderItem>();
    }
}
