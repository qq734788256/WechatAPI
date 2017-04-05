package com.wx.handle.order.service.impl;

import com.wx.handle.order.dao.IOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl {

    @Resource
    private IOrderDao orderDao;
}
