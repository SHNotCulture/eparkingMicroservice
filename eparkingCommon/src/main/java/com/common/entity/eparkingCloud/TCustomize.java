package com.common.entity.eparkingCloud;

import java.util.List;

/**
 * @ClassName TCustomize
 * @Author jin
 * @Date 2018/9/28 19:10
 **/
public class TCustomize {
    Integer totalCarNo;
    Integer parkingNo;
    Integer privateParkingNo;
    Integer pauseParkingNo;
    Integer expiredParkingNo;
    Double monEle;//月租车电子收费
    Double monCash;//月租车现金
    Double temporaryEle;//临时车电子收费
    Double temporaryCash;//临时车现金
    Double yueEle;//当月电子收费
    Double yueCash;//当月现金收费
    List<Histogram> histogram;

    public List<Histogram> getHistogram() {
        return histogram;
    }

    public void setHistogram(List<Histogram> histogram) {
        this.histogram = histogram;
    }

    public Double getMonEle() {
        return monEle;
    }

    public void setMonEle(Double monEle) {
        this.monEle = monEle;
    }

    public Double getMonCash() {
        return monCash;
    }

    public void setMonCash(Double monCash) {
        this.monCash = monCash;
    }

    public Double getTemporaryEle() {
        return temporaryEle;
    }

    public void setTemporaryEle(Double temporaryEle) {
        this.temporaryEle = temporaryEle;
    }

    public Double getTemporaryCash() {
        return temporaryCash;
    }

    public void setTemporaryCash(Double temporaryCash) {
        this.temporaryCash = temporaryCash;
    }

    public Double getYueEle() {
        return yueEle;
    }

    public void setYueEle(Double yueEle) {
        this.yueEle = yueEle;
    }

    public Double getYueCash() {
        return yueCash;
    }

    public void setYueCash(Double yueCash) {
        this.yueCash = yueCash;
    }

    public Integer getTotalCarNo() {
        return totalCarNo;
    }

    public void setTotalCarNo(Integer totalCarNo) {
        this.totalCarNo = totalCarNo;
    }

    public Integer getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(Integer parkingNo) {
        this.parkingNo = parkingNo;
    }

    public Integer getPrivateParkingNo() {
        return privateParkingNo;
    }

    public void setPrivateParkingNo(Integer privateParkingNo) {
        this.privateParkingNo = privateParkingNo;
    }

    public Integer getPauseParkingNo() {
        return pauseParkingNo;
    }

    public void setPauseParkingNo(Integer pauseParkingNo) {
        this.pauseParkingNo = pauseParkingNo;
    }

    public Integer getExpiredParkingNo() {
        return expiredParkingNo;
    }

    public void setExpiredParkingNo(Integer expiredParkingNo) {
        this.expiredParkingNo = expiredParkingNo;
    }
}
