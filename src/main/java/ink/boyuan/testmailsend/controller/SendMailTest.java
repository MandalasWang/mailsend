package ink.boyuan.testmailsend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/19 15:38
 * @description
 **/
@RestController
@RequestMapping(value = "v1/api")
public class SendMailTest {


    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/sendmail",method = RequestMethod.GET)
    public String sendSimpleMail() throws Exception {
        SimpleMailMessage   message  =  new   SimpleMailMessage();
        message.setFrom("single_violet@163.com");
        message.setTo( "790832824@qq.com");
        message.setSubject(" Theme " );
        message.setText("Hello: how have you been!");
        mailSender.send( message);
      return "发送成功！";
    }

}
