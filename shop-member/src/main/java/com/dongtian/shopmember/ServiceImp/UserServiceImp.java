package com.dongtian.shopmember.ServiceImp;

import com.alibaba.fastjson.JSONObject;
import com.dongtian.shopcommon.constants.MQInterfaceType;
import com.dongtian.shopcommon.entity.LoginTicket;
import com.dongtian.shopcommon.entity.User;
import com.dongtian.shopcommon.utils.DateUtils;
import com.dongtian.shopcommon.utils.MD5Utils;
import com.dongtian.shopcommon.utils.ReturnInfoUtils;
import com.dongtian.shopmember.dao.LoginTicketDao;
import com.dongtian.shopmember.dao.UserDao;
import com.dongtian.shopmember.mq.RegisterMailboxProducer;
import com.dongtian.shopmember_api.service.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;
    @Autowired
    private LoginTicketDao loginTicketDao;

    @Override
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg","用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg","密码不能为空");
            return map;
        }

        User user = userDao.selectByName(username);

        if (user != null) {
            map.put("msg","用户名已经被注册");
            return map;
        }
        // 密码强度
        user = new User();
        user.setUsername(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setPassword(MD5Utils.MD5(password+user.getSalt()));
        user.setPhone("13719300943");
        user.setEmail("13719300942@163.com");
        user.setCreate_time(DateUtils.getTimestamp());
        user.setUpdate_time(DateUtils.getTimestamp());
        userDao.addUser(user);

        User user_1 = userDao.selectByName(username);
        // 注册成功默认给一个ticket
        String ticket = addLoginTicket(user_1.getId());
        map.put("ticket", ticket);

        // 队列
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        // 组装报文格式
        String mailMessage = mailMessage(user.getEmail(), user.getUsername());
        log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);
        // mq
        registerMailboxProducer.send(activeMQQueue, mailMessage);
        return map;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg","用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg","密码不能为空");
            return map;
        }

        User user = userDao.selectByName(username);

        if (user == null) {
            map.put("msg","用户名没有被注册");
            return map;
        }

        if (!MD5Utils.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg","密码不正确");
            return map;
        }
        //生成一个token
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        map.put("userId", user.getId());
        return map;
    }

    private String addLoginTicket(Long id) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(id);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDao.addTicket(ticket);
        return ticket.getTicket();
    }

    //邮件的报文格式
    private String mailMessage(String email, String userName) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }

}
