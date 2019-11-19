package com.dongtian.shopmember_api.service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ResponseBody
@RequestMapping("/member")
public interface UserService {
    @GetMapping("/register")
    Map<String, Object> register(String username, String password);

}
