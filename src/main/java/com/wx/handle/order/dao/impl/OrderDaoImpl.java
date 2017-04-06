package com.wx.handle.order.dao.impl;

import com.wx.base.entity.Order;
import com.wx.handle.order.dao.IOrderDao;
import com.wx.handle.order.mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements IOrderDao {

    @Resource
    private IOrderMapper orderMapper;

    @Override
    public List<Order> queryOrderList(int userId, int pageNo, int pageSize) {
        Map<String, Object> param = new HashMap<>();
        param.put("user", userId);
        param.put("index", (pageNo - 1) * pageSize);
        param.put("size", pageSize);
        return orderMapper.queryOrderList(param);
    }
}
