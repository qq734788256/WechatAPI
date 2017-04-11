package com.wx.handle.system.service.impl;

import com.wx.base.entity.User;
import com.wx.base.param.system.LoginParam;
import com.wx.base.param.system.WxOpenParam;
import com.wx.base.result.system.LoginResult;
import com.wx.base.result.system.WxOpenIdResult;
import com.wx.base.result.system.WxOpenResult;
import com.wx.base.token.UserToken;
import com.wx.base.util.*;
import com.wx.handle.system.service.ISystemService;
import com.wx.handle.user.dao.IUserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemServiceImpl implements ISystemService {

    @Resource
    private IUserDao userDao;

    // 用户登录
    @Override
    public LoginResult login(LoginParam param) {
        LoginResult result = new LoginResult();
        try {
            // 查询用户
            Map<String,String> queryParam = new HashMap<>();
            if(CommonUtil.isPhoneNumber(param.getUserName())){
                queryParam.put("phone", param.getUserName());
            } else {
                queryParam.put("cardNumber", param.getUserName());
            }
            User user = userDao.getUserInfo(queryParam);

            // 逻辑判断
            if(user == null){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.LOGIN_PARAM_ERROR);
                return result;
            }
            if(user.getStatus() == 1){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.USER_NO_USED);
                return result;
            }
            if(!user.getPassword().equals(MD5.parse(param.getPassword()))){
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage(ResultStatus.LOGIN_PARAM_ERROR);
                return result;
            }

            // 用户信息处理
            UserToken userToken = new UserToken();
            BeanUtils.copyProperties(user,userToken);

            String token = AesUtil.getUserToken(JacksonUtil.toJson(userToken));
            result.setToken(token);
            result.setUserName(user.getUserName());
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }
        return result;
    }

    @Override
    public WxOpenResult getWxOpenID(WxOpenParam param) {
        WxOpenResult result = new WxOpenResult();
        try{
            String returnInfo = HttpClientUtil.connectPostHttps("https://api.weixin.qq.com/sns/jscode2session?appid="+CommonInfo.APP_ID+"&secret="+CommonInfo.APP_SECRET+"&js_code="+param.getCode()+"&grant_type=authorization_code",new HashMap<String, String>());
            WxOpenIdResult wxOpenIdResult = JacksonUtil.toObject(returnInfo,WxOpenIdResult.class);
            result.setOpen(AesUtil.getUserPassword(wxOpenIdResult.getOpenid()));
        } catch (Exception ex) {

        }

        return null;
    }
}
