package com.common.entity.eparkingCloud;

import com.common.util.CommonUtil;
import com.common.annotation.ExcelTitle;

public class ExcelTruckSpace {
    private String parkCode;
    private String parkingSpace;
    private String parkingState;
/*    private String theirCarPlate;
    private String useCarPlate;*/
    private Integer parkId;

    @ExcelTitle(value = "车位编码",order=1)
    public String getParkCode() {
        return parkCode;
    }
    @ExcelTitle(value = "车位性质",order=2)
    public String getParkingSpace() {
        return parkingSpace;
    }
    @ExcelTitle(value = "车位状态",order=3)
    public String getParkingState() {
        return parkingState;
    }
/*    @ExcelTitle(value = "绑定车牌",order=4)
    public String getTheirCarPlate() {
        return theirCarPlate;
    }
    @ExcelTitle(value = "使用车牌",order=5)
    public String getUseCarPlate() {
        return useCarPlate;
    }*/
    @ExcelTitle(value = "所属车场",order=4)
    public Integer getParkId() {
        return parkId;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = CommonUtil.parkingSpaceList.get(parkingSpace-1).getName();
    }

    public void setParkingState(Integer parkingState) {
        this.parkingState = CommonUtil.parkingStateList.get(parkingState).getName();
    }

/*    public void setTheirCarPlate(String theirCarPlate) {
        this.theirCarPlate = theirCarPlate;
    }

    public void setUseCarPlate(String useCarPlate) {
        this.useCarPlate = useCarPlate;
    }*/

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
}
