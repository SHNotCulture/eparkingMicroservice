package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelParkingRecordParkInOut {
    private String carNatureDesc;
    private String carStatus;
    private String chargeTypeDesc;
    private String outType;
    private String ePayType;
    private String inCarPlate;
    private String inPortName;
    private String outPortName;
    private String inTime;
    private String outTime;
    private String stopTime;
    private Double needPay;
    private Double actualPay;
    private Double qpassPay;
    private Double coupon;
    private Double beforePay;
    private Double localcoupon;
    private String couponType;
    private String dutyPerson;

    @ExcelTitle(value = "车辆性质",order=1)
    public String getCarNatureDesc() {
        return carNatureDesc;
    }

    @ExcelTitle(value = "车辆状态",order=2)
    public String getCarStatus() {
        return carStatus;
    }

    @ExcelTitle(value = "计费规则",order=3)
    public String getChargeTypeDesc() {
        return chargeTypeDesc;
    }

    @ExcelTitle(value = "出场方式",order=4)
    public String getOutType() {
        return outType;
    }

    @ExcelTitle(value = "支付方式",order=5)
    public String getePayType() {
        return ePayType;
    }

    @ExcelTitle(value = "车牌",order=6)
    public String getInCarPlate() {
        return inCarPlate;
    }

    @ExcelTitle(value = "入口",order=7)
    public String getInPortName() {
        return inPortName;
    }

    @ExcelTitle(value = "出口",order=8)
    public String getOutPortName() {
        return outPortName;
    }

    @ExcelTitle(value = "进场时间",order=9)
    public String getInTime() {
        return inTime;
    }

    @ExcelTitle(value = "出场时间",order=10)
    public String getOutTime() {
        return outTime;
    }

    @ExcelTitle(value = "停车时长",order=11)
    public String getStopTime() {
        return stopTime;
    }

    @ExcelTitle(value = "应收",order=12)
    public Double getNeedPay() {
        return needPay;
    }

    @ExcelTitle(value = "现金",order=13)
    public Double getActualPay() {
        return actualPay;
    }

    @ExcelTitle(value = "聚合",order=14)
    public Double getQpassPay() {
        return qpassPay;
    }

    @ExcelTitle(value = "代缴",order=15)
    public Double getCoupon() {
        return coupon;
    }

    @ExcelTitle(value = "预缴",order=16)
    public Double getBeforePay() {
        return beforePay;
    }

    @ExcelTitle(value = "减免",order=17)
    public Double getLocalcoupon() {
        return localcoupon;
    }

    @ExcelTitle(value = "减免类型",order=18)
    public String getCouponType() {
        return couponType;
    }

    @ExcelTitle(value = "当班员",order=19)
    public String getDutyPerson() {
        return dutyPerson;
    }

    public void setCarNatureDesc(String carNatureDesc) {
        this.carNatureDesc = carNatureDesc;
    }

    public void setCarStatus(Integer carStatusStr) {
        if(carStatusStr!=null){
            this.carStatus = CommonUtil.carStatusList.get(carStatusStr-1).getName();
        }else{
            this.carStatus ="";
        }
    }

    public void setChargeTypeDesc(String chargeTypeDesc) {
        this.chargeTypeDesc = chargeTypeDesc;
    }

    public void setOutType(Integer outTypeStr) {
        for(int i=0;i<CommonUtil.outTypeList.size();i++){
            if(outTypeStr==CommonUtil.outTypeList.get(i).getId()){
                this.outType = CommonUtil.outTypeList.get(i).getName();
            }
        }
    }

    public void setePayType(String ePayType) {
        this.ePayType = ePayType;
    }

    public void setInCarPlate(String inCarPlate) {
        this.inCarPlate = inCarPlate;
    }

    public void setInPortName(String inPortName) {
        this.inPortName = inPortName;
    }

    public void setOutPortName(String outPortName) {
        this.outPortName = outPortName;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public void setNeedPay(Double needPay) {
        this.needPay = needPay;
    }

    public void setActualPay(Double actualPay) {
        this.actualPay = actualPay;
    }

    public void setQpassPay(Double qpassPay) {
        this.qpassPay = qpassPay;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public void setBeforePay(Double beforePay) {
        this.beforePay = beforePay;
    }

    public void setLocalcoupon(Double localcoupon) {
        this.localcoupon = localcoupon;
    }

    public void setCouponType(Integer couponTypeStr) {
        if(couponTypeStr==null){
            this.couponType ="";
        }else{
            this.couponType = CommonUtil.busineCouponTypeList.get(couponTypeStr-1).getName();
        }
    }

    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson;
    }
/*  @ExcelTitle(value = "车牌",order=1)
    public String getInCarPlate() {
        return inCarPlate;
    }
    @ExcelTitle(value = "车辆类型",order=2)
    public String getCarNature() {
        return carNature;
    }
    @ExcelTitle(value = "进出场时间",order=3)
    public String getInOutTime() {
        return inOutTime;
    }
    @ExcelTitle(value = "停车时长",order=4)
    public String getStopTime() {
        return stopTime;
    }
    @ExcelTitle(value = "应收",order=5)
    public Double getNeedPay() {
        return needPay;
    }
    @ExcelTitle(value = "现金",order=6)
    public Double getActualPay() {
        return actualPay;
    }
    @ExcelTitle(value = "聚合",order=7)
    public Double getQpassPay() {
        return qpassPay;
    }
    @ExcelTitle(value = "代缴",order=8)
    public Double getCoupon() {
        return coupon;
    }
    @ExcelTitle(value = "预缴",order=9)
    public Double getBeforePay() {
        return beforePay;
    }
    @ExcelTitle(value = "减免",order=10)
    public Double getLocalcoupon() {
        return localcoupon;
    }
    @ExcelTitle(value = "出场方式",order=11)
    public String getOutTypeDesc() {
        return outTypeDesc;
    }
    @ExcelTitle(value = "当班员",order=12)
    public String getDutyPerson() {
        return dutyPerson;
    }
    @ExcelTitle(value = "说明",order=13)
    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setInCarPlate(String inCarPlate) {
        this.inCarPlate = inCarPlate;
    }

    public void setCarNature(Integer carNatureStr) {
        this.carNature = CommonUtil.carNature.get(carNatureStr-1).getName();
    }


    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public void setNeedPay(Double needPay) {
        this.needPay = needPay;
    }

    public void setActualPay(Double actualPay) {
        this.actualPay = actualPay;
    }

    public void setQpassPay(Double qpassPay) {
        this.qpassPay = qpassPay;
    }

    public void setCoupon(Double coupon) {
        this.coupon = coupon;
    }

    public void setBeforePay(Double beforePay) {
        this.beforePay = beforePay;
    }

    public void setLocalcoupon(Double localcoupon) {
        this.localcoupon = localcoupon;
    }

    public void setOutTypeDesc(String outTypeDesc) {
        this.outTypeDesc = outTypeDesc;
    }

    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
        this.inOutTime=inTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
        this.inOutTime=this.inOutTime+"*"+outTime;
    }*/

}
