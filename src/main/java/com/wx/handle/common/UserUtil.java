package com.wx.handle.common;


import com.wx.base.token.UserToken;
import com.wx.base.util.AesUtil;
import com.wx.base.util.JacksonUtil;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static int getUserId(HttpServletRequest request){
        // 获取token
        String token = request.getHeader("UToken");
        // 解析token
        UserToken userToken = JacksonUtil.toObject(AesUtil.getUserInfoByToken(token), UserToken.class);
        return userToken.getId();
    }
}
