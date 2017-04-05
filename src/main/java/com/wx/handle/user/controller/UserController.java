package com.wx.handle.user.controller;

import com.wx.base.result.user.UserResult;
import com.wx.base.token.UserToken;
import com.wx.handle.common.UserUtil;
import com.wx.handle.user.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
