package com.wx.handle.order.dao;

import com.wx.base.entity.Order;

import java.util.List;

public interface IOrderDao {
    List<Order> queryOrderList(int userId, int type, int pageNo, int pageSize);
}
