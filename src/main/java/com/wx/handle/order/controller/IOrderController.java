package com.wx.handle.order.controller;

import com.wx.base.entity.Order;
import com.wx.base.param.InitOrderParam;
import com.wx.base.result.BaseResult;
import com.wx.base.result.order.OrderListResult;
import com.wx.base.util.MapDistance;
import com.wx.base.util.ResultStatus;
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

    /**
     * 获取订单列表
     * @param pageNo
     * @param pageSize
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public OrderListResult queryOrderList(@RequestParam(required = true) int pageNo, @RequestParam(required = true) int pageSize,
                                          @RequestParam(required = true) int type, HttpServletRequest request){
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageSize < 1){
            pageSize = 10;
        }
        int userId = UserUtil.getUserId(request);
        return orderService.queryOrderList(userId,type,pageNo,pageSize);
    }

    /**
     * 初始化创建订单
     * @param initOrderParam
     * @return
     */
    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public BaseResult initOrder(@RequestBody InitOrderParam initOrderParam, HttpServletRequest request){
        int userId = UserUtil.getUserId(request);
        // 判断距离
        Double jl = Double.parseDouble(MapDistance.getShopDistance(initOrderParam.getLatitude(), initOrderParam.getLogitude()));

        // 超出10KM不提供上门取件
        if(jl > 10){
            BaseResult result = new BaseResult();
            result.setStatusCode(ResultStatus.FAIL_CODE);
            result.setResult(ResultStatus.FAIL);
            result.setMessage(ResultStatus.BEYOND_SHOP);
            return result;
        }

        // 初始化订单
        Order order = new Order();
        order.setOrderContent(initOrderParam.getOrderInfo());
        order.setType(0);
        order.setUser(userId);

        // 创建订单
        BaseResult result = orderService.initOrder(order);

        return result;
    }
}
