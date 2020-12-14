package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TParkInOutToday;
import com.common.entity.eparkingCloud.TParkInOutTodayCriteria;
import com.eparkingdb.dao.sqlProvider.TParkInOutTodaySqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TParkInOutTodayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @SelectProvider(type= TParkInOutTodaySqlProvider.class, method="countByExample")
    long countByExample(TParkInOutTodayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TParkInOutTodaySqlProvider.class, method="deleteByExample")
    int deleteByExample(TParkInOutTodayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_park_in_out_today",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_park_in_out_today (id, actual_pay, ",
        "before_pay, car_nature, ",
        "charge_type, company_id, ",
        "in_car_plate, in_card_code, ",
        "in_pic_path, in_port_id, ",
        "in_port_name, in_time, ",
        "in_type, in_type_desc, ",
        "need_pay, occur_time, ",
        "out_car_plate, out_car_type, ",
        "out_card_code, out_pic_path, ",
        "out_port_id, out_port_name, ",
        "out_time, out_type, ",
        "out_type_desc, park_id, ",
        "stop_time, in_split_time, ",
        "out_split_time, first_in_time, ",
        "day_pay, operating_id, ",
        "operating_name, coupon, ",
        "qpass_pay, merchant_id, ",
        "secTime_Payed, secTime_Start, ",
        "ePayType, statistics_flog, ",
        "inOut_status, coupon_type, ",
        "billing_status)",
        "values (#{id,jdbcType=INTEGER}, #{actualPay,jdbcType=DOUBLE}, ",
        "#{beforePay,jdbcType=DOUBLE}, #{carNature,jdbcType=INTEGER}, ",
        "#{chargeType,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{inCarPlate,jdbcType=VARCHAR}, #{inCardCode,jdbcType=VARCHAR}, ",
        "#{inPicPath,jdbcType=VARCHAR}, #{inPortId,jdbcType=INTEGER}, ",
        "#{inPortName,jdbcType=VARCHAR}, #{inTime,jdbcType=VARCHAR}, ",
        "#{inType,jdbcType=INTEGER}, #{inTypeDesc,jdbcType=VARCHAR}, ",
        "#{needPay,jdbcType=DOUBLE}, #{occurTime,jdbcType=VARCHAR}, ",
        "#{outCarPlate,jdbcType=VARCHAR}, #{outCarType,jdbcType=INTEGER}, ",
        "#{outCardCode,jdbcType=VARCHAR}, #{outPicPath,jdbcType=VARCHAR}, ",
        "#{outPortId,jdbcType=INTEGER}, #{outPortName,jdbcType=VARCHAR}, ",
        "#{outTime,jdbcType=VARCHAR}, #{outType,jdbcType=INTEGER}, ",
        "#{outTypeDesc,jdbcType=VARCHAR}, #{parkId,jdbcType=INTEGER}, ",
        "#{stopTime,jdbcType=VARCHAR}, #{inSplitTime,jdbcType=VARCHAR}, ",
        "#{outSplitTime,jdbcType=VARCHAR}, #{firstInTime,jdbcType=VARCHAR}, ",
        "#{dayPay,jdbcType=DOUBLE}, #{operatingId,jdbcType=INTEGER}, ",
        "#{operatingName,jdbcType=VARCHAR}, #{coupon,jdbcType=DOUBLE}, ",
        "#{qpassPay,jdbcType=DOUBLE}, #{merchantId,jdbcType=INTEGER}, ",
        "#{sectimePayed,jdbcType=DOUBLE}, #{sectimeStart,jdbcType=VARCHAR}, ",
        "#{epaytype,jdbcType=INTEGER}, #{statisticsFlog,jdbcType=INTEGER}, ",
        "#{inoutStatus,jdbcType=INTEGER}, #{couponType,jdbcType=INTEGER}, ",
        "#{billingStatus,jdbcType=VARCHAR})"
    })
    int insert(TParkInOutToday record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @InsertProvider(type=TParkInOutTodaySqlProvider.class, method="insertSelective")
    int insertSelective(TParkInOutToday record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @SelectProvider(type=TParkInOutTodaySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="actual_pay", property="actualPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="before_pay", property="beforePay", jdbcType=JdbcType.DOUBLE),
        @Result(column="car_nature", property="carNature", jdbcType=JdbcType.INTEGER),
        @Result(column="charge_type", property="chargeType", jdbcType=JdbcType.INTEGER),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="in_car_plate", property="inCarPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_card_code", property="inCardCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_pic_path", property="inPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_port_id", property="inPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="in_port_name", property="inPortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_time", property="inTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_type", property="inType", jdbcType=JdbcType.INTEGER),
        @Result(column="in_type_desc", property="inTypeDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="need_pay", property="needPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_car_plate", property="outCarPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_car_type", property="outCarType", jdbcType=JdbcType.INTEGER),
        @Result(column="out_card_code", property="outCardCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_pic_path", property="outPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_port_id", property="outPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="out_port_name", property="outPortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_time", property="outTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_type", property="outType", jdbcType=JdbcType.INTEGER),
        @Result(column="out_type_desc", property="outTypeDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="stop_time", property="stopTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_split_time", property="inSplitTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_split_time", property="outSplitTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_in_time", property="firstInTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="day_pay", property="dayPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="operating_id", property="operatingId", jdbcType=JdbcType.INTEGER),
        @Result(column="operating_name", property="operatingName", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon", property="coupon", jdbcType=JdbcType.DOUBLE),
        @Result(column="qpass_pay", property="qpassPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="merchant_id", property="merchantId", jdbcType=JdbcType.INTEGER),
        @Result(column="secTime_Payed", property="sectimePayed", jdbcType=JdbcType.DOUBLE),
        @Result(column="secTime_Start", property="sectimeStart", jdbcType=JdbcType.VARCHAR),
        @Result(column="ePayType", property="epaytype", jdbcType=JdbcType.INTEGER),
        @Result(column="statistics_flog", property="statisticsFlog", jdbcType=JdbcType.INTEGER),
        @Result(column="inOut_status", property="inoutStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.INTEGER),
        @Result(column="billing_status", property="billingStatus", jdbcType=JdbcType.VARCHAR)
    })
    List<TParkInOutToday> selectByExample(TParkInOutTodayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, actual_pay, before_pay, car_nature, charge_type, company_id, in_car_plate, ",
        "in_card_code, in_pic_path, in_port_id, in_port_name, in_time, in_type, in_type_desc, ",
        "need_pay, occur_time, out_car_plate, out_car_type, out_card_code, out_pic_path, ",
        "out_port_id, out_port_name, out_time, out_type, out_type_desc, park_id, stop_time, ",
        "in_split_time, out_split_time, first_in_time, day_pay, operating_id, operating_name, ",
        "coupon, qpass_pay, merchant_id, secTime_Payed, secTime_Start, ePayType, statistics_flog, ",
        "inOut_status, coupon_type, billing_status",
        "from t_park_in_out_today",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="actual_pay", property="actualPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="before_pay", property="beforePay", jdbcType=JdbcType.DOUBLE),
        @Result(column="car_nature", property="carNature", jdbcType=JdbcType.INTEGER),
        @Result(column="charge_type", property="chargeType", jdbcType=JdbcType.INTEGER),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="in_car_plate", property="inCarPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_card_code", property="inCardCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_pic_path", property="inPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_port_id", property="inPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="in_port_name", property="inPortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_time", property="inTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_type", property="inType", jdbcType=JdbcType.INTEGER),
        @Result(column="in_type_desc", property="inTypeDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="need_pay", property="needPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="occur_time", property="occurTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_car_plate", property="outCarPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_car_type", property="outCarType", jdbcType=JdbcType.INTEGER),
        @Result(column="out_card_code", property="outCardCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_pic_path", property="outPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_port_id", property="outPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="out_port_name", property="outPortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_time", property="outTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_type", property="outType", jdbcType=JdbcType.INTEGER),
        @Result(column="out_type_desc", property="outTypeDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="stop_time", property="stopTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_split_time", property="inSplitTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_split_time", property="outSplitTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_in_time", property="firstInTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="day_pay", property="dayPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="operating_id", property="operatingId", jdbcType=JdbcType.INTEGER),
        @Result(column="operating_name", property="operatingName", jdbcType=JdbcType.VARCHAR),
        @Result(column="coupon", property="coupon", jdbcType=JdbcType.DOUBLE),
        @Result(column="qpass_pay", property="qpassPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="merchant_id", property="merchantId", jdbcType=JdbcType.INTEGER),
        @Result(column="secTime_Payed", property="sectimePayed", jdbcType=JdbcType.DOUBLE),
        @Result(column="secTime_Start", property="sectimeStart", jdbcType=JdbcType.VARCHAR),
        @Result(column="ePayType", property="epaytype", jdbcType=JdbcType.INTEGER),
        @Result(column="statistics_flog", property="statisticsFlog", jdbcType=JdbcType.INTEGER),
        @Result(column="inOut_status", property="inoutStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.INTEGER),
        @Result(column="billing_status", property="billingStatus", jdbcType=JdbcType.VARCHAR)
    })
    TParkInOutToday selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TParkInOutTodaySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TParkInOutToday record, @Param("example") TParkInOutTodayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TParkInOutTodaySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TParkInOutToday record, @Param("example") TParkInOutTodayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TParkInOutTodaySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TParkInOutToday record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out_today
     *
     * @mbg.generated
     */
    @Update({
        "update t_park_in_out_today",
        "set actual_pay = #{actualPay,jdbcType=DOUBLE},",
          "before_pay = #{beforePay,jdbcType=DOUBLE},",
          "car_nature = #{carNature,jdbcType=INTEGER},",
          "charge_type = #{chargeType,jdbcType=INTEGER},",
          "company_id = #{companyId,jdbcType=INTEGER},",
          "in_car_plate = #{inCarPlate,jdbcType=VARCHAR},",
          "in_card_code = #{inCardCode,jdbcType=VARCHAR},",
          "in_pic_path = #{inPicPath,jdbcType=VARCHAR},",
          "in_port_id = #{inPortId,jdbcType=INTEGER},",
          "in_port_name = #{inPortName,jdbcType=VARCHAR},",
          "in_time = #{inTime,jdbcType=VARCHAR},",
          "in_type = #{inType,jdbcType=INTEGER},",
          "in_type_desc = #{inTypeDesc,jdbcType=VARCHAR},",
          "need_pay = #{needPay,jdbcType=DOUBLE},",
          "occur_time = #{occurTime,jdbcType=VARCHAR},",
          "out_car_plate = #{outCarPlate,jdbcType=VARCHAR},",
          "out_car_type = #{outCarType,jdbcType=INTEGER},",
          "out_card_code = #{outCardCode,jdbcType=VARCHAR},",
          "out_pic_path = #{outPicPath,jdbcType=VARCHAR},",
          "out_port_id = #{outPortId,jdbcType=INTEGER},",
          "out_port_name = #{outPortName,jdbcType=VARCHAR},",
          "out_time = #{outTime,jdbcType=VARCHAR},",
          "out_type = #{outType,jdbcType=INTEGER},",
          "out_type_desc = #{outTypeDesc,jdbcType=VARCHAR},",
          "park_id = #{parkId,jdbcType=INTEGER},",
          "stop_time = #{stopTime,jdbcType=VARCHAR},",
          "in_split_time = #{inSplitTime,jdbcType=VARCHAR},",
          "out_split_time = #{outSplitTime,jdbcType=VARCHAR},",
          "first_in_time = #{firstInTime,jdbcType=VARCHAR},",
          "day_pay = #{dayPay,jdbcType=DOUBLE},",
          "operating_id = #{operatingId,jdbcType=INTEGER},",
          "operating_name = #{operatingName,jdbcType=VARCHAR},",
          "coupon = #{coupon,jdbcType=DOUBLE},",
          "qpass_pay = #{qpassPay,jdbcType=DOUBLE},",
          "merchant_id = #{merchantId,jdbcType=INTEGER},",
          "secTime_Payed = #{sectimePayed,jdbcType=DOUBLE},",
          "secTime_Start = #{sectimeStart,jdbcType=VARCHAR},",
          "ePayType = #{epaytype,jdbcType=INTEGER},",
          "statistics_flog = #{statisticsFlog,jdbcType=INTEGER},",
          "inOut_status = #{inoutStatus,jdbcType=INTEGER},",
          "coupon_type = #{couponType,jdbcType=INTEGER},",
          "billing_status = #{billingStatus,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TParkInOutToday record);
}