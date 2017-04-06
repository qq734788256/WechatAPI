package com.wx.handle.user.service.impl;

import com.wx.base.entity.User;
import com.wx.base.param.user.UpdatePasswordParam;
import com.wx.base.result.BaseResult;
import com.wx.base.result.user.UserResult;
import com.wx.base.util.MD5;
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
            if(user.getRoleId() == 0){
                result.setRole("超级管理员");
            } else if(user.getRoleId() == 1){
                result.setRole("普通会员");
            } else if(user.getRoleId() == 2){
                result.setRole("黄金会员");
            } else if(user.getRoleId() == 3){
                result.setRole("钻石会员");
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }
        return result;
    }

    @Override
    public BaseResult updatePassword(int userId, String oldPassword, String newPassword) {
        BaseResult result = new BaseResult();
        try {
            User user = userDao.getUserById(userId);
            if(user == null){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.USER_NO_USED);
                return result;
            }
            if(!user.getPassword().equals(MD5.parse(oldPassword))){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.OLD_PASSWORD_ERROR);
                return result;
            }
            user = new User();
            user.setId(userId);
            user.setPassword(MD5.parse(newPassword));
            if(userDao.updateUserInfo(user) < 1){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.OPRATE_FAIL);
                return result;
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }
        return result;
    }
}
