package com.wx.handle.user.dao;

import com.wx.base.entity.User;

import java.util.Map;

public interface IUserDao {

    User getUserInfo(Map<String, String> queryParam);

    User getUserById(int id);
}
