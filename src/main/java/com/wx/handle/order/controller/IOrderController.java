package com.wx.handle.order.controller;

import com.wx.base.result.order.OrderListResult;
import com.wx.handle.common.UserUtil;
import com.wx.handle.order.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单类
 */
@RestController
@RequestMapping("/order")
public class IOrderController {

    @Resource
    private IOrderService orderService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public OrderListResult queryOrderList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize, HttpServletRequest request){
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageSize < 1){
            pageSize = 10;
        }
        int userId = UserUtil.getUserId(request);
        return orderService.queryOrderList(userId,pageNo,pageSize);
    }
}
