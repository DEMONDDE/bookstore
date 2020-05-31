package cn.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Notice实体类
 */
@Getter
@Setter
public class Notice {
    int id;
    /**
     * 题目
     */
    String title;
    /**
     * 内容
     */
    String detail;
    /**
     * 创建时间
     */
    Date n_time;
}
