package com.wx.base.param;

/**
 * 创建订单参数类
 * Created by wangyunlong on 2017/8/21.
 */
public class InitOrderParam extends BaseParam {
    /**
     * 订单信息
     */
    private String orderInfo;
    /**
     * 经度
     */
    private String latitude;
    /**
     * 维度
     */
    private String logitude;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }
}
