package com.wx.handle.user.controller;

import com.wx.base.param.user.AddUserParam;
import com.wx.base.param.user.UpdatePasswordParam;
import com.wx.base.result.BaseResult;
import com.wx.base.result.user.UserResult;
import com.wx.handle.common.UserUtil;
import com.wx.handle.user.service.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/info")
    public UserResult getUserInfo(HttpServletRequest request){
        int userId = UserUtil.getUserId(request);
        return userService.getUserInfo(userId);
    }

    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public BaseResult updatePassword(@RequestBody UpdatePasswordParam param, HttpServletRequest request){
        int userId = UserUtil.getUserId(request);
        return userService.updatePassword(userId,param.getOldPassword(),param.getNewPassword());
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResult addUser(@RequestBody AddUserParam param){
        return null;
    }
}
