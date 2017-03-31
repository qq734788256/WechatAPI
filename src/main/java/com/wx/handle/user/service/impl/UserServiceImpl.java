package com.wx.handle.user.service.impl;

import com.wx.base.entity.User;
import com.wx.base.result.User.UserResult;
import com.wx.handle.user.dao.IUserDao;
import com.wx.handle.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public UserResult getUserInfo() {
        UserResult r = new UserResult();
        User user = userDao.getUserInfo();
        BeanUtils.copyProperties(user,r);
        return r;
    }
}
