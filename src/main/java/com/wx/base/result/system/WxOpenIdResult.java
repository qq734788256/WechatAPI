package com.wx.base.result.system;

import java.io.Serializable;

public class WxOpenIdResult implements Serializable {
    private static final long serialVersionUID = -1288020043246117318L;

    private int errcode;
    private String errmsg;
    private String openid;
    private String session_key;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
