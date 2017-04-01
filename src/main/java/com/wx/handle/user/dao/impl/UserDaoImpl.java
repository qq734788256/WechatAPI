package com.wx.handle.user.dao.impl;

import com.wx.base.entity.User;
import com.wx.handle.user.dao.IUserDao;
import com.wx.handle.user.mapper.IUserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {

    @Resource
    private IUserMapper userMapper;

    @Override
    public User getUserInfo(Map<String, String> queryParam) {
        return userMapper.getUserInfo(queryParam);
    }
}
