package com.wx.handle.user.service.impl;

import com.wx.base.entity.User;
import com.wx.base.result.user.UserResult;
import com.wx.base.util.ResultStatus;
import com.wx.handle.user.dao.IUserDao;
import com.wx.handle.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    // 获取用户信息
    @Override
    public UserResult getUserInfo(int userId) {
        UserResult result = new UserResult();
        try {
            User user = userDao.getUserById(userId);
            if(user == null){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.USER_NO_USED);
                return result;
            }
            if(user.getStatus() != 0){
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.USER_NO_USED);
                return result;
            }
            BeanUtils.copyProperties(user,result);
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }
        return result;
    }
}
