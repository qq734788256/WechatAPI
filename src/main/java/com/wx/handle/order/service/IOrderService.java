package com.wx.handle.order.service;

import com.wx.base.entity.Order;
import com.wx.base.result.BaseResult;
import com.wx.base.result.order.OrderListResult;

public interface IOrderService {

    /**
     * 获取订单列表
     *
     * @param userId
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    OrderListResult queryOrderList(int userId, int type, int pageNo, int pageSize);

    /**
     * 初始化订单数据
     * @param order
     * @return
     */
    BaseResult initOrder(Order order);
}
