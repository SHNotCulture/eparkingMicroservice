package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TChargeDetailsReport;
import com.common.entity.eparkingCloud.TChargeDetailsReportCriteria;
import com.eparkingdb.dao.sqlProvider.TChargeDetailsReportSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TChargeDetailsReportMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @SelectProvider(type= TChargeDetailsReportSqlProvider.class, method="countByExample")
    long countByExample(TChargeDetailsReportCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TChargeDetailsReportSqlProvider.class, method="deleteByExample")
    int deleteByExample(TChargeDetailsReportCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_charge_details_report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_charge_details_report (id, park_id, ",
        "company_id, charge_date, ",
        "car_nature_desc, charge_amount, ",
        "needpay_total, actualpay_total, ",
        "qpasspay_total, behalfpay_total, ",
        "prepay_total, coupon_total, ",
        "create_time, duty_person, ",
        "out_port_id, walletpay_total, ",
        "inaccesspaid_total)",
        "values (#{id,jdbcType=INTEGER}, #{parkId,jdbcType=INTEGER}, ",
        "#{companyId,jdbcType=INTEGER}, #{chargeDate,jdbcType=VARCHAR}, ",
        "#{carNatureDesc,jdbcType=VARCHAR}, #{chargeAmount,jdbcType=INTEGER}, ",
        "#{needpayTotal,jdbcType=DOUBLE}, #{actualpayTotal,jdbcType=DOUBLE}, ",
        "#{qpasspayTotal,jdbcType=DOUBLE}, #{behalfpayTotal,jdbcType=DOUBLE}, ",
        "#{prepayTotal,jdbcType=DOUBLE}, #{couponTotal,jdbcType=DOUBLE}, ",
        "#{createTime,jdbcType=VARCHAR}, #{dutyPerson,jdbcType=VARCHAR}, ",
        "#{outPortId,jdbcType=INTEGER}, #{walletpayTotal,jdbcType=DOUBLE}, ",
        "#{inaccesspaidTotal,jdbcType=DOUBLE})"
    })
    int insert(TChargeDetailsReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @InsertProvider(type=TChargeDetailsReportSqlProvider.class, method="insertSelective")
    int insertSelective(TChargeDetailsReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @SelectProvider(type=TChargeDetailsReportSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="charge_date", property="chargeDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_nature_desc", property="carNatureDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="charge_amount", property="chargeAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="needpay_total", property="needpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="actualpay_total", property="actualpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="qpasspay_total", property="qpasspayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="behalfpay_total", property="behalfpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="prepay_total", property="prepayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="coupon_total", property="couponTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="duty_person", property="dutyPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_port_id", property="outPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="walletpay_total", property="walletpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="inaccesspaid_total", property="inaccesspaidTotal", jdbcType=JdbcType.DOUBLE)
    })
    List<TChargeDetailsReport> selectByExample(TChargeDetailsReportCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, park_id, company_id, charge_date, car_nature_desc, charge_amount, needpay_total, ",
        "actualpay_total, qpasspay_total, behalfpay_total, prepay_total, coupon_total, ",
        "create_time, duty_person, out_port_id, walletpay_total, inaccesspaid_total",
        "from t_charge_details_report",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="charge_date", property="chargeDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_nature_desc", property="carNatureDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="charge_amount", property="chargeAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="needpay_total", property="needpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="actualpay_total", property="actualpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="qpasspay_total", property="qpasspayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="behalfpay_total", property="behalfpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="prepay_total", property="prepayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="coupon_total", property="couponTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="duty_person", property="dutyPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_port_id", property="outPortId", jdbcType=JdbcType.INTEGER),
        @Result(column="walletpay_total", property="walletpayTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="inaccesspaid_total", property="inaccesspaidTotal", jdbcType=JdbcType.DOUBLE)
    })
    TChargeDetailsReport selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TChargeDetailsReportSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TChargeDetailsReport record, @Param("example") TChargeDetailsReportCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TChargeDetailsReportSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TChargeDetailsReport record, @Param("example") TChargeDetailsReportCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TChargeDetailsReportSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TChargeDetailsReport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_charge_details_report
     *
     * @mbg.generated
     */
    @Update({
        "update t_charge_details_report",
        "set park_id = #{parkId,jdbcType=INTEGER},",
          "company_id = #{companyId,jdbcType=INTEGER},",
          "charge_date = #{chargeDate,jdbcType=VARCHAR},",
          "car_nature_desc = #{carNatureDesc,jdbcType=VARCHAR},",
          "charge_amount = #{chargeAmount,jdbcType=INTEGER},",
          "needpay_total = #{needpayTotal,jdbcType=DOUBLE},",
          "actualpay_total = #{actualpayTotal,jdbcType=DOUBLE},",
          "qpasspay_total = #{qpasspayTotal,jdbcType=DOUBLE},",
          "behalfpay_total = #{behalfpayTotal,jdbcType=DOUBLE},",
          "prepay_total = #{prepayTotal,jdbcType=DOUBLE},",
          "coupon_total = #{couponTotal,jdbcType=DOUBLE},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "duty_person = #{dutyPerson,jdbcType=VARCHAR},",
          "out_port_id = #{outPortId,jdbcType=INTEGER},",
          "walletpay_total = #{walletpayTotal,jdbcType=DOUBLE},",
          "inaccesspaid_total = #{inaccesspaidTotal,jdbcType=DOUBLE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TChargeDetailsReport record);
}