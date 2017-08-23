package com.wx.handle.user.dao.impl;

import com.wx.base.entity.User;
import com.wx.base.util.SysTimeUitl;
import com.wx.handle.common.MongoUtil;
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

    @Override
    public User getUserById(int id) {
        User user = MongoUtil.get("user","id",id,User.class);
        if(user == null){
            user = userMapper.getUserById(id);
            // 防止击穿
            if(user == null){
                user = new User();
                user.setId(id);
                user.setRoleId(-999);
            }
            MongoUtil.insert("user",user);
        } else {
            if(user.getRoleId() == -999){
                user = null;
            }
        }
        return user;
    }

    @Override
    public int updateUserInfo(User user) {
        user.setUpdateTime(SysTimeUitl.getSystemTime());
        int result = userMapper.updateUserInfo(user);
        if(result > 0){
            MongoUtil.update("user", "id", user.getId(), user);
        }
        return result;
    }
}
