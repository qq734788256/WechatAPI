package com.wx.base.entity;

import java.io.Serializable;

/**
 * 订单
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 5592623929949781856L;
    private int id;
    private String orderNumber;
    private String orderContent;
    private String orderTime;
    private String orderMoney;
    private int type;
    private int user;
    private int operateUser;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(int operateUser) {
        this.operateUser = operateUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
