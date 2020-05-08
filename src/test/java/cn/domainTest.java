package cn;

import cn.domain.FileNameDatabase;
import cn.domain.MailUtiles;
import cn.mapper.ClientMapper;
import cn.mapper.DatabaseInsert;
import cn.po.Products;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.io.File;
import java.util.*;

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
    public void inserBook() throws InterruptedException {
        String path = "C:\\Users\\78114\\Desktop\\bookcover";
        File file = new File(path);
        String[] names = file.list();
        Random r = new Random();
        int price;//随机生成价格
        int prum;//生成随机库存
        String[] categros={"文学","生活","计算机","外语","经管","励志","社科","学术","少儿","艺术","原版","科技","考试","原活百科"};
        String categro;
        List<Products> productsList = new ArrayList<Products>();
        Products products;
        for(int i = 0; i < names.length; i++){
            String name = names[i];
            price = r.nextInt(200)+1;
            prum = r.nextInt(200)+200;
            categro = categros[r.nextInt(14)];
            String url = "/client/bookcover/"+names[i];
            products = new Products();
            products.setName(name);
            products.setPrice(price);
            products.setPnum(prum);
            products.setCategory(categro);
            products.setImgurl(url);
            productsList.add(products);
        }
        databaseInsert.insertBook(productsList);
    }

    @Test
    public void aaa(){
        Map<Products,Integer> a=new HashMap<>();
        Products products1 = new Products();
        products1.setId(1);
        Products products2 = new Products();
        products2.setId(1);
//        System.out.println(products1.hashCode());
//        System.out.println(products2.hashCode());
        a.put(products1,1);
        a.put(products2,2);
        Products products = new Products();
        products.setId(1);
        a.put(products,0);
        System.out.println(a);
    }

    @Test
    public void aq(){
        reverse(1534236469);
    }

    public int reverse(int x) {
        int intNum = x;//记录整数
        int cnt = 0;//记录反转的结果
        while(intNum != 0){
            cnt = cnt *10 + intNum % 10;
            intNum = intNum / 10;
        }
        return cnt;
    }
}
