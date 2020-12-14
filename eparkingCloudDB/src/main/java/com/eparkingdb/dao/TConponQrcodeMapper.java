package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.entity.eparkingCloud.TConponQrcodeCriteria;
import com.eparkingdb.dao.sqlProvider.TConponQrcodeSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TConponQrcodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @SelectProvider(type= TConponQrcodeSqlProvider.class, method="countByExample")
    long countByExample(TConponQrcodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcodeTConponQrcodeMapper
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TConponQrcodeSqlProvider.class, method="deleteByExample")
    int deleteByExample(TConponQrcodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_conpon_qrcode",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_conpon_qrcode (id, qrcode_name, ",
        "qrcode_url, tries_limit, ",
        "use_limit, create_time, ",
        "company_id, park_id, ",
        "busine_id, coupon_type, ",
        "coupon_pay, ticket_id, ",
        "valid_time)",
        "values (#{id,jdbcType=INTEGER}, #{qrcodeName,jdbcType=VARCHAR}, ",
        "#{qrcodeUrl,jdbcType=VARCHAR}, #{triesLimit,jdbcType=INTEGER}, ",
        "#{useLimit,jdbcType=INTEGER}, #{createTime,jdbcType=VARCHAR}, ",
        "#{companyId,jdbcType=INTEGER}, #{parkId,jdbcType=INTEGER}, ",
        "#{busineId,jdbcType=INTEGER}, #{couponType,jdbcType=INTEGER}, ",
        "#{couponPay,jdbcType=DOUBLE}, #{ticketId,jdbcType=INTEGER}, ",
        "#{validTime,jdbcType=VARCHAR})"
    })
    int insert(TConponQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @InsertProvider(type=TConponQrcodeSqlProvider.class, method="insertSelective")
    int insertSelective(TConponQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @SelectProvider(type=TConponQrcodeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="qrcode_name", property="qrcodeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="qrcode_url", property="qrcodeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="tries_limit", property="triesLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="use_limit", property="useLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_id", property="busineId", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_pay", property="couponPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="ticket_id", property="ticketId", jdbcType=JdbcType.INTEGER),
        @Result(column="valid_time", property="validTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TConponQrcode> selectByExample(TConponQrcodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, qrcode_name, qrcode_url, tries_limit, use_limit, create_time, company_id, ",
        "park_id, busine_id, coupon_type, coupon_pay, ticket_id, valid_time",
        "from t_conpon_qrcode",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="qrcode_name", property="qrcodeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="qrcode_url", property="qrcodeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="tries_limit", property="triesLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="use_limit", property="useLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="busine_id", property="busineId", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_type", property="couponType", jdbcType=JdbcType.INTEGER),
        @Result(column="coupon_pay", property="couponPay", jdbcType=JdbcType.DOUBLE),
        @Result(column="ticket_id", property="ticketId", jdbcType=JdbcType.INTEGER),
        @Result(column="valid_time", property="validTime", jdbcType=JdbcType.VARCHAR)
    })
    TConponQrcode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TConponQrcodeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TConponQrcode record, @Param("example") TConponQrcodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TConponQrcodeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TConponQrcode record, @Param("example") TConponQrcodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TConponQrcodeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TConponQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    @Update({
        "update t_conpon_qrcode",
        "set qrcode_name = #{qrcodeName,jdbcType=VARCHAR},",
          "qrcode_url = #{qrcodeUrl,jdbcType=VARCHAR},",
          "tries_limit = #{triesLimit,jdbcType=INTEGER},",
          "use_limit = #{useLimit,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "company_id = #{companyId,jdbcType=INTEGER},",
          "park_id = #{parkId,jdbcType=INTEGER},",
          "busine_id = #{busineId,jdbcType=INTEGER},",
          "coupon_type = #{couponType,jdbcType=INTEGER},",
          "coupon_pay = #{couponPay,jdbcType=DOUBLE},",
          "ticket_id = #{ticketId,jdbcType=INTEGER},",
          "valid_time = #{validTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TConponQrcode record);
}