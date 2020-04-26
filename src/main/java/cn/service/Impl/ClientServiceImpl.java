package cn.service.Impl;

import cn.domain.ActivateCode;
import cn.domain.MailUtiles;
import cn.domain.PageBean;
import cn.mapper.ClientMapper;
import cn.po.Products;
import cn.po.User;
import cn.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;


@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientMapper clientMapper;
//    进行对用户名查重
    @Override
    public Boolean checkUsername(String name) {
        int a = clientMapper.checkName(name);
        //当重名返回false否则返回true
        if(a < 1)
            return false;
        return true;
    }

    //注册用户
    @Override
    public void register(User user) {

        //确定注册时间
        Date date = new Date();
        user.setRegistime(date);
        user.setRole("普通用户");
        user.setActiviecode(ActivateCode.getActivateCode());
        //发送邮件内容
        String content = "请注意这是测试用的邮件，<br>点击<a href = 'http://www.baidu.com'> 激活<a>";
        try {
            MailUtiles.sendMail(user.getEmail(),content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        clientMapper.register(user);
    }

    //用户登陆
    @Override
    public User login(String username, String password) {
        User user = clientMapper.longin(username);
        if (user != null){
            //判断账号密码 正确为登陆 不正确为未登录
            if(user.getPassword().equals(password)){
                user.setState("登陆");
            }else {
                user.setState("未登录");
            }
        }
        return user;
    }

    @Override
    public PageBean<Products> finProductByPage(int currentPage, int currentCount, String category) {
        PageBean<Products> bean = null;

        return bean;
    }

}
