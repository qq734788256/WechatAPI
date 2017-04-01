package com.wx.handle.user.mapper;

import com.wx.base.entity.User;

import java.util.Map;

public interface IUserMapper {

    User getUserInfo(Map<String, String> queryParam);
}
