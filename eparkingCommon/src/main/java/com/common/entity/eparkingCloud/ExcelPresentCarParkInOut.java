package com.common.entity.eparkingCloud;


import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelPresentCarParkInOut {
    private String inCarPlate;
    private String inPortName;
    private String inTime;
    private String stopTime;
    private String carNatureDesc;//根据commonuntil判断

    @ExcelTitle(value = "车牌",order=1)
    public String getInCarPlate() {
        return inCarPlate;
    }

    @ExcelTitle(value = "车辆性质",order=2)
    public String getCarNatureDesc() {
        return carNatureDesc;
    }
    @ExcelTitle(value = "进入通道",order=3)
    public String getInPortName() {
        return inPortName;
    }
    @ExcelTitle(value = "进场时间",order=4)
    public String getInTime() {
        return inTime;
    }
    @ExcelTitle(value = "停车时长",order=5)
    public String getStopTime() {
        return stopTime;
    }


    public void setInCarPlate(String inCarPlate) {
        this.inCarPlate = inCarPlate;
    }

    public void setInPortName(String inPortName) {
        this.inPortName = inPortName;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public void setCarNatureDesc(String carNatureDesc) {
        this.carNatureDesc = carNatureDesc;
    }

    /*    public void setCarNature(Integer carNatureStr) {
        for(int i=0;i<CommonUtil.carNature.size();i++){
            if(carNatureStr==CommonUtil.carNature.get(i).getId()){
                this.carNature = CommonUtil.carNature.get(i).getName();
            }
        }

    }*/
}
