package cn.mapper;

import cn.po.User;
import cn.service.ClientService;
import cn.service.Impl.ClientServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * 关于ClientMapper测试类
 */
public class ClientMapperTest {

    ApplicationContext  act ;
    ClientMapper clientMapper;
    @Before
    public void init(){
        act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        clientMapper = act.getBean(ClientMapper.class);
    }


    //注册测试
    @Test
    public void resigerTest(){
        User user = new User();
        Date date = new Date();
        user.setRegistime(date);
        user.setEmail("1");
        user.setUsername("1");
        user.setGender("1");
        user.setPassword("1");
        user.setTelephone("1");
        int a = clientMapper.register(user);
        System.out.println(a);

    }

    //登陆测试
    @Test
    public void login(){
        //正确显示
        User user = clientMapper.longin("test");
        System.out.println(user);
        //错误显示
        user = clientMapper.longin("123");
        System.out.println(user);
    }

}
