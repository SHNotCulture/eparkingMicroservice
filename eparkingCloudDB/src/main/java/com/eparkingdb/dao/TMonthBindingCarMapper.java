package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.entity.eparkingCloud.TMonthBindingCarCriteria;
import com.eparkingdb.dao.sqlProvider.TMonthBindingCarSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TMonthBindingCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @SelectProvider(type= TMonthBindingCarSqlProvider.class, method="countByExample")
    long countByExample(TMonthBindingCarCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TMonthBindingCarSqlProvider.class, method="deleteByExample")
    int deleteByExample(TMonthBindingCarCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_month_binding_car",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_month_binding_car (id, park_id, ",
        "park_car_id, car_plate, ",
        "car_type, update_time, ",
        "user_id, create_time)",
        "values (#{id,jdbcType=INTEGER}, #{parkId,jdbcType=INTEGER}, ",
        "#{parkCarId,jdbcType=INTEGER}, #{carPlate,jdbcType=VARCHAR}, ",
        "#{carType,jdbcType=INTEGER}, #{updateTime,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{createTime,jdbcType=VARCHAR})"
    })
    int insert(TMonthBindingCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @InsertProvider(type=TMonthBindingCarSqlProvider.class, method="insertSelective")
    int insertSelective(TMonthBindingCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @SelectProvider(type=TMonthBindingCarSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_car_id", property="parkCarId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_plate", property="carPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TMonthBindingCar> selectByExample(TMonthBindingCarCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, park_id, park_car_id, car_plate, car_type, update_time, user_id, create_time",
        "from t_month_binding_car",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="park_car_id", property="parkCarId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_plate", property="carPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="car_type", property="carType", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR)
    })
    TMonthBindingCar selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TMonthBindingCarSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TMonthBindingCar record, @Param("example") TMonthBindingCarCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TMonthBindingCarSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TMonthBindingCar record, @Param("example") TMonthBindingCarCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TMonthBindingCarSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TMonthBindingCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_month_binding_car
     *
     * @mbg.generated
     */
    @Update({
        "update t_month_binding_car",
        "set park_id = #{parkId,jdbcType=INTEGER},",
          "park_car_id = #{parkCarId,jdbcType=INTEGER},",
          "car_plate = #{carPlate,jdbcType=VARCHAR},",
          "car_type = #{carType,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TMonthBindingCar record);
}