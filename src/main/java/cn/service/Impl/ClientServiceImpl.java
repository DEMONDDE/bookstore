package cn.service.Impl;

import cn.domain.ActivateCode;
import cn.domain.MailUtiles;
import cn.domain.PageBean;
import cn.mapper.ClientMapper;
import cn.po.Orders;
import cn.po.Products;
import cn.po.User;
import cn.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


/**
 * @author 胡建德
 */
@Service("clientService")
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    public Boolean checkUsername(String name) {
        int a = clientMapper.checkName(name);
        //当重名返回false否则返回true
        if(a < 1)
            return false;
        return true;
    }


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
        PageBean<Products> bean = new PageBean<Products>();
        if(category.equals("全部商品")){
            category = null;
        }
        int totalCount = clientMapper.findTotalCategoryBook(category);
        int totalPage = totalCount % currentCount == 0? totalCount/currentCount:totalCount/currentCount+1;
        //判断页码是否超出范围
        if(currentPage <= 0 ){
            currentPage = 1;
        }else if(currentPage > totalPage){
            currentPage = totalPage;
        }
        bean.setCurrentPage(currentPage);
        bean.setPageCount(currentCount);
        bean.setTotalPage(totalPage);
        bean.setTotalCount(totalCount);
        //计算起始位置
        int start = (currentPage - 1) * currentCount;
        List<Products> list = clientMapper.findBook(category,start,currentCount);
        bean.setPs(list);
        return bean;
    }

    @Override
    public Products findProductById(int id) {
        return clientMapper.findProductById(id);
    }


    @Override
    public void addOrder(Orders order) {
        //存储订单信息
        clientMapper.addOrder(order);
        //存储订单物品信息
        clientMapper.addOrderItems(order.getOrderitem());
    }


    @Override
    public PageBean<Products> findProductByName(int currentCount, int currentPage, String textfield) {
        PageBean<Products> bean = new PageBean<Products>();
        //查找数据总量
        int totalCount = clientMapper.findTotalCategoryBookByName(textfield);
        int totalPage = totalCount % currentCount == 0? totalCount/currentCount:totalCount/currentCount+1;
        //判断页码是否超出范围
        if(currentPage <= 0 ){
            currentPage = 1;
        }else if(currentPage > totalPage){
            currentPage = totalPage;
        }
        bean.setCurrentPage(currentPage);
        bean.setPageCount(currentCount);
        bean.setTotalPage(totalPage);
        bean.setTotalCount(totalCount);
        //计算起始位置
        int start = (currentPage - 1) * currentCount;
        List<Products> list = clientMapper.findBookByName(textfield,start,currentCount);
        bean.setPs(list);
        return bean;
    }

    @Override
    public List<Orders> findOrderByUser(int id) {
        return clientMapper.findOrderByUser(id);
    }

}
