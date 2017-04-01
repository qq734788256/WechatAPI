package com.wx.handle.system.controller;

import com.wx.base.param.system.LoginParam;
import com.wx.base.result.system.LoginResult;
import com.wx.base.util.CommonUtil;
import com.wx.base.util.ResultStatus;
import com.wx.handle.system.service.ISystemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class SystemController {

    @Resource
    private ISystemService systemService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResult doLogin(@RequestBody LoginParam param){
        if(CommonUtil.isBlank(param.getUserName()) || CommonUtil.isBlank(param.getPassword())){
            LoginResult result = new LoginResult();
            result.setStatusCode(ResultStatus.FAIL_CODE);
            result.setResult(ResultStatus.FAIL);
            result.setMessage(ResultStatus.LOGIN_PARAM_NULL);
            return  result;
        }
        return systemService.login(param);
    }
}
