package com.wx.handle.user.service.impl;

import com.wx.base.result.user.UserResult;
import com.wx.handle.user.dao.IUserDao;
import com.wx.handle.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public UserResult getUserInfo() {
        UserResult r = new UserResult();
        return r;
    }
}
