package com.wx.handle.order.service;

import com.wx.base.result.order.OrderListResult;

public interface IOrderService {

    /**
     * 获取订单列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    OrderListResult queryOrderList(int userId, int pageNo, int pageSize);
}
