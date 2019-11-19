package com.dongtian.shopmobileweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mobileWeb")
@Slf4j
public class DemoController {

    public static final String INDEX = "index";

    @RequestMapping("/index")
    public String index(HttpServletRequest request, String token) {
        log.info(" 我的web工程搭建成功啦!,userName:{}");
        return INDEX;
    }

}
