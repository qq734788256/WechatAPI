package com.wx.base.param.user;

import com.wx.base.param.BaseParam;

/**
 * 修改密码
 */
public class UpdatePasswordParam extends BaseParam {
    private static final long serialVersionUID = -4397827218480700391L;

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
