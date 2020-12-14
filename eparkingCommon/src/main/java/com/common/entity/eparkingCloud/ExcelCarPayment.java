package com.common.entity.eparkingCloud;

import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelCarPayment {

    private String carplate;
    private String beginDate;
    private String endDate;
    private Double actualPay;
    private String operType;
    private String payRuleStr;
    private String payTime;
//    private String operator;

    @ExcelTitle(value = "车牌", order = 1)
    public String getCarplate() {
        return carplate;
    }

    @ExcelTitle(value = "开始时间", order = 2)
    public String getBeginDate() {
        return beginDate;
    }

    @ExcelTitle(value = "截止时间", order = 3)
    public String getEndDate() {
        return endDate;
    }

    @ExcelTitle(value = "金额", order = 4)
    public Double getActualPay() {
        return actualPay;
    }

    @ExcelTitle(value = "支付类型", order = 5)
    public String getOperType() {
        return operType;
    }

    @ExcelTitle(value = "登记车规则", order = 6)
    public String getPayRuleStr() {
        return payRuleStr;
    }

    @ExcelTitle(value = "支付时间", order = 7)
    public String getPayTime() {
        return payTime;
    }
/*    @ExcelTitle(value = "操作人",order=8)
    public String getOperator() {
        return SessionUtil.getUser().getUserName();
    }*/

    public void setCarplate(String carplate) {
        this.carplate = carplate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setActualPay(Double actualPay) {
        this.actualPay = actualPay;
    }

    public void setOperType(Integer operTypeStr) {
        this.operType = CommonUtil.operTypeList.get(operTypeStr - 1).getName();
    }

    public void setPayRuleStr(String payRuleStr) {
        this.payRuleStr = payRuleStr;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

//    public void setOperator(String operator) {
//        this.operator = operator;
//    }
}
