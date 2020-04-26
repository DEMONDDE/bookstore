package cn.mapper;


import cn.po.User;


public interface ClientMapper {

    //查询是否存在重名
    public Integer checkName(String name);

    //注册用户
    public int register(User user);

    //用户登陆
    public User longin( String username);
}
