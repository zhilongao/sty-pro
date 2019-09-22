package com.study.zl.design;

/**
 * @Author long
 * @Date 2019/8/8 9:20
 */
public class OrderRequest {

    private String orderNo;
    private String orderName;


    public OrderRequest() {

    }

    public OrderRequest(String orderNo, String orderName) {
        this.orderNo = orderNo;
        this.orderName = orderName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return this.orderNo + "--" + this.orderName;
    }
}
