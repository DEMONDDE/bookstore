package cn.service;

import cn.po.User;
import cn.service.Impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientServiceTest {
    ApplicationContext act ;
    ClientService clientService;
    @Before
    public void init(){
        act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        clientService = act.getBean(ClientService.class);
    }

    @Test
    public void chen(){
        Boolean a = clientService.checkUsername("test1");
        System.out.println(a);
    }

    //登陆测试
    @Test
    public void login(){
        User user = clientService.login("test","1");
        System.out.println(user);
        user = clientService.login("test","123");
        System.out.println(user);
        user = clientService.login("123", "123");
        System.out.println(user);
    }
}
