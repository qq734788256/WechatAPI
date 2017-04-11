package com.wx.base.param.system;

import com.wx.base.param.BaseParam;

/**
 * 获取微信OpenId参数
 */
public class WxOpenParam extends BaseParam {

    private static final long serialVersionUID = 7492063570194962805L;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
