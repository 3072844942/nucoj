package com.q7g.nucoj_spring.consumer;

import com.alibaba.fastjson.JSON;
import com.q7g.nucoj_spring.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.List;

import static com.q7g.nucoj_spring.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * 通知邮箱
 **/
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class EmailConsumer {

    /**
     * 邮箱号
     */
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender mailSender;

    @RabbitHandler
    public void process(byte[] data) throws MessagingException {
        EmailDTO emailDTO = JSON.parseObject(new String(data), EmailDTO.class);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(email);
        messageHelper.setTo(emailDTO.getEmail());
        messageHelper.setSubject(emailDTO.getSubject());
        messageHelper.setText(emailDTO.getContent(), true);
        mailSender.send(mimeMessage);
    }
}
