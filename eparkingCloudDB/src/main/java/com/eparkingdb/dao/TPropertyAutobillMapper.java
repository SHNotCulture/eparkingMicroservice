package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.common.entity.eparkingCloud.TPropertyAutobillCriteria;
import com.eparkingdb.dao.sqlProvider.TPropertyAutobillSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TPropertyAutobillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @SelectProvider(type= TPropertyAutobillSqlProvider.class, method="countByExample")
    long countByExample(TPropertyAutobillCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TPropertyAutobillSqlProvider.class, method="deleteByExample")
    int deleteByExample(TPropertyAutobillCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_property_autobill",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_property_autobill (id, company_id, ",
        "park_id, bill_item_id, ",
        "bill_name, checkout_date, ",
        "autobill_push_date, amount, ",
        "create_time, update_time, ",
        "user_id, items_type)",
        "values (#{id,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{parkId,jdbcType=INTEGER}, #{billItemId,jdbcType=INTEGER}, ",
        "#{billName,jdbcType=VARCHAR}, #{checkoutDate,jdbcType=VARCHAR}, ",
        "#{autobillPushDate,jdbcType=VARCHAR}, #{amount,jdbcType=DOUBLE}, ",
        "#{createTime,jdbcType=VARCHAR}, #{updateTime,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{itemsType,jdbcType=INTEGER})"
    })
    int insert(TPropertyAutobill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @InsertProvider(type=TPropertyAutobillSqlProvider.class, method="insertSelective")
    int insertSelective(TPropertyAutobill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @SelectProvider(type=TPropertyAutobillSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_item_id", property="billItemId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_name", property="billName", jdbcType=JdbcType.VARCHAR),
        @Result(column="checkout_date", property="checkoutDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="autobill_push_date", property="autobillPushDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="items_type", property="itemsType", jdbcType=JdbcType.INTEGER)
    })
    List<TPropertyAutobill> selectByExample(TPropertyAutobillCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, company_id, park_id, bill_item_id, bill_name, checkout_date, autobill_push_date, ",
        "amount, create_time, update_time, user_id, items_type",
        "from t_property_autobill",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_item_id", property="billItemId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_name", property="billName", jdbcType=JdbcType.VARCHAR),
        @Result(column="checkout_date", property="checkoutDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="autobill_push_date", property="autobillPushDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="items_type", property="itemsType", jdbcType=JdbcType.INTEGER)
    })
    TPropertyAutobill selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyAutobillSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TPropertyAutobill record, @Param("example") TPropertyAutobillCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyAutobillSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TPropertyAutobill record, @Param("example") TPropertyAutobillCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyAutobillSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TPropertyAutobill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    @Update({
        "update t_property_autobill",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "park_id = #{parkId,jdbcType=INTEGER},",
          "bill_item_id = #{billItemId,jdbcType=INTEGER},",
          "bill_name = #{billName,jdbcType=VARCHAR},",
          "checkout_date = #{checkoutDate,jdbcType=VARCHAR},",
          "autobill_push_date = #{autobillPushDate,jdbcType=VARCHAR},",
          "amount = #{amount,jdbcType=DOUBLE},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "items_type = #{itemsType,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TPropertyAutobill record);
}