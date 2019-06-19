package com.byk.common.common;

/**
 * @Author: love
 * @Description:
 * @Date: 2019-06-19 17:33
 * @Version: 1.0
 */
public class RedisCommon {

    /**
     * 生成 保存用户信息的 redis key
     * @param userCode
     * @return
     */
    public static String getUserInfoRedisKey(String userCode){
        return "GET_USER_INFO_KEY_"+userCode;
    }
}
