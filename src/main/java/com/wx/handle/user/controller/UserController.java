package com.wx.handle.user.controller;

import com.wx.base.result.User.UserResult;
import com.wx.handle.user.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/info")
    public UserResult getUserInfo(){
        return userService.getUserInfo();
    }
}
