package cn.mapper;

import cn.po.Products;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * adminMapper测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AdminMapperTest {

    @Autowired
    ApplicationContext act ;
    AdminMapper adminMapper;
    @Before
    public void init(){
//        act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        adminMapper = act.getBean(AdminMapper.class);
    }
    //findAll()测试
    @Test
    public void findAllTest(){
        List<Products> list = adminMapper.findAll();
        System.out.println(list.toString());
    }

    //findProductByManyCondition测试
    @Test
    public void findProductByManyConditionTest(){
        List<Products> list = adminMapper.findProductByManyCondition(null,"101.jpg",null,null,null);
        System.out.println(list.toString());
    }
}
