package cn.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * User实体类
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    int id;
    String username;//用户名
    String password;//密码
    String gender;//性别
    String email;//邮箱
    String telephone;//电话
    String activiecode;//验证码也把他当作激活码
    String state;//状态 判断账号密码是否正确
    String role;//客户类别
    Date registime;//注册时间
    String description;
}
