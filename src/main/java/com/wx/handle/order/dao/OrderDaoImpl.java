package com.wx.handle.order.dao;

import com.wx.handle.order.mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OrderDaoImpl {

    @Resource
    private IOrderMapper orderMapper;

}
