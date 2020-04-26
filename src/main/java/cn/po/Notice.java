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
    String title;//题目
    String detail;//内容
    Date n_time;//创建时间
}
