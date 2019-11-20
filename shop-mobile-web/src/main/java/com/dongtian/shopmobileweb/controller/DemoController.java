package com.dongtian.shopmobileweb.controller;

import com.dongtian.shopmobileweb.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class DemoController {
    private static final String INDEX = "index";
    @Autowired
    UserFeign userFeign;
    @RequestMapping("/demo/index")
    public String index(String username,String password){
        userFeign.login(username,password);
        return INDEX;

    }


}
