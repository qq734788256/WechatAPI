package com.wx.handle.user.service;

import com.wx.base.result.BaseResult;
import com.wx.base.result.user.UserResult;

public interface IUserService {

    UserResult getUserInfo(int userId);

    BaseResult updatePassword(int userId, String oldPassword, String newPassword);
}
