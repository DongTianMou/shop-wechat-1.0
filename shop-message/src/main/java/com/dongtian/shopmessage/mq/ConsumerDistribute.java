package com.dongtian.shopmessage.mq;


import com.alibaba.fastjson.JSONObject;
import com.dongtian.shopcommon.constants.MQInterfaceType;
import com.dongtian.shopmessage.Service.SMSMailboxService;
import com.dongtian.shopmessage.adapter.MessageAdapter;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerDistribute {
    @Autowired
    private SMSMailboxService smsMailboxService;
    //监听消息队列
    @JmsListener(destination = "mail_queue")
    public void distribute(String msg) {
        log.info("###消息服务###收到消息,消息内容 json:{}", msg);
        //如果消息内容为空，直接返回
        if (StringUtils.isEmpty(msg)) {
            return;
        }
        //String -- Json
        JSONObject stringToJson = new JSONObject().parseObject(msg);
        //拿到报文信息
        JSONObject header = stringToJson.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");

        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
            //判断类型
            case MQInterfaceType.SMS_MAIL:
                // 发送邮件
                messageAdapter = smsMailboxService;
                break;
            default:
                break;
        }
        //只需要content中的内容
        JSONObject content = stringToJson.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}
