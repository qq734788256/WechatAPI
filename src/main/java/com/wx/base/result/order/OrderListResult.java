package com.wx.base.result.order;

import com.wx.base.result.BaseResult;
import com.wx.base.vo.Order.OrderVO;

import java.util.List;

/**
 * 订单列表结果类
 */
public class OrderListResult extends BaseResult {

    private static final long serialVersionUID = 7777634600759448371L;

    private boolean haveNext;

    private List<OrderVO> orders;

    public List<OrderVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderVO> orders) {
        this.orders = orders;
    }

    public boolean isHaveNext() {
        return haveNext;
    }

    public void setHaveNext(boolean haveNext) {
        this.haveNext = haveNext;
    }
}
