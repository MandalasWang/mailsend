package ink.boyuan.testmailsend.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/19 16:15
 * @description
 **/

@Component
public class MailMsg {

    @Value("${mail.sender}")
    private String mailSender;

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }
}
