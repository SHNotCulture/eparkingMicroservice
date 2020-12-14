package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelWalletRechargeRecord {
    private String carplate;
    private Double needPay;
    private Double actualPay;
    private Double difference;
    private String walletBanance;
    private String operType;
    private String payTime;
    private String payType;
    private String operator;
    private String remark;

//    private String address;

    @ExcelTitle(value = "车牌",order=1)
    public String getCarPlate() {
        return carplate;
    }
    @ExcelTitle(value = "应付",order=2)
    public Double getNeedPay() {
        return needPay;
    }
    @ExcelTitle(value = "实付",order=3)
    public Double getActualPay() {
        return actualPay;
    }
    @ExcelTitle(value = "差额",order=4)
    public Double getDifference() {
        return difference;
    }
    @ExcelTitle(value = "钱包余额",order=5)
    public String getWalletBanance() {
        return walletBanance;
    }
    @ExcelTitle(value = "操作类型",order=6)
    public String getOperType() {
        return operType;
    }
    @ExcelTitle(value = "支付时间",order=7)
    public String getPayTime() {
        return payTime;
    }
    @ExcelTitle(value = "充值方式",order=8)
    public String getPayType() {
        return payType;
    }
    @ExcelTitle(value = "操作员",order=9)
    public String getOperator() {
        return operator;
    }
    @ExcelTitle(value = "备注",order=10)
    public String getRemark() {
        return remark;
    }

/*    @ExcelTitle(value = "地址",order=11)
    public String getAddress() {
        return address;
    }*/

    public void setCarplate(String carplate) {
        this.carplate = carplate;
    }

    public void setNeedPay(Double needPay) {
        this.needPay = needPay;
    }

    public void setActualPay(Double actualPay) {
        this.actualPay = actualPay;
    }

    public void setDifference() {
        this.difference = this.actualPay-this.needPay;
    }

    public void setWalletBanance(String walletBanance) {
        this.walletBanance = walletBanance;
    }

    public void setOperType(Integer operTypeStr) {
        this.operType = CommonUtil.operTypeList.get(operTypeStr-1).getName();
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public void setPayType(Short payTypeStr) {
        this.payType = CommonUtil.payTypeList.get(payTypeStr-1).getName();
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public void setAddress(String address) {
//        this.address = address;
//    }
}
