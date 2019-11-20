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
public class RegisterController {
    private static final String LOCALREG = "localReg";
    private static final String LOGIN = "login";
    private static final String INDEX = "index";
    @Autowired
    private UserFeign userFeign;

    @RequestMapping(path = {"/register"}, method = {RequestMethod.GET})
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map = userFeign.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return INDEX;
            } else {
                model.addAttribute("msg", map.get("msg"));
                return "test";
            }

        } catch (Exception e) {
            log.error("注册异常" + e.getMessage());
            model.addAttribute("msg", "服务器错误");
            return "test";
        }
    }
}
