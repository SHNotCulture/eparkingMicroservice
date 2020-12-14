package com.common.entity.eparkingCloud;

public class TParkDuty {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.actual_total
     *
     * @mbg.generated
     */
    private Double actualTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.begin_time
     *
     * @mbg.generated
     */
    private String beginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.duty_person
     *
     * @mbg.generated
     */
    private String dutyPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.end_time
     *
     * @mbg.generated
     */
    private String endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.free_pass
     *
     * @mbg.generated
     */
    private Integer freePass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.last_charge_time
     *
     * @mbg.generated
     */
    private String lastChargeTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.manual_pass
     *
     * @mbg.generated
     */
    private Integer manualPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.match_pass
     *
     * @mbg.generated
     */
    private Integer matchPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.need_total
     *
     * @mbg.generated
     */
    private Double needTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.pay_online_total
     *
     * @mbg.generated
     */
    private Double payOnlineTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.sys_pass
     *
     * @mbg.generated
     */
    private Integer sysPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.company_id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.park_id
     *
     * @mbg.generated
     */
    private Integer parkId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.prepay_total
     *
     * @mbg.generated
     */
    private Double prepayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.qpasspay_total
     *
     * @mbg.generated
     */
    private Double qpasspayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.behalfpay_total
     *
     * @mbg.generated
     */
    private Double behalfpayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.noconfirm_pass
     *
     * @mbg.generated
     */
    private Integer noconfirmPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.localcoupon_total
     *
     * @mbg.generated
     */
    private Double localcouponTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_park_duty.updatecarplates_total
     *
     * @mbg.generated
     */
    private Integer updatecarplatesTotal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.id
     *
     * @return the value of t_park_duty.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.id
     *
     * @param id the value for t_park_duty.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.actual_total
     *
     * @return the value of t_park_duty.actual_total
     *
     * @mbg.generated
     */
    public Double getActualTotal() {
        return actualTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.actual_total
     *
     * @param actualTotal the value for t_park_duty.actual_total
     *
     * @mbg.generated
     */
    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.begin_time
     *
     * @return the value of t_park_duty.begin_time
     *
     * @mbg.generated
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.begin_time
     *
     * @param beginTime the value for t_park_duty.begin_time
     *
     * @mbg.generated
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.duty_person
     *
     * @return the value of t_park_duty.duty_person
     *
     * @mbg.generated
     */
    public String getDutyPerson() {
        return dutyPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.duty_person
     *
     * @param dutyPerson the value for t_park_duty.duty_person
     *
     * @mbg.generated
     */
    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson == null ? null : dutyPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.end_time
     *
     * @return the value of t_park_duty.end_time
     *
     * @mbg.generated
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.end_time
     *
     * @param endTime the value for t_park_duty.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.free_pass
     *
     * @return the value of t_park_duty.free_pass
     *
     * @mbg.generated
     */
    public Integer getFreePass() {
        return freePass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.free_pass
     *
     * @param freePass the value for t_park_duty.free_pass
     *
     * @mbg.generated
     */
    public void setFreePass(Integer freePass) {
        this.freePass = freePass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.last_charge_time
     *
     * @return the value of t_park_duty.last_charge_time
     *
     * @mbg.generated
     */
    public String getLastChargeTime() {
        return lastChargeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.last_charge_time
     *
     * @param lastChargeTime the value for t_park_duty.last_charge_time
     *
     * @mbg.generated
     */
    public void setLastChargeTime(String lastChargeTime) {
        this.lastChargeTime = lastChargeTime == null ? null : lastChargeTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.manual_pass
     *
     * @return the value of t_park_duty.manual_pass
     *
     * @mbg.generated
     */
    public Integer getManualPass() {
        return manualPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.manual_pass
     *
     * @param manualPass the value for t_park_duty.manual_pass
     *
     * @mbg.generated
     */
    public void setManualPass(Integer manualPass) {
        this.manualPass = manualPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.match_pass
     *
     * @return the value of t_park_duty.match_pass
     *
     * @mbg.generated
     */
    public Integer getMatchPass() {
        return matchPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.match_pass
     *
     * @param matchPass the value for t_park_duty.match_pass
     *
     * @mbg.generated
     */
    public void setMatchPass(Integer matchPass) {
        this.matchPass = matchPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.need_total
     *
     * @return the value of t_park_duty.need_total
     *
     * @mbg.generated
     */
    public Double getNeedTotal() {
        return needTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.need_total
     *
     * @param needTotal the value for t_park_duty.need_total
     *
     * @mbg.generated
     */
    public void setNeedTotal(Double needTotal) {
        this.needTotal = needTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.pay_online_total
     *
     * @return the value of t_park_duty.pay_online_total
     *
     * @mbg.generated
     */
    public Double getPayOnlineTotal() {
        return payOnlineTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.pay_online_total
     *
     * @param payOnlineTotal the value for t_park_duty.pay_online_total
     *
     * @mbg.generated
     */
    public void setPayOnlineTotal(Double payOnlineTotal) {
        this.payOnlineTotal = payOnlineTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.sys_pass
     *
     * @return the value of t_park_duty.sys_pass
     *
     * @mbg.generated
     */
    public Integer getSysPass() {
        return sysPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.sys_pass
     *
     * @param sysPass the value for t_park_duty.sys_pass
     *
     * @mbg.generated
     */
    public void setSysPass(Integer sysPass) {
        this.sysPass = sysPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.company_id
     *
     * @return the value of t_park_duty.company_id
     *
     * @mbg.generated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.company_id
     *
     * @param companyId the value for t_park_duty.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.park_id
     *
     * @return the value of t_park_duty.park_id
     *
     * @mbg.generated
     */
    public Integer getParkId() {
        return parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.park_id
     *
     * @param parkId the value for t_park_duty.park_id
     *
     * @mbg.generated
     */
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.prepay_total
     *
     * @return the value of t_park_duty.prepay_total
     *
     * @mbg.generated
     */
    public Double getPrepayTotal() {
        return prepayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.prepay_total
     *
     * @param prepayTotal the value for t_park_duty.prepay_total
     *
     * @mbg.generated
     */
    public void setPrepayTotal(Double prepayTotal) {
        this.prepayTotal = prepayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.qpasspay_total
     *
     * @return the value of t_park_duty.qpasspay_total
     *
     * @mbg.generated
     */
    public Double getQpasspayTotal() {
        return qpasspayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.qpasspay_total
     *
     * @param qpasspayTotal the value for t_park_duty.qpasspay_total
     *
     * @mbg.generated
     */
    public void setQpasspayTotal(Double qpasspayTotal) {
        this.qpasspayTotal = qpasspayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.behalfpay_total
     *
     * @return the value of t_park_duty.behalfpay_total
     *
     * @mbg.generated
     */
    public Double getBehalfpayTotal() {
        return behalfpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.behalfpay_total
     *
     * @param behalfpayTotal the value for t_park_duty.behalfpay_total
     *
     * @mbg.generated
     */
    public void setBehalfpayTotal(Double behalfpayTotal) {
        this.behalfpayTotal = behalfpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.noconfirm_pass
     *
     * @return the value of t_park_duty.noconfirm_pass
     *
     * @mbg.generated
     */
    public Integer getNoconfirmPass() {
        return noconfirmPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.noconfirm_pass
     *
     * @param noconfirmPass the value for t_park_duty.noconfirm_pass
     *
     * @mbg.generated
     */
    public void setNoconfirmPass(Integer noconfirmPass) {
        this.noconfirmPass = noconfirmPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.user_id
     *
     * @return the value of t_park_duty.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.user_id
     *
     * @param userId the value for t_park_duty.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.localcoupon_total
     *
     * @return the value of t_park_duty.localcoupon_total
     *
     * @mbg.generated
     */
    public Double getLocalcouponTotal() {
        return localcouponTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.localcoupon_total
     *
     * @param localcouponTotal the value for t_park_duty.localcoupon_total
     *
     * @mbg.generated
     */
    public void setLocalcouponTotal(Double localcouponTotal) {
        this.localcouponTotal = localcouponTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_park_duty.updatecarplates_total
     *
     * @return the value of t_park_duty.updatecarplates_total
     *
     * @mbg.generated
     */
    public Integer getUpdatecarplatesTotal() {
        return updatecarplatesTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_park_duty.updatecarplates_total
     *
     * @param updatecarplatesTotal the value for t_park_duty.updatecarplates_total
     *
     * @mbg.generated
     */
    public void setUpdatecarplatesTotal(Integer updatecarplatesTotal) {
        this.updatecarplatesTotal = updatecarplatesTotal;
    }
}