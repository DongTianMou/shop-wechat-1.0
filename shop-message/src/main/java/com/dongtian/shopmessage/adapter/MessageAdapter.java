package com.dongtian.shopmessage.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述:(所有消息都会交给他进行转发)
 */
public interface MessageAdapter {
	//接受消息
    void distribute(JSONObject jsonObject);
}
