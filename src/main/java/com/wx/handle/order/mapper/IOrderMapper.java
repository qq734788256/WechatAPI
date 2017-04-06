package com.wx.handle.order.mapper;

import com.wx.base.entity.Order;

import java.util.List;
import java.util.Map;

public interface IOrderMapper {

    List<Order> queryOrderList(Map<String, Object> param);

}
