package com.gupao.study.order.domain;

/**
 * @Author long
 * @Date 2019/8/5 7:32
 * 订单实体
 */

public class Order {
    private Long id;
    private Integer goodName;
    private String startAddress;
    private String endAddress;

    public Order() {
    }

    public Order(Long id, Integer goodName, String startAddress, String endAddress) {
        this.id = id;
        this.goodName = goodName;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoodName() {
        return goodName;
    }

    public void setGoodName(Integer goodName) {
        this.goodName = goodName;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }
}
