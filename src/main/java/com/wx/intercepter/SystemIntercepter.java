package com.wx.intercepter;

import com.wx.base.entity.User;
import com.wx.base.token.UserToken;
import com.wx.base.util.AesUtil;
import com.wx.base.util.CommonUtil;
import com.wx.base.util.JacksonUtil;
import com.wx.handle.user.dao.IUserDao;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用于拦截所有请求
 * 判断token
 *
 */
public class SystemIntercepter implements HandlerInterceptor {

    private List<String> uncheckUrls;

    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    @Resource
    private IUserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前访问连接
        String uri = request.getRequestURI();
        if(uncheckUrls.contains(uri)){
            return true;
        }
        // 获取token
        String token = request.getHeader("UToken");
        if(CommonUtil.isBlank(token)){
            request.getRequestDispatcher("/token/error.wx").forward(request,response);
            return false;
        }

        // 解析token
        try {
            UserToken userToken = JacksonUtil.toObject(AesUtil.getUserInfoByToken(token), UserToken.class);
            if(userToken == null){
                request.getRequestDispatcher("/token/error.wx").forward(request,response);
                return false;
            }
            // 获取用户
            User user = userDao.getUserById(userToken.getId());
            if(user == null || user.getStatus() != 0){
                // 用户为空或者被禁
                request.getRequestDispatcher("/token/error.wx").forward(request,response);
                return false;
            }
            if(user.getUpdateTime() != userToken.getUpdateTime()){
                // 用户信息更新
                request.getRequestDispatcher("/token/error.wx").forward(request,response);
                return false;
            }
        } catch (Exception e) {
            request.getRequestDispatcher("/token/error.wx").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
