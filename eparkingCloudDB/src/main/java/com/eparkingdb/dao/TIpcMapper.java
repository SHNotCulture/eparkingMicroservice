package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.TIpc;
import com.common.entity.eparkingCloud.TIpcCriteria;
import com.eparkingdb.dao.sqlProvider.TIpcSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TIpcMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @SelectProvider(type= TIpcSqlProvider.class, method="countByExample")
    long countByExample(TIpcCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TIpcSqlProvider.class, method="deleteByExample")
    int deleteByExample(TIpcCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_ipc",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_ipc (id, ipc_type, ",
        "rtspUrl, 相机厂家名称)",
        "values (#{id,jdbcType=INTEGER}, #{ipcType,jdbcType=INTEGER}, ",
        "#{rtspurl,jdbcType=VARCHAR}, #{相机厂家名称,jdbcType=VARCHAR})"
    })
    int insert(TIpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @InsertProvider(type=TIpcSqlProvider.class, method="insertSelective")
    int insertSelective(TIpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @SelectProvider(type=TIpcSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ipc_type", property="ipcType", jdbcType=JdbcType.INTEGER),
        @Result(column="rtspUrl", property="rtspurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="相机厂家名称", property="相机厂家名称", jdbcType=JdbcType.VARCHAR)
    })
    List<TIpc> selectByExample(TIpcCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, ipc_type, rtspUrl, 相机厂家名称",
        "from t_ipc",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ipc_type", property="ipcType", jdbcType=JdbcType.INTEGER),
        @Result(column="rtspUrl", property="rtspurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="相机厂家名称", property="相机厂家名称", jdbcType=JdbcType.VARCHAR)
    })
    TIpc selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TIpcSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TIpc record, @Param("example") TIpcCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TIpcSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TIpc record, @Param("example") TIpcCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TIpcSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TIpc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ipc
     *
     * @mbg.generated
     */
    @Update({
        "update t_ipc",
        "set ipc_type = #{ipcType,jdbcType=INTEGER},",
          "rtspUrl = #{rtspurl,jdbcType=VARCHAR},",
          "相机厂家名称 = #{相机厂家名称,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TIpc record);
}