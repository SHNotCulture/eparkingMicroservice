package com.common.entity.eparkingCloud;

/** 同步会员信息到本地 */
public class MemberDown {
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
	
	//统一多车位到这里生成任务
	 private String parkCode;//停车位，多个用逗号隔开
     private Integer parkingNo;//拥有车位数
     private Integer isUse;	//
     //增加 cloud_park_car_id，对应t_park_car的id，慢慢替代member_id
     private Integer cloud_park_car_id;

	//20190116新增，用于月租车每日有效时间
	private String dailyStartTime;//月租每日起始时间
	private String dailyEndTime;//月租每日截止时间

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

	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer memberId) {
		member_id = memberId;
	}
	public String getCar_plate() {
		return car_plate;
	}
	public void setCar_plate(String carPlate) {
		car_plate = carPlate;
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
	
	public Integer getParkingSpace() {
		return parkingSpace;
	}
	public void setParkingSpace(Integer parkingSpace) {
		this.parkingSpace = parkingSpace;
	}
		
	public Integer getMcard_status() {
		return mcard_status;
	}
	public void setMcard_status(Integer mcardStatus) {
		mcard_status = mcardStatus;
	}
	public String getMcard_begin_date() {
		return mcard_begin_date;
	}
	public void setMcard_begin_date(String mcareparkingdbeginDate) {
		mcard_begin_date = mcareparkingdbeginDate;
	}
	public String getMcard_end_date() {
		return mcard_end_date;
	}
	public void setMcard_end_date(String mcardEndDate) {
		mcard_end_date = mcardEndDate;
	}
	public String getLane_rights() {
		return lane_rights;
	}
	public void setLane_rights(String laneRights) {
		lane_rights = laneRights;
	}
	public String getP_card_code() {
		return p_card_code;
	}
	public void setP_card_code(String pCardCode) {
		p_card_code = pCardCode;
	}
	public String getP_card_id() {
		return p_card_id;
	}
	public void setP_card_id(String pCardId) {
		p_card_id = pCardId;
	}
	public Integer getCar_type() {
		return car_type;
	}
	public void setCar_type(Integer carType) {
		car_type = carType;
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
	public Integer getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}
	public Integer getShow_card() {
		return show_card;
	}
	public void setShow_card(Integer showCard) {
		show_card = showCard;
	}
	public Integer getCloud_park_car_id() {
		return cloud_park_car_id;
	}
	public void setCloud_park_car_id(Integer cloudParkCarId) {
		cloud_park_car_id = cloudParkCarId;
	}
	
}
