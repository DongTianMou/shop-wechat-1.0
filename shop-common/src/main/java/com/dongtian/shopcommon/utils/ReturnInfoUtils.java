package com.dongtian.shopcommon.utils;

import com.dongtian.shopcommon.constants.CodeUtils;

import java.util.HashMap;
import java.util.Map;

public class ReturnInfoUtils {

    public static Map<String, Object> setResult(Integer code, String msg, Object data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CodeUtils.HTTP_CODE_NAME, code);
        result.put(CodeUtils.HTTP_200_NAME, msg);
        if (data != null)
            result.put(CodeUtils.HTTP_DATA_NAME, data);
        return result;
    }
    //功能描述:(返回错误)
    public static Map<String, Object> setResultError(String msg) {
        return setResult(CodeUtils.HTTP_500_CODE, msg, null);
    }
    // @methodDesc: 功能描述:(返回成功),以及消息
    public static Map<String, Object> setResultSuccess(String msg) {
        return setResult(CodeUtils.HTTP_200_CODE, msg, null);
    }
    //@methodDesc: 功能描述:(返回成功)数据
    public static Map<String, Object> setResultSuccessData(Object data) {
        return setResult(CodeUtils.HTTP_200_CODE, CodeUtils.HTTP_SUCCESS_NAME, data);
    }
    //@methodDesc: 功能描述:(返回成功)
    public static Map<String, Object> setResultSuccess() {
        return setResult(CodeUtils.HTTP_200_CODE, CodeUtils.HTTP_SUCCESS_NAME, null);
    }
}
