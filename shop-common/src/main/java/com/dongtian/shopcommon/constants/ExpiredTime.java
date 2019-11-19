package com.dongtian.shopcommon.constants;

public interface ExpiredTime {
    //用户会话保存90天
    Long USER_TOKEN_TERMVALIDITY = 60 * 60 * 24 * 90L;
    long WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 1000*3600*90*24;
}
