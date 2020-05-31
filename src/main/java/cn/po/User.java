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
    /**
     *  用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
    /**
     * 性别
     */
    String gender;
    /**
     * 邮箱
     */
    String email;
    /**
     * 电话
     */
    String telephone;
    /**
     * 验证码也把他当作激活码
     */
    String activiecode;
    /**
     * 状态 判断账号密码是否正确
     */
    String state;
    /**
     * 客户类别
     */
    String role;
    /**
     * 注册时间
     */
    Date registime;
    /**
     * 简介
     */
    String description;
}
