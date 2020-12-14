package com.common.entity.eparkingCloud;

/**
 * @Author jin
 * @Data 2019/1/16 10:46
 **/
public class ParkCarTask {
    private Integer member_id; // 会员id,, 对应线下的cascadid, 是本云端记录的id
    private Integer car_type; // 车型
    private String car_plate;
    private String realname;
    private String address;
    private String phone;
    private Integer show_card; // 是否刷卡 isReadCard
    private Integer mcard_status; //
    private String mcard_begin_date;
    private String mcard_end_date;
    private String lane_rights;
    private String p_card_code;
    private String p_card_id;
    private Integer isActivate; //是否激活 1 未激活 2 已激活
    private String delete;//true 代表是删除会员，否则不是
    private String ruleName;//转换为规则名称下载到本地，用于中医院定制，定制名称为："月租80元"
    private String bindingName;	//绑定名称也下载
    private Integer parkingSpace;//车位性质：1=机械车位，2=子母车位，3=普通车位
    private Integer isOwner;	//1=独占车位2=公用车位
    private String parkCode;//车位编码，多个用逗号隔开
    private Integer parkingNo;//拥有车位数
    private Integer isUse;
    private Integer cloud_park_car_id;
    //20190116新增，用于月租车每日有效时间
    private String dailyStartTime;//月租每日起始时间
    private String dailyEndTime;//月租每日截止时间
    //20201019新增：车辆性质，车辆计费规则
    private Integer carNature;
    private Integer tempPayRule;
    private String renewalTime;//续费时间
    private Integer bindingNum;//绑定车牌数量
    //20201125新增：钱包余额
    private String balance;//钱包余额

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getShow_card() {
        return show_card;
    }

    public void setShow_card(Integer show_card) {
        this.show_card = show_card;
    }

    public Integer getMcard_status() {
        return mcard_status;
    }

    public void setMcard_status(Integer mcard_status) {
        this.mcard_status = mcard_status;
    }

    public String getMcard_begin_date() {
        return mcard_begin_date;
    }

    public void setMcard_begin_date(String mcard_begin_date) {
        this.mcard_begin_date = mcard_begin_date;
    }

    public String getMcard_end_date() {
        return mcard_end_date;
    }

    public void setMcard_end_date(String mcard_end_date) {
        this.mcard_end_date = mcard_end_date;
    }

    public String getLane_rights() {
        return lane_rights;
    }

    public void setLane_rights(String lane_rights) {
        this.lane_rights = lane_rights;
    }

    public String getP_card_code() {
        return p_card_code;
    }

    public void setP_card_code(String p_card_code) {
        this.p_card_code = p_card_code;
    }

    public String getP_card_id() {
        return p_card_id;
    }

    public void setP_card_id(String p_card_id) {
        this.p_card_id = p_card_id;
    }

    public Integer getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(Integer isActivate) {
        this.isActivate = isActivate;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getBindingName() {
        return bindingName;
    }

    public void setBindingName(String bindingName) {
        this.bindingName = bindingName;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public Integer getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(Integer parkingNo) {
        this.parkingNo = parkingNo;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getCloud_park_car_id() {
        return cloud_park_car_id;
    }

    public void setCloud_park_car_id(Integer cloud_park_car_id) {
        this.cloud_park_car_id = cloud_park_car_id;
    }

    public String getDailyStartTime() {
        return dailyStartTime;
    }

    public void setDailyStartTime(String dailyStartTime) {
        this.dailyStartTime = dailyStartTime;
    }

    public String getDailyEndTime() {
        return dailyEndTime;
    }

    public void setDailyEndTime(String dailyEndTime) {
        this.dailyEndTime = dailyEndTime;
    }

    public Integer getCarNature() {
        return carNature;
    }

    public void setCarNature(Integer carNature) {
        this.carNature = carNature;
    }

    public Integer getTempPayRule() {
        return tempPayRule;
    }

    public void setTempPayRule(Integer tempPayRule) {
        this.tempPayRule = tempPayRule;
    }

    public String getRenewalTime() {
        return renewalTime;
    }

    public void setRenewalTime(String renewalTime) {
        this.renewalTime = renewalTime;
    }

    public Integer getBindingNum() {
        return bindingNum;
    }

    public void setBindingNum(Integer bindingNum) {
        this.bindingNum = bindingNum;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
