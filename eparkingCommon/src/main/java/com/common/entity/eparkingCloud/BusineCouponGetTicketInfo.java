package com.common.entity.eparkingCloud;

/**
 * @author ：lishuhan
 * @date ：Created in 2020/12/3 14:05
 * @description：
 * @modified By：
 * @version: 1.0$
 */
public class BusineCouponGetTicketInfo {
    private Integer companyId;
    private Integer parkId;
    private Integer busineId;
    private Integer ticketId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getBusineId() {
        return busineId;
    }

    public void setBusineId(Integer busineId) {
        this.busineId = busineId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
