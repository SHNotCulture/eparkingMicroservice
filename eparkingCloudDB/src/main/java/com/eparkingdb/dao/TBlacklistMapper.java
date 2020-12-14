package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TBlacklist;
import com.common.entity.eparkingCloud.TBlacklistCriteria;
import com.eparkingdb.dao.sqlProvider.TBlacklistSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TBlacklistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @SelectProvider(type= TBlacklistSqlProvider.class, method="countByExample")
    long countByExample(TBlacklistCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TBlacklistSqlProvider.class, method="deleteByExample")
    int deleteByExample(TBlacklistCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_blacklist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_blacklist (id, park_id, ",
        "car_plate, create_time, ",
        "create_person, begin_time, ",
        "end_time)",
        "values (#{id,jdbcType=INTEGER}, #{parkId,jdbcType=INTEGER}, ",
        "#{carPlate,jdbcType=VARCHAR}, #{createTime,jdbcType=VARCHAR}, ",
        "#{createPerson,jdbcType=VARCHAR}, #{beginTime,jdbcType=VARCHAR}, ",
        "#{endTime,jdbcType=VARCHAR})"
    })
    int insert(TBlacklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @InsertProvider(type=TBlacklistSqlProvider.class, method="insertSelective")
    int insertSelective(TBlacklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @SelectProvider(type=TBlacklistSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_plate", property="carPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_person", property="createPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="begin_time", property="beginTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TBlacklist> selectByExample(TBlacklistCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, park_id, car_plate, create_time, create_person, begin_time, end_time",
        "from t_blacklist",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.INTEGER),
        @Result(column="car_plate", property="carPlate", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_person", property="createPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="begin_time", property="beginTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.VARCHAR)
    })
    TBlacklist selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBlacklistSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TBlacklist record, @Param("example") TBlacklistCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBlacklistSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TBlacklist record, @Param("example") TBlacklistCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TBlacklistSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TBlacklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blacklist
     *
     * @mbg.generated
     */
    @Update({
        "update t_blacklist",
        "set park_id = #{parkId,jdbcType=INTEGER},",
          "car_plate = #{carPlate,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=VARCHAR},",
          "create_person = #{createPerson,jdbcType=VARCHAR},",
          "begin_time = #{beginTime,jdbcType=VARCHAR},",
          "end_time = #{endTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TBlacklist record);
}