package com.wx.handle.system.service;

import com.wx.base.param.system.LoginParam;
import com.wx.base.result.system.LoginResult;

public interface ISystemService {

    /**
     * 用户登录
     * @param param
     * @return
     */
    LoginResult login(LoginParam param);

}
