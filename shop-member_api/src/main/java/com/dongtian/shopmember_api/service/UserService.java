package com.dongtian.shopmember_api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public interface UserService {
    @RequestMapping("/register")
    Map<String, Object> register(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("/login")
    Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password);
}
