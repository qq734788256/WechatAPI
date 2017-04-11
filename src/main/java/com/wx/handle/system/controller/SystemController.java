package com.wx.handle.system.controller;

import com.wx.base.param.system.LoginParam;
import com.wx.base.param.system.WxOpenParam;
import com.wx.base.result.BaseResult;
import com.wx.base.result.system.LoginResult;
import com.wx.base.result.system.WxOpenResult;
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

    /**
     * 登录
     * @param param
     * @return
     */
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

    /**
     * 用户失效或token错误
     * @return
     */
    @RequestMapping(value = "/token/error")
    public BaseResult tokenError(){
        BaseResult result = new BaseResult();
        result.setStatusCode(ResultStatus.TOKEN_ERROR_CODE);
        result.setResult(ResultStatus.FAIL);
        result.setMessage(ResultStatus.USER_INVILIDE);
        return result;
    }

    @RequestMapping(value = "test")
    public BaseResult testToken(){
        return new BaseResult();
    }

    @RequestMapping(value = "/openid",method = RequestMethod.POST)
    public WxOpenResult getWxOpenID(@RequestBody WxOpenParam param){
        if(CommonUtil.isBlank(param.getCode())){
            WxOpenResult result = new WxOpenResult();
            result.setStatusCode(ResultStatus.FAIL_CODE);
            result.setResult(ResultStatus.FAIL);
            result.setMessage(ResultStatus.PARAM_ERROR);
            return result;
        }
        return systemService.getWxOpenID(param);
    }
}
