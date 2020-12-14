package com.common.entity.eparkingCloud;

/**
 * @Author jin
 * @Data 2019/3/22 10:54
 **/
public class Shop {
    private Integer client_id; // t_park_in_out的client_id
    private Double coupon_money; // 优惠金额
    private String car_num; // 车牌
    private String merchant_name; // 商家
    private String orderId;	//入场的订单号
    private Integer paymode;	//支付模式，要与下线一致
    private Integer merchant_Id;	//商户ID
    private String remark;	//描述
    private Double money_received;
    private String discount_time;//商户优惠时间
    //2019-05新增
    private Integer coupon_type;//优惠类型
    //2020-10-12 新增
    private String shopdiscount_outTimeStamp;//优惠截止时间

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Double getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(Double coupon_money) {
        this.coupon_money = coupon_money;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymode() {
        return paymode;
    }

    public void setPaymode(Integer paymode) {
        this.paymode = paymode;
    }

    public Integer getMerchant_Id() {
        return merchant_Id;
    }

    public void setMerchant_Id(Integer merchant_Id) {
        this.merchant_Id = merchant_Id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getMoney_received() {
        return money_received;
    }

    public void setMoney_received(Double money_received) {
        this.money_received = money_received;
    }

    public String getDiscount_time() {
        return discount_time;
    }

    public void setDiscount_time(String discount_time) {
        this.discount_time = discount_time;
    }

    public Integer getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(Integer coupon_type) {
        this.coupon_type = coupon_type;
    }

    public String getShopdiscount_outTimeStamp() {
        return shopdiscount_outTimeStamp;
    }

    public void setShopdiscount_outTimeStamp(String shopdiscount_outTimeStamp) {
        this.shopdiscount_outTimeStamp = shopdiscount_outTimeStamp;
    }
}
