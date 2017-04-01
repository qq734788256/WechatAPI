package com.wx.base.param.system;

import com.wx.base.param.BaseParam;

public class LoginParam extends BaseParam {

    private static final long serialVersionUID = 4017725455848168049L;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
