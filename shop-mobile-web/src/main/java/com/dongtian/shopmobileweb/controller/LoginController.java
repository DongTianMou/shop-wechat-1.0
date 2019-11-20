package com.dongtian.shopmobileweb.controller;

import com.dongtian.shopmobileweb.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {
    private static final String LOGIN = "login";
    private static final String INDEX = "index";
    @Autowired
    private UserFeign userFeign;

    @RequestMapping(path = {"/login"}, method = {RequestMethod.GET})
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {
        try {
            Map<String, Object> map = userFeign.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return INDEX;
            } else {
                model.addAttribute("msg", map.get("msg"));
                return LOGIN;
            }

        } catch (Exception e) {
            log.error("登陆异常" + e.getMessage());
            return LOGIN;
        }
    }


}

