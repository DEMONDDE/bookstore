package cn;

import cn.domain.FileNameDatabase;
import cn.domain.MailUtiles;
import cn.mapper.ClientMapper;
import cn.mapper.DatabaseInsert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.io.File;

/**
 * 工具栏测试
 */
public class domainTest {
    ApplicationContext act ;
    DatabaseInsert databaseInsert;
    @Before
    public void init(){
        act = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        databaseInsert = act.getBean(DatabaseInsert.class);
    }

    @Test
    public void sendMailTest(){
        try {
            MailUtiles.sendMail("2912182810@qq.com","hello");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void IntegerTest(){
        Integer a = Integer.valueOf("");
        System.out.println(a);
    }

    //不是测试只是为了插入书籍信息
    @Test
    public void inserBook(){
        String path = "C:\\Users\\78114\\Desktop\\bookcover";
        File file = new File(path);
        String[] names = file.list();
        databaseInsert.insertBook(name);

    }
}
