package com.dongtian.shopmember.mq;

import javax.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/*
功能描述:(往消息服务 推送 邮件信息)
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate ;
    /**
     * 向指定队列destination发送消息msg
     */
    public void send(Destination destination, String msg){
        jmsMessagingTemplate.convertAndSend(destination,msg);
    }
}
