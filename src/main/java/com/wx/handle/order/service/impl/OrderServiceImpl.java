package com.wx.handle.order.service.impl;

import com.wx.base.entity.Order;
import com.wx.base.result.BaseResult;
import com.wx.base.result.order.OrderListResult;
import com.wx.base.util.OrderUtil;
import com.wx.base.util.ResultStatus;
import com.wx.base.util.SysTimeUitl;
import com.wx.base.vo.Order.OrderVO;
import com.wx.handle.order.dao.IOrderDao;
import com.wx.handle.order.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDao orderDao;

    @Override
    public OrderListResult queryOrderList(int userId, int type, int pageNo, int pageSize) {
        OrderListResult result = new OrderListResult();

        try {
            result.setHaveNext(true);
            List<Order> orderList = orderDao.queryOrderList(userId,type,pageNo,pageSize);
            if(orderList == null || orderList.size() < 1){
                result.setHaveNext(false);
                return result;
            }
            if(orderList.size() < pageSize){
                result.setHaveNext(false);
            }
            OrderVO orderVO = null;
            List<OrderVO> orders = new ArrayList<>();
            for(Order order : orderList){
                orderVO = new OrderVO();
                BeanUtils.copyProperties(order, orderVO);
                orderVO.setTypeInfo(order.getType() == 0 ? "消费" : "充值");
                int status = order.getStatus();
                if(status == 0){
                    orderVO.setStatusInfo("未支付");
                } else if(status == 1){
                    orderVO.setStatusInfo("已支付");
                } else if(status == 2){
                    orderVO.setStatusInfo("已完成");
                } else if(status == 3){
                    orderVO.setStatusInfo("待商家确认");
                } else if(status == -1){
                    orderVO.setStatusInfo("已失效");
                }
                orders.add(orderVO);
            }
            result.setOrders(orders);
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }

        return result;
    }

    /**
     * 初始化订单数据
     * @param order
     * @return
     */
    @Override
    public BaseResult initOrder(Order order) {
        BaseResult result = new BaseResult();

        try {
            order.setOrderTime(String.valueOf(SysTimeUitl.getSystemTime()));
            order.setOrderNumber(OrderUtil.getOrderNumber());
            order.setStatus(3);
            int count = orderDao.insertOrder(order);
            if(count > 0){

            } else {
                result.setStatusCode(ResultStatus.FAIL_CODE);
                result.setResult(ResultStatus.FAIL);
                result.setMessage("");
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setStatusCode(ResultStatus.SERVER_ERROR_CODE);
            result.setResult(ResultStatus.ERROR);
            result.setMessage(ResultStatus.SERVER_ERROR);
        }

        return result;
    }
}
