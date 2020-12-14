package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TParkInOut;
import com.common.entity.eparkingCloud.TParkInOutCriteria;
import com.eparkingdb.dao.sqlProvider.TParkInOutSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TParkInOutMapper {

   /* @SelectProvider(type= TParkInOutSqlProvider.class, method="sumPresentCar")
    Integer sumPresentCar(TParkInOutCriteria example);*/


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @SelectProvider(type = TParkInOutSqlProvider.class, method = "countByExample")
    long countByExample(TParkInOutCriteria example, Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @DeleteProvider(type = TParkInOutSqlProvider.class, method = "deleteByExample")
    int deleteByExample(TParkInOutCriteria example, Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @Delete({
            "delete from t_park_in_out",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @Insert({
            "insert into t_park_in_out (id, actual_pay, ",
            "before_pay, car_nature, ",
            "charge_type, company_id, ",
            "duty_person, in_car_plate, ",
            "in_card_code, in_pic_path, ",
            "in_port_id, in_port_name, ",
            "in_time, in_type,in_car_type, ",
            "in_type_desc, need_pay, ",
            "occur_time, out_car_plate, ",
            "out_car_type, out_card_code, ",
            "out_pic_path, out_port_id, ",
            "out_port_name, out_time, ",
            "out_type, out_type_desc, ",
            "park_id, stop_time, ",
            "client_id, in_split_time, ",
            "out_split_time, first_in_time, ",
            "day_pay, operating_id, ",
            "operating_name, coupon, ",
            "operating_type, pid, ",
            "order_id, qpass_pay, ",
            "merchant_id, secTime_Payed, ",
            "secTime_Start, ePayType, ",
            "gov_in_up_flag, gov_out_up_flag, ",
            "in_car_plate_color, out_car_plate_color, ",
            "prepay_type, localcoupon, ",
            "localcoupon_name, localCoupon_rule_id, ",
            "updatedInCarplate, updatedOutCarplate, ",
            "coupon_type, billing_status, ",
            "cloud_order_id, settle_date, ",
            "cash_pay_code, pre_pay_code, ",
            "behalf_pay_code, q_pass_pay_code, ",
            "free_pay_code, charge_type_desc, ",
            "remarks, car_nature_desc, ",
            "car_status) ",
            "values (#{id,jdbcType=VARCHAR}, #{actualPay,jdbcType=DOUBLE}, ",
            "#{beforePay,jdbcType=DOUBLE}, #{carNature,jdbcType=INTEGER}, ",
            "#{chargeType,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
            "#{dutyPerson,jdbcType=VARCHAR}, #{inCarPlate,jdbcType=VARCHAR}, ",
            "#{inCardCode,jdbcType=VARCHAR}, #{inPicPath,jdbcType=VARCHAR}, ",
            "#{inPortId,jdbcType=INTEGER}, #{inPortName,jdbcType=VARCHAR}, ",
            "#{inTime,jdbcType=VARCHAR}, #{inType,jdbcType=INTEGER}, ",
            "#{inTypeDesc,jdbcType=VARCHAR}, #{needPay,jdbcType=DOUBLE}, ",
            "#{occurTime,jdbcType=VARCHAR}, #{outCarPlate,jdbcType=VARCHAR}, ",
            "#{outCarType,jdbcType=INTEGER}, #{outCardCode,jdbcType=VARCHAR}, ",
            "#{outPicPath,jdbcType=VARCHAR}, #{outPortId,jdbcType=INTEGER}, ",
            "#{outPortName,jdbcType=VARCHAR}, #{outTime,jdbcType=VARCHAR}, ",
            "#{outType,jdbcType=INTEGER}, #{outTypeDesc,jdbcType=VARCHAR}, ",
            "#{parkId,jdbcType=INTEGER}, #{stopTime,jdbcType=VARCHAR}, ",
            "#{clientId,jdbcType=INTEGER}, #{inSplitTime,jdbcType=VARCHAR}, ",
            "#{outSplitTime,jdbcType=VARCHAR}, #{firstInTime,jdbcType=VARCHAR}, ",
            "#{dayPay,jdbcType=DOUBLE}, #{operatingId,jdbcType=INTEGER}, ",
            "#{operatingName,jdbcType=VARCHAR}, #{coupon,jdbcType=DOUBLE}, ",
            "#{operatingType,jdbcType=INTEGER}, #{pid,jdbcType=INTEGER}, ",
            "#{orderId,jdbcType=VARCHAR}, #{qpassPay,jdbcType=DOUBLE}, ",
            "#{merchantId,jdbcType=INTEGER}, #{sectimePayed,jdbcType=DOUBLE}, ",
            "#{sectimeStart,jdbcType=VARCHAR}, #{epaytype,jdbcType=INTEGER}, ",
            "#{govInUpFlag,jdbcType=INTEGER}, #{govOutUpFlag,jdbcType=INTEGER}, ",
            "#{inCarPlateColor,jdbcType=VARCHAR}, #{outCarPlateColor,jdbcType=VARCHAR}, ",
            "#{prepayType,jdbcType=INTEGER}, #{localcoupon,jdbcType=DOUBLE}, ",
            "#{localcouponName,jdbcType=VARCHAR}, #{localcouponRuleId,jdbcType=INTEGER}, ",
            "#{updatedincarplate,jdbcType=VARCHAR}, #{updatedoutcarplate,jdbcType=VARCHAR}, ",
            "#{couponType,jdbcType=INTEGER}, #{billingStatus,jdbcType=VARCHAR}, ",
            "#{cloudOrderId,jdbcType=VARCHAR}, #{settleDate,jdbcType=VARCHAR}",
            "#{cashPayCode,jdbcType=VARCHAR}, #{prePayCode,jdbcType=VARCHAR}, ",
            "#{behalfPayCode,jdbcType=VARCHAR}, #{qPassPayCode,jdbcType=VARCHAR}, ",
            "#{freePayCode,jdbcType=VARCHAR}, #{chargeTypeDesc,jdbcType=VARCHAR}, ",
            "#{remarks,jdbcType=VARCHAR}, #{carNatureDesc,jdbcType=VARCHAR}, ",
            "#{carStatus,jdbcType=INTEGER})"
    })
    int insert(TParkInOut record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @InsertProvider(type = TParkInOutSqlProvider.class, method = "insertSelective")
    int insertSelective(TParkInOut record, Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @SelectProvider(type = TParkInOutSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "actual_pay", property = "actualPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "before_pay", property = "beforePay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "car_nature", property = "carNature", jdbcType = JdbcType.INTEGER),
            @Result(column = "charge_type", property = "chargeType", jdbcType = JdbcType.INTEGER),
            @Result(column = "company_id", property = "companyId", jdbcType = JdbcType.INTEGER),
            @Result(column = "duty_person", property = "dutyPerson", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_car_plate", property = "inCarPlate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_card_code", property = "inCardCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_pic_path", property = "inPicPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_port_id", property = "inPortId", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_port_name", property = "inPortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_time", property = "inTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_type", property = "inType", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_car_type", property = "inCarType", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_type_desc", property = "inTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "need_pay", property = "needPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "occur_time", property = "occurTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_plate", property = "outCarPlate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_type", property = "outCarType", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_card_code", property = "outCardCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_pic_path", property = "outPicPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_port_id", property = "outPortId", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_port_name", property = "outPortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_time", property = "outTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_type", property = "outType", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_type_desc", property = "outTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "park_id", property = "parkId", jdbcType = JdbcType.INTEGER),
            @Result(column = "stop_time", property = "stopTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "client_id", property = "clientId", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_split_time", property = "inSplitTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_split_time", property = "outSplitTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "first_in_time", property = "firstInTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "day_pay", property = "dayPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "operating_id", property = "operatingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operating_name", property = "operatingName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "coupon", property = "coupon", jdbcType = JdbcType.DOUBLE),
            @Result(column = "operating_type", property = "operatingType", jdbcType = JdbcType.INTEGER),
            @Result(column = "pid", property = "pid", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_id", property = "orderId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "qpass_pay", property = "qpassPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "merchant_id", property = "merchantId", jdbcType = JdbcType.INTEGER),
            @Result(column = "secTime_Payed", property = "sectimePayed", jdbcType = JdbcType.DOUBLE),
            @Result(column = "secTime_Start", property = "sectimeStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ePayType", property = "epaytype", jdbcType = JdbcType.INTEGER),
            @Result(column = "gov_in_up_flag", property = "govInUpFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "gov_out_up_flag", property = "govOutUpFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_car_plate_color", property = "inCarPlateColor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_plate_color", property = "outCarPlateColor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "prepay_type", property = "prepayType", jdbcType = JdbcType.INTEGER),
            @Result(column = "localcoupon", property = "localcoupon", jdbcType = JdbcType.DOUBLE),
            @Result(column = "localcoupon_name", property = "localcouponName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "localCoupon_rule_id", property = "localcouponRuleId", jdbcType = JdbcType.INTEGER),
            @Result(column = "updatedInCarplate", property = "updatedincarplate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updatedOutCarplate", property = "updatedoutcarplate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "coupon_type", property = "couponType", jdbcType = JdbcType.INTEGER),
            @Result(column = "billing_status", property = "billingStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cloud_order_id", property = "cloudOrderId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "settle_date", property = "settleDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cash_pay_code", property = "cashPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pre_pay_code", property = "prePayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "behalf_pay_code", property = "behalfPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "q_pass_pay_code", property = "qPassPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "free_pay_code", property = "freePayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "charge_type_desc", property = "chargeTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remarks", property = "remarks", jdbcType = JdbcType.VARCHAR),
            @Result(column = "car_nature_desc", property = "carNatureDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "car_status", property = "carStatus", jdbcType = JdbcType.INTEGER)
    })
    List<TParkInOut> selectByExample(TParkInOutCriteria example, Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "id, actual_pay, before_pay, car_nature, charge_type, company_id, duty_person, ",
            "in_car_plate, in_card_code, in_pic_path, in_port_id, in_port_name, in_time, ",
            "in_type,in_car_type, in_type_desc, need_pay, occur_time, out_car_plate, out_car_type, out_card_code, ",
            "out_pic_path, out_port_id, out_port_name, out_time, out_type, out_type_desc, ",
            "park_id, stop_time, client_id, in_split_time, out_split_time, first_in_time, ",
            "day_pay, operating_id, operating_name, coupon, operating_type, pid, order_id, ",
            "qpass_pay, merchant_id, secTime_Payed, secTime_Start, ePayType, gov_in_up_flag, ",
            "gov_out_up_flag, in_car_plate_color, out_car_plate_color, prepay_type, localcoupon, ",
            "localcoupon_name, localCoupon_rule_id, updatedInCarplate, updatedOutCarplate, ",
            "coupon_type, billing_status, cloud_order_id, settle_date",
            "from t_park_in_out",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "actual_pay", property = "actualPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "before_pay", property = "beforePay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "car_nature", property = "carNature", jdbcType = JdbcType.INTEGER),
            @Result(column = "charge_type", property = "chargeType", jdbcType = JdbcType.INTEGER),
            @Result(column = "company_id", property = "companyId", jdbcType = JdbcType.INTEGER),
            @Result(column = "duty_person", property = "dutyPerson", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_car_plate", property = "inCarPlate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_card_code", property = "inCardCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_pic_path", property = "inPicPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_port_id", property = "inPortId", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_port_name", property = "inPortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_time", property = "inTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "in_type", property = "inType", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_car_type", property = "inCarType", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_type_desc", property = "inTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "need_pay", property = "needPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "occur_time", property = "occurTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_plate", property = "outCarPlate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_type", property = "outCarType", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_card_code", property = "outCardCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_pic_path", property = "outPicPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_port_id", property = "outPortId", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_port_name", property = "outPortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_time", property = "outTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_type", property = "outType", jdbcType = JdbcType.INTEGER),
            @Result(column = "out_type_desc", property = "outTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "park_id", property = "parkId", jdbcType = JdbcType.INTEGER),
            @Result(column = "stop_time", property = "stopTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "client_id", property = "clientId", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_split_time", property = "inSplitTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_split_time", property = "outSplitTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "first_in_time", property = "firstInTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "day_pay", property = "dayPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "operating_id", property = "operatingId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operating_name", property = "operatingName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "coupon", property = "coupon", jdbcType = JdbcType.DOUBLE),
            @Result(column = "operating_type", property = "operatingType", jdbcType = JdbcType.INTEGER),
            @Result(column = "pid", property = "pid", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_id", property = "orderId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "qpass_pay", property = "qpassPay", jdbcType = JdbcType.DOUBLE),
            @Result(column = "merchant_id", property = "merchantId", jdbcType = JdbcType.INTEGER),
            @Result(column = "secTime_Payed", property = "sectimePayed", jdbcType = JdbcType.DOUBLE),
            @Result(column = "secTime_Start", property = "sectimeStart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ePayType", property = "epaytype", jdbcType = JdbcType.INTEGER),
            @Result(column = "gov_in_up_flag", property = "govInUpFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "gov_out_up_flag", property = "govOutUpFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "in_car_plate_color", property = "inCarPlateColor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "out_car_plate_color", property = "outCarPlateColor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "prepay_type", property = "prepayType", jdbcType = JdbcType.INTEGER),
            @Result(column = "localcoupon", property = "localcoupon", jdbcType = JdbcType.DOUBLE),
            @Result(column = "localcoupon_name", property = "localcouponName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "localCoupon_rule_id", property = "localcouponRuleId", jdbcType = JdbcType.INTEGER),
            @Result(column = "updatedInCarplate", property = "updatedincarplate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updatedOutCarplate", property = "updatedoutcarplate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "coupon_type", property = "couponType", jdbcType = JdbcType.INTEGER),
            @Result(column = "billing_status", property = "billingStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cloud_order_id", property = "cloudOrderId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "settle_date", property = "settleDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cash_pay_code", property = "cashPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pre_pay_code", property = "prePayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "behalf_pay_code", property = "behalfPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "q_pass_pay_code", property = "qPassPayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "free_pay_code", property = "freePayCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "charge_type_desc", property = "chargeTypeDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remarks", property = "remarks", jdbcType = JdbcType.VARCHAR),
            @Result(column = "car_nature_desc", property = "carNatureDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "car_status", property = "carStatus", jdbcType = JdbcType.INTEGER)
    })
    TParkInOut selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TParkInOutSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TParkInOut record, @Param("example") TParkInOutCriteria example, @Param("parkID") Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TParkInOutSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") TParkInOut record, @Param("example") TParkInOutCriteria example, @Param("parkID") Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TParkInOutSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TParkInOut record, Integer parkID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_park_in_out
     *
     * @mbg.generated
     */
    @Update({
            "update t_park_in_out",
            "set actual_pay = #{actualPay,jdbcType=DOUBLE},",
            "before_pay = #{beforePay,jdbcType=DOUBLE},",
            "car_nature = #{carNature,jdbcType=INTEGER},",
            "charge_type = #{chargeType,jdbcType=INTEGER},",
            "company_id = #{companyId,jdbcType=INTEGER},",
            "duty_person = #{dutyPerson,jdbcType=VARCHAR},",
            "in_car_plate = #{inCarPlate,jdbcType=VARCHAR},",
            "in_card_code = #{inCardCode,jdbcType=VARCHAR},",
            "in_pic_path = #{inPicPath,jdbcType=VARCHAR},",
            "in_port_id = #{inPortId,jdbcType=INTEGER},",
            "in_port_name = #{inPortName,jdbcType=VARCHAR},",
            "in_time = #{inTime,jdbcType=VARCHAR},",
            "in_type = #{inType,jdbcType=INTEGER},",
            "in_car_type = #{inCarType,jdbcType=INTEGER},",
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
            "client_id = #{clientId,jdbcType=INTEGER},",
            "in_split_time = #{inSplitTime,jdbcType=VARCHAR},",
            "out_split_time = #{outSplitTime,jdbcType=VARCHAR},",
            "first_in_time = #{firstInTime,jdbcType=VARCHAR},",
            "day_pay = #{dayPay,jdbcType=DOUBLE},",
            "operating_id = #{operatingId,jdbcType=INTEGER},",
            "operating_name = #{operatingName,jdbcType=VARCHAR},",
            "coupon = #{coupon,jdbcType=DOUBLE},",
            "operating_type = #{operatingType,jdbcType=INTEGER},",
            "pid = #{pid,jdbcType=INTEGER},",
            "order_id = #{orderId,jdbcType=VARCHAR},",
            "qpass_pay = #{qpassPay,jdbcType=DOUBLE},",
            "merchant_id = #{merchantId,jdbcType=INTEGER},",
            "secTime_Payed = #{sectimePayed,jdbcType=DOUBLE},",
            "secTime_Start = #{sectimeStart,jdbcType=VARCHAR},",
            "ePayType = #{epaytype,jdbcType=INTEGER},",
            "gov_in_up_flag = #{govInUpFlag,jdbcType=INTEGER},",
            "gov_out_up_flag = #{govOutUpFlag,jdbcType=INTEGER},",
            "in_car_plate_color = #{inCarPlateColor,jdbcType=VARCHAR},",
            "out_car_plate_color = #{outCarPlateColor,jdbcType=VARCHAR},",
            "prepay_type = #{prepayType,jdbcType=INTEGER},",
            "localcoupon = #{localcoupon,jdbcType=DOUBLE},",
            "localcoupon_name = #{localcouponName,jdbcType=VARCHAR},",
            "localCoupon_rule_id = #{localcouponRuleId,jdbcType=INTEGER},",
            "updatedInCarplate = #{updatedincarplate,jdbcType=VARCHAR},",
            "updatedOutCarplate = #{updatedoutcarplate,jdbcType=VARCHAR},",
            "coupon_type = #{couponType,jdbcType=INTEGER},",
            "billing_status = #{billingStatus,jdbcType=VARCHAR},",
            "cloud_order_id = #{cloudOrderId,jdbcType=VARCHAR},",
            "settle_date = #{settleDate,jdbcType=VARCHAR}",
            "cash_pay_code = #{cashPayCode,jdbcType=VARCHAR}",
            "pre_pay_code = #{prePayCode,jdbcType=VARCHAR}",
            "behalf_pay_code = #{behalfPayCode,jdbcType=VARCHAR}",
            "q_pass_pay_code = #{qPassPayCode,jdbcType=VARCHAR}",
            "free_pay_code = #{freePayCode,jdbcType=VARCHAR}",
            "charge_type_desc = #{chargeTypeDesc,jdbcType=VARCHAR}",
            "remarks = #{remarks,jdbcType=VARCHAR}",
            "car_nature_desc = #{carNatureDesc,jdbcType=VARCHAR}",
            "car_status = #{carStatus,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TParkInOut record);
}