package cn.domain;

import java.util.UUID;

/**
 * 提供激活码
 * @author 胡建德
 */
public class ActivateCode {
    public static String getActivateCode(){
        String code = UUID.randomUUID().toString();
        return code;
    }

}
