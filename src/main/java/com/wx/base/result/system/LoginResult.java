package com.wx.base.result.system;

import com.wx.base.result.BaseResult;

/**
 * 登录结果类
 */
public class LoginResult extends BaseResult {

    private static final long serialVersionUID = -632456243842421452L;
    /**
     * 用户token
     */
    private String token;
    private String userName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
