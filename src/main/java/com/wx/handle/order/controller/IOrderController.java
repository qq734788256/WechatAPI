package com.wx.handle.order.controller;

import com.wx.handle.order.service.IOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单类
 */
@RestController
@RequestMapping("/order")
public class IOrderController {

    @Resource
    private IOrderService orderService;
}
