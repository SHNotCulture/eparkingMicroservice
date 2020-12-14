package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TPropertyBillItems;
import com.common.entity.eparkingCloud.TPropertyBillItemsCriteria;
import com.eparkingdb.dao.sqlProvider.TPropertyBillItemsSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TPropertyBillItemsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @SelectProvider(type= TPropertyBillItemsSqlProvider.class, method="countByExample")
    long countByExample(TPropertyBillItemsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TPropertyBillItemsSqlProvider.class, method="deleteByExample")
    int deleteByExample(TPropertyBillItemsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_property_bill_items",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_property_bill_items (id, company_id, ",
        "park_id, bill_name, ",
        "items_type, items_amount, ",
        "create_time, update_time, ",
        "user_id)",
        "values (#{id,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{parkId,jdbcType=INTEGER}, #{billName,jdbcType=VARCHAR}, ",
        "#{itemsType,jdbcType=INTEGER}, #{itemsAmount,jdbcType=DOUBLE}, ",
        "#{createTime,jdbcType=VARCHAR}, #{updateTime,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    int insert(TPropertyBillItems record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @InsertProvider(type=TPropertyBillItemsSqlProvider.class, method="insertSelective")
    int insertSelective(TPropertyBillItems record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @SelectProvider(type=TPropertyBillItemsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_name", property="billName", jdbcType=JdbcType.VARCHAR),
        @Result(column="items_type", property="itemsType", jdbcType=JdbcType.INTEGER),
        @Result(column="items_amount", property="itemsAmount", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<TPropertyBillItems> selectByExample(TPropertyBillItemsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, company_id, park_id, bill_name, items_type, items_amount, create_time, update_time, ",
        "user_id",
        "from t_property_bill_items",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="bill_name", property="billName", jdbcType=JdbcType.VARCHAR),
        @Result(column="items_type", property="itemsType", jdbcType=JdbcType.INTEGER),
        @Result(column="items_amount", property="itemsAmount", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    TPropertyBillItems selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyBillItemsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TPropertyBillItems record, @Param("example") TPropertyBillItemsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyBillItemsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TPropertyBillItems record, @Param("example") TPropertyBillItemsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TPropertyBillItemsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TPropertyBillItems record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_bill_items
     *
     * @mbg.generated
     */
    @Update({
        "update t_property_bill_items",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "park_id = #{parkId,jdbcType=INTEGER},",
          "bill_name = #{billName,jdbcType=VARCHAR},",
          "items_type = #{itemsType,jdbcType=INTEGER},",
          "items_amount = #{itemsAmount,jdbcType=DOUBLE},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TPropertyBillItems record);
}