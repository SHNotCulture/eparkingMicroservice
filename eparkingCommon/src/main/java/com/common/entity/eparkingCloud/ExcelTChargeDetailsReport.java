package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelTChargeDetailsReport {
    private String chargeDate;
    private String carNatureDesc;
    private Integer chargeAmount;
    private Double needpayTotal;
    private Double actualpayTotal;
    private Double qpasspayTotal;
    private Double walletpayTotal;
    private Double behalfpayTotal;
    private Double prepayTotal;
    private Double inaccesspaidTotal;
    private Double couponTotal;
    private Double difference;
    private String createTime;

    @ExcelTitle(value = "日期",order=1)
    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    @ExcelTitle(value = "车辆性质",order=2)
    public String getCarNatureDesc() {
        return carNatureDesc;
    }

    public void setCarNatureDesc(String carNatureDesc) {
        this.carNatureDesc = carNatureDesc;
    }

    @ExcelTitle(value = "次数",order=3)
    public Integer getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Integer chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @ExcelTitle(value = "总应收",order=4)
    public Double getNeedpayTotal() {
        return needpayTotal;
    }

    public void setNeedpayTotal(Double needpayTotal) {
        this.needpayTotal = needpayTotal;
    }

    @ExcelTitle(value = "总现金",order=5)
    public Double getActualpayTotal() {
        return actualpayTotal;
    }

    public void setActualpayTotal(Double actualpayTotal) {
        this.actualpayTotal = actualpayTotal;
    }

    @ExcelTitle(value = "总聚合",order=6)
    public Double getQpasspayTotal() {
        return this.qpasspayTotal-this.walletpayTotal;
    }

    public void setQpasspayTotal(Double qpasspayTotal) {
        this.qpasspayTotal = qpasspayTotal;
    }

    @ExcelTitle(value = "钱包支付",order=7)
    public Double getWalletpayTotal() {
        return walletpayTotal;
    }

    public void setWalletpayTotal(Double walletpayTotal) {
        this.walletpayTotal = walletpayTotal;
    }

    @ExcelTitle(value = "支付成功未抬杆",order=8)
    public Double getInaccesspaidTotal() {
        return inaccesspaidTotal;
    }

    public void setInaccesspaidTotal(Double inaccesspaidTotal) {
        this.inaccesspaidTotal = inaccesspaidTotal;
    }

    @ExcelTitle(value = "总预缴",order=9)
    public Double getPrepayTotal() {
        return prepayTotal;
    }

    public void setPrepayTotal(Double prepayTotal) {
        this.prepayTotal = prepayTotal;
    }

    @ExcelTitle(value = "总代缴",order=10)
    public Double getBehalfpayTotal() {
        return behalfpayTotal;
    }

    public void setBehalfpayTotal(Double behalfpayTotal) {
        this.behalfpayTotal = behalfpayTotal;
    }

    @ExcelTitle(value = "总减免",order=11)
    public Double getCouponTotal() {
        return couponTotal;
    }

    public void setCouponTotal(Double couponTotal) {
        this.couponTotal = couponTotal;
    }

    @ExcelTitle(value = "总差额",order=12)
    public Double getDifference() {
        return this.needpayTotal-this.actualpayTotal-this.qpasspayTotal-this.prepayTotal-this.behalfpayTotal-this.couponTotal;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    @ExcelTitle(value = "创建时间",order=13)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
