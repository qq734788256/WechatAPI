package com.wx.handle.system.service;

import com.wx.base.param.system.LoginParam;
import com.wx.base.param.system.WxOpenParam;
import com.wx.base.result.system.LoginResult;
import com.wx.base.result.system.WxOpenResult;

public interface ISystemService {

    /**
     * 用户登录
     * @param param
     * @return
     */
    LoginResult login(LoginParam param);

    /**
     * 获取微信openId
     * @param param
     * @return
     */
    WxOpenResult getWxOpenID(WxOpenParam param);
}
