package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelParkCar {
    private String carPlate;
    private String carNature;
    private String parkingSpace;//根据commonuntil判断
    private String payruleidstr;
    private String beginDate;
    private String endDate;
    private String stopTime;
    private String realname;
    private String phone;
    private String address;
    private String status;//根据commonuntil判断
    private Integer parkingNo;
    private String parkCode;
    private String walletBanance;
//    private String portId;

    @ExcelTitle(value = "车牌",order=1)
    public String getCarPlate() {
        return carPlate;
    }
    @ExcelTitle(value = "登记车性质",order=2)
    public String getCarNature() {
        return carNature;
    }
    @ExcelTitle(value = "车位性质",order=3)
    public String getParkingSpace() {
        return parkingSpace;
    }
    @ExcelTitle(value = "登记车规则",order=4)
    public String getPayruleidstr() {
        return payruleidstr;
    }
    @ExcelTitle(value = "开始时间",order=5)
    public String getBeginDate() {
        return beginDate;
    }
    @ExcelTitle(value = "到期时间",order=6)
    public String getEndDate() {
        return endDate;
    }
    @ExcelTitle(value = "暂停时间",order=7)
    public String getStopTime() {
        return stopTime;
    }
    @ExcelTitle(value = "联系人",order=8)
    public String getRealname() {
        return realname;
    }
    @ExcelTitle(value = "电话",order=9)
    public String getPhone() {
        return phone;
    }
    @ExcelTitle(value = "住宅",order=10)
    public String getAddress() {
        return address;
    }
    @ExcelTitle(value = "状态",order=11)
    public String getStatus() {
        return status;
    }
    @ExcelTitle(value = "车位数",order=12)
    public Integer getParkingNo() {
        return parkingNo;
    }
    @ExcelTitle(value = "车位编码",order=13)
    public String getParkCode() {
        return parkCode;
    }
    @ExcelTitle(value = "钱包余额",order=14)
    public String getWalletBanance() {
        return walletBanance;
    }
    /*    @ExcelTitle(value = "通道权限",order=13)
    public String getPortId() {
        return portId;
    }*/


    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setCarNature(Integer carNatureStr) {
        for(int i=0;i<CommonUtil.carNature.size();i++){
            if(carNatureStr==CommonUtil.carNature.get(i).getId()){
                this.carNature = CommonUtil.carNature.get(i).getName();
            }
        }
    }

    public void setParkingSpaceStr(Integer parkingSpaceStr) {
        this.parkingSpace = CommonUtil.parkingSpaceList.get(parkingSpaceStr-1).getName();
    }

    public void setPayruleidstr(String payruleidstr) {
        this.payruleidstr = payruleidstr;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(Short statusStr) {
        this.status = CommonUtil.status.get(statusStr-1).getName();
    }

    public void setParkingNo(Integer parkingNo) {
        this.parkingNo = parkingNo;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public void setWalletBanance(String walletBanance) {
        this.walletBanance = walletBanance;
    }

    //    public void setPortId(String portId) {
//        this.portId = portId;
//    }
}
