package com.dongtian.shopcommon.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LoginTicket {
    private int id;
    private Long userId;
    private Date expired;
    private int status;// 0有效，1无效
    private String ticket;

}
