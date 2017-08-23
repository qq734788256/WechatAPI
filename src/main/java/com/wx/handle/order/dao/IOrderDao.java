package com.wx.handle.order.dao;

import com.wx.base.entity.Order;

import java.util.List;

public interface IOrderDao {

    /**
     * 查询订单列表
     * @param userId
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Order> queryOrderList(int userId, int type, int pageNo, int pageSize);

    /**
     * 插入订单
     * @param order
     * @return
     */
    int insertOrder(Order order);
}
