package com.common.entity.eparkingCloud;

public class TChargeDetailsReport {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.park_id
     *
     * @mbg.generated
     */
    private Integer parkId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.company_id
     *
     * @mbg.generated
     */
    private Integer companyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.charge_date
     *
     * @mbg.generated
     */
    private String chargeDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.car_nature_desc
     *
     * @mbg.generated
     */
    private String carNatureDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.charge_amount
     *
     * @mbg.generated
     */
    private Integer chargeAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.needpay_total
     *
     * @mbg.generated
     */
    private Double needpayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.actualpay_total
     *
     * @mbg.generated
     */
    private Double actualpayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.qpasspay_total
     *
     * @mbg.generated
     */
    private Double qpasspayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.behalfpay_total
     *
     * @mbg.generated
     */
    private Double behalfpayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.prepay_total
     *
     * @mbg.generated
     */
    private Double prepayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.coupon_total
     *
     * @mbg.generated
     */
    private Double couponTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.create_time
     *
     * @mbg.generated
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.duty_person
     *
     * @mbg.generated
     */
    private String dutyPerson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.out_port_id
     *
     * @mbg.generated
     */
    private Integer outPortId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.walletpay_total
     *
     * @mbg.generated
     */
    private Double walletpayTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_charge_details_report.inaccesspaid_total
     *
     * @mbg.generated
     */
    private Double inaccesspaidTotal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.id
     *
     * @return the value of t_charge_details_report.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.id
     *
     * @param id the value for t_charge_details_report.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.park_id
     *
     * @return the value of t_charge_details_report.park_id
     *
     * @mbg.generated
     */
    public Integer getParkId() {
        return parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.park_id
     *
     * @param parkId the value for t_charge_details_report.park_id
     *
     * @mbg.generated
     */
    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.company_id
     *
     * @return the value of t_charge_details_report.company_id
     *
     * @mbg.generated
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.company_id
     *
     * @param companyId the value for t_charge_details_report.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.charge_date
     *
     * @return the value of t_charge_details_report.charge_date
     *
     * @mbg.generated
     */
    public String getChargeDate() {
        return chargeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.charge_date
     *
     * @param chargeDate the value for t_charge_details_report.charge_date
     *
     * @mbg.generated
     */
    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate == null ? null : chargeDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.car_nature_desc
     *
     * @return the value of t_charge_details_report.car_nature_desc
     *
     * @mbg.generated
     */
    public String getCarNatureDesc() {
        return carNatureDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.car_nature_desc
     *
     * @param carNatureDesc the value for t_charge_details_report.car_nature_desc
     *
     * @mbg.generated
     */
    public void setCarNatureDesc(String carNatureDesc) {
        this.carNatureDesc = carNatureDesc == null ? null : carNatureDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.charge_amount
     *
     * @return the value of t_charge_details_report.charge_amount
     *
     * @mbg.generated
     */
    public Integer getChargeAmount() {
        return chargeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.charge_amount
     *
     * @param chargeAmount the value for t_charge_details_report.charge_amount
     *
     * @mbg.generated
     */
    public void setChargeAmount(Integer chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.needpay_total
     *
     * @return the value of t_charge_details_report.needpay_total
     *
     * @mbg.generated
     */
    public Double getNeedpayTotal() {
        return needpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.needpay_total
     *
     * @param needpayTotal the value for t_charge_details_report.needpay_total
     *
     * @mbg.generated
     */
    public void setNeedpayTotal(Double needpayTotal) {
        this.needpayTotal = needpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.actualpay_total
     *
     * @return the value of t_charge_details_report.actualpay_total
     *
     * @mbg.generated
     */
    public Double getActualpayTotal() {
        return actualpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.actualpay_total
     *
     * @param actualpayTotal the value for t_charge_details_report.actualpay_total
     *
     * @mbg.generated
     */
    public void setActualpayTotal(Double actualpayTotal) {
        this.actualpayTotal = actualpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.qpasspay_total
     *
     * @return the value of t_charge_details_report.qpasspay_total
     *
     * @mbg.generated
     */
    public Double getQpasspayTotal() {
        return qpasspayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.qpasspay_total
     *
     * @param qpasspayTotal the value for t_charge_details_report.qpasspay_total
     *
     * @mbg.generated
     */
    public void setQpasspayTotal(Double qpasspayTotal) {
        this.qpasspayTotal = qpasspayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.behalfpay_total
     *
     * @return the value of t_charge_details_report.behalfpay_total
     *
     * @mbg.generated
     */
    public Double getBehalfpayTotal() {
        return behalfpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.behalfpay_total
     *
     * @param behalfpayTotal the value for t_charge_details_report.behalfpay_total
     *
     * @mbg.generated
     */
    public void setBehalfpayTotal(Double behalfpayTotal) {
        this.behalfpayTotal = behalfpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.prepay_total
     *
     * @return the value of t_charge_details_report.prepay_total
     *
     * @mbg.generated
     */
    public Double getPrepayTotal() {
        return prepayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.prepay_total
     *
     * @param prepayTotal the value for t_charge_details_report.prepay_total
     *
     * @mbg.generated
     */
    public void setPrepayTotal(Double prepayTotal) {
        this.prepayTotal = prepayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.coupon_total
     *
     * @return the value of t_charge_details_report.coupon_total
     *
     * @mbg.generated
     */
    public Double getCouponTotal() {
        return couponTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.coupon_total
     *
     * @param couponTotal the value for t_charge_details_report.coupon_total
     *
     * @mbg.generated
     */
    public void setCouponTotal(Double couponTotal) {
        this.couponTotal = couponTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.create_time
     *
     * @return the value of t_charge_details_report.create_time
     *
     * @mbg.generated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.create_time
     *
     * @param createTime the value for t_charge_details_report.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.duty_person
     *
     * @return the value of t_charge_details_report.duty_person
     *
     * @mbg.generated
     */
    public String getDutyPerson() {
        return dutyPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.duty_person
     *
     * @param dutyPerson the value for t_charge_details_report.duty_person
     *
     * @mbg.generated
     */
    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson == null ? null : dutyPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.out_port_id
     *
     * @return the value of t_charge_details_report.out_port_id
     *
     * @mbg.generated
     */
    public Integer getOutPortId() {
        return outPortId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.out_port_id
     *
     * @param outPortId the value for t_charge_details_report.out_port_id
     *
     * @mbg.generated
     */
    public void setOutPortId(Integer outPortId) {
        this.outPortId = outPortId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.walletpay_total
     *
     * @return the value of t_charge_details_report.walletpay_total
     *
     * @mbg.generated
     */
    public Double getWalletpayTotal() {
        return walletpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.walletpay_total
     *
     * @param walletpayTotal the value for t_charge_details_report.walletpay_total
     *
     * @mbg.generated
     */
    public void setWalletpayTotal(Double walletpayTotal) {
        this.walletpayTotal = walletpayTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_charge_details_report.inaccesspaid_total
     *
     * @return the value of t_charge_details_report.inaccesspaid_total
     *
     * @mbg.generated
     */
    public Double getInaccesspaidTotal() {
        return inaccesspaidTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_charge_details_report.inaccesspaid_total
     *
     * @param inaccesspaidTotal the value for t_charge_details_report.inaccesspaid_total
     *
     * @mbg.generated
     */
    public void setInaccesspaidTotal(Double inaccesspaidTotal) {
        this.inaccesspaidTotal = inaccesspaidTotal;
    }
}