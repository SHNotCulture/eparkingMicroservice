package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TBusinePay;
import com.common.entity.eparkingCloud.TBusinePayCriteria;
import com.eparkingdb.dao.sqlProvider.TBusinePaySqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TBusinePayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @SelectProvider(type=TBusinePaySqlProvider.class, method="countByExample")
    long countByExample(TBusinePayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TBusinePaySqlProvider.class, method="deleteByExample")
    int deleteByExample(TBusinePayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_busine_pay",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_busine_pay (id, company_id, ",
        "busine_id, busine_name, ",
        "park_id, need_pay, ",
        "actual_pay, balance, ",
        "pay_time, order_number, ",
        "busine_status, remark, ",
        "park_name)",
        "values (#{id,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{busineId,jdbcType=INTEGER}, #{busineName,jdbcType=VARCHAR}, ",
        "#{parkId,jdbcType=INTEGER}, #{needPay,jdbcType=DOUBLE}, ",
        "#{actualPay,jdbcType=DOUBLE}, #{balance,jdbcType=DOUBLE}, ",
        "#{payTime,jdbcType=VARCHAR}, #{orderNumber,jdbcType=VARCHAR}, ",
        "#{busineStatus,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{parkName,jdbcType=VARCHAR})"
    })
    int insert(TBusinePay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @InsertProvider(type=TBusinePaySqlProvider.class, method="insertSelective")
    int insertSelective(TBusinePay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @SelectProvider(type=TBusinePaySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_id", property="busineId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_name", property="busineName", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="need_pay", property="needPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="actual_pay", property="actualPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="balance", property="balance", jdbcType=JdbcType.DOUBLE),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_number", property="orderNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="busine_status", property="busineStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_name", property="parkName", jdbcType=JdbcType.VARCHAR)
    })
    List<TBusinePay> selectByExample(TBusinePayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, company_id, busine_id, busine_name, park_id, need_pay, actual_pay, balance, ",
        "pay_time, order_number, busine_status, remark, park_name",
        "from t_busine_pay",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_id", property="busineId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_name", property="busineName", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="need_pay", property="needPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="actual_pay", property="actualPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="balance", property="balance", jdbcType=JdbcType.DOUBLE),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_number", property="orderNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="busine_status", property="busineStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="park_name", property="parkName", jdbcType=JdbcType.VARCHAR)
    })
    TBusinePay selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBusinePaySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TBusinePay record, @Param("example") TBusinePayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBusinePaySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TBusinePay record, @Param("example") TBusinePayCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBusinePaySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TBusinePay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busine_pay
     *
     * @mbg.generated
     */
    @Update({
        "update t_busine_pay",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "busine_id = #{busineId,jdbcType=INTEGER},",
          "busine_name = #{busineName,jdbcType=VARCHAR},",
          "park_id = #{parkId,jdbcType=INTEGER},",
          "need_pay = #{needPay,jdbcType=DOUBLE},",
          "actual_pay = #{actualPay,jdbcType=DOUBLE},",
          "balance = #{balance,jdbcType=DOUBLE},",
          "pay_time = #{payTime,jdbcType=VARCHAR},",
          "order_number = #{orderNumber,jdbcType=VARCHAR},",
          "busine_status = #{busineStatus,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "park_name = #{parkName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TBusinePay record);
}