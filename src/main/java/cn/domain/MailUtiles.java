package cn.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtiles {


    public static void sendMail(String userEmail, String emailContent) throws MessagingException {
        //得到session对象,与邮件服务器建立连接,通过邮件服务器发送邮件
        Properties props= new Properties();
        //设置发送邮件协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发送邮件账号
        props.setProperty("mail.host","smtp.qq.com");
        //设置smtp服务器是否用户验证
        props.setProperty("mail.smtp.auth","true");
        //创建校验器对象
        Authenticator aut = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //设置发送邮箱的账号密码
                String username = "781140432@qq.com";
                String password = "nsqjbdnkgckobaja";//这个是邮箱的授权码
                return new PasswordAuthentication(username,password);
            }
        };
        Session session = Session.getInstance(props, aut);
        //发送邮件，准备发送内容
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("781140432@qq.com"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(userEmail));
        //设置邮件主题
        message.setSubject("用户激活");
        message.setContent(emailContent, "text/html;charset=utf-8");
        Transport.send(message);
    }



}
