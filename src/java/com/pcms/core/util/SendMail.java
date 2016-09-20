package com.pcms.core.util;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMail {
    private static Logger logger = Logger.getLogger(SendMail.class);
       
    public static void send(String smtp, final String user, final String password, String subject, String content, String from, String to) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtp);
            props.put("mail.smtp.auth", "true");
            Session ssn = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            MimeMessage message = new MimeMessage(ssn);
            InternetAddress fromAddress = new InternetAddress(from);
            message.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(content,"text/html; charset=utf-8");
 
            message.setSentDate(new Date());

            Transport transport = ssn.getTransport("smtp");
            transport.connect(smtp, user, password);
            transport.sendMessage(message, message
                    .getRecipients(Message.RecipientType.TO));
            transport.close();
            logger.info("邮件发送成功");
        } catch (Exception e) {
            logger.warn("邮件发送失败", e);
        }
    }
    public static void main(String[] args){
        send("smtp.sina.com","jkgb@raytrontech.com","jkgb.tms.raytron.123","222","测试邮件","jkgb@raytrontech.com","522763304@qq.com");
    }
}
