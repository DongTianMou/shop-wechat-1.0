package com.dongtian.shopmessage.Service;

import com.alibaba.fastjson.JSONObject;
import com.dongtian.shopmessage.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SMSMailboxService implements MessageAdapter {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void distribute(JSONObject jsonObject) {
        //获取发送者邮箱和用户用户名
        String sender = jsonObject.getString("mail");
        String userName=jsonObject.getString("userName");
        log.info("###消费者收到消息... mail:{},userName:{}",sender,userName);
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发送
        message.setFrom(sender);
        //发给谁
        message.setTo(sender); // 自己给自己发送邮件
        //邮件主题
        message.setSubject("微信商城会员注册成功");
        message.setText("恭喜您"+userName+",成为微信商城的新用户!谢谢您的光临!");
        log.info("###发送短信邮箱 mail:{}", sender);
        //调用接口发送
        javaMailSender.send(message);
    }
}
