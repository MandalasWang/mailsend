package ink.boyuan.testmailsend.controller;

import ink.boyuan.testmailsend.constant.MailMsg;
import ink.boyuan.testmailsend.model.MailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;

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
    private MailMsg mailMsg;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单的邮件
     * @param mailBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendmail",method = RequestMethod.GET)
    public String sendSimpleMail(@RequestBody MailBean mailBean) throws Exception {
        SimpleMailMessage   message  =  new   SimpleMailMessage();
        message.setFrom(mailMsg.getMailSender());
        message.setTo( mailBean.getRecipient());
        message.setSubject(" Theme " );
        message.setText("Hello: how have you been!");
        mailSender.send( message);
      return "发送成功！";
    }

    /**
     * 发送HTML格式的邮件
     * @param mailBean
     */
    @RequestMapping(value = "/sendHtml",method = RequestMethod.GET)
    public String sendHTMLMail(@RequestBody MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(mailMsg.getMailSender());
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("\"<p style='color:#F00'>你是真的太棒了！</p>");
            mimeMessageHelper.setText(sb.toString(), true);
            mailSender.send(mimeMailMessage);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "success";
    }

    /**
     * 发送静态文件的邮件
     * @param mailBean
     */
    @RequestMapping(value = "/sendAttachment",method = RequestMethod.GET)
    public String sendAttachmentMail(@RequestBody MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(mailMsg.getMailSender());
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.jpg"));
            mimeMessageHelper.addAttachment("mail.jpg", file);

            mailSender.send(mimeMailMessage);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "success";
    }
}
