package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelBlacklist {
    private String carPlate;
    private String createTime;
    private String createPerson;
    private String beginTime;
    private String endTime;

    @ExcelTitle(value = "车牌",order=1)
    public String getCarPlate() {
        return carPlate;
    }
    @ExcelTitle(value = "修改时间",order=2)
    public String getCreateTime() {
        return createTime;
    }
    @ExcelTitle(value = "修改人",order=3)
    public String getCreatePerson() {
        return createPerson;
    }
    @ExcelTitle(value = "起始时间",order=4)
    public String getBeginTime() {
        return beginTime;
    }
    @ExcelTitle(value = "终止时间",order=5)
    public String getEndTime() {
        return endTime;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
