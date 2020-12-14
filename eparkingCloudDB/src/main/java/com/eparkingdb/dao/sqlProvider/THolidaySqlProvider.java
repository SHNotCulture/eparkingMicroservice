package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.THoliday;
import com.common.entity.eparkingCloud.THolidayCriteria;
import com.common.entity.eparkingCloud.THolidayCriteria.Criteria;
import com.common.entity.eparkingCloud.THolidayCriteria.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class THolidaySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String countByExample(THolidayCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_holiday");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String deleteByExample(THolidayCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_holiday");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String insertSelective(THoliday record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_holiday");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getBeginDay() != null) {
            sql.VALUES("begin_day", "#{beginDay,jdbcType=INTEGER}");
        }
        
        if (record.getBeginMonth() != null) {
            sql.VALUES("begin_month", "#{beginMonth,jdbcType=INTEGER}");
        }
        
        if (record.getBeginTime() != null) {
            sql.VALUES("begin_time", "#{beginTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCascadeFlag() != null) {
            sql.VALUES("cascade_flag", "#{cascadeFlag,jdbcType=INTEGER}");
        }
        
        if (record.getCascadeId() != null) {
            sql.VALUES("cascade_id", "#{cascadeId,jdbcType=INTEGER}");
        }
        
        if (record.getCompIndex() != null) {
            sql.VALUES("comp_index", "#{compIndex,jdbcType=VARCHAR}");
        }
        
        if (record.getDayCount() != null) {
            sql.VALUES("day_count", "#{dayCount,jdbcType=INTEGER}");
        }
        
        if (record.getHolidayName() != null) {
            sql.VALUES("holiday_name", "#{holidayName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocid() != null) {
            sql.VALUES("locId", "#{locid,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.VALUES("park_id", "#{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getStaFlag() != null) {
            sql.VALUES("sta_flag", "#{staFlag,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String selectByExample(THolidayCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("begin_day");
        sql.SELECT("begin_month");
        sql.SELECT("begin_time");
        sql.SELECT("cascade_flag");
        sql.SELECT("cascade_id");
        sql.SELECT("comp_index");
        sql.SELECT("day_count");
        sql.SELECT("holiday_name");
        sql.SELECT("locId");
        sql.SELECT("park_id");
        sql.SELECT("sta_flag");
        sql.FROM("t_holiday");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        THoliday record = (THoliday) parameter.get("record");
        THolidayCriteria example = (THolidayCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_holiday");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getBeginDay() != null) {
            sql.SET("begin_day = #{record.beginDay,jdbcType=INTEGER}");
        }
        
        if (record.getBeginMonth() != null) {
            sql.SET("begin_month = #{record.beginMonth,jdbcType=INTEGER}");
        }
        
        if (record.getBeginTime() != null) {
            sql.SET("begin_time = #{record.beginTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCascadeFlag() != null) {
            sql.SET("cascade_flag = #{record.cascadeFlag,jdbcType=INTEGER}");
        }
        
        if (record.getCascadeId() != null) {
            sql.SET("cascade_id = #{record.cascadeId,jdbcType=INTEGER}");
        }
        
        if (record.getCompIndex() != null) {
            sql.SET("comp_index = #{record.compIndex,jdbcType=VARCHAR}");
        }
        
        if (record.getDayCount() != null) {
            sql.SET("day_count = #{record.dayCount,jdbcType=INTEGER}");
        }
        
        if (record.getHolidayName() != null) {
            sql.SET("holiday_name = #{record.holidayName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocid() != null) {
            sql.SET("locId = #{record.locid,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        }
        
        if (record.getStaFlag() != null) {
            sql.SET("sta_flag = #{record.staFlag,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_holiday");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("begin_day = #{record.beginDay,jdbcType=INTEGER}");
        sql.SET("begin_month = #{record.beginMonth,jdbcType=INTEGER}");
        sql.SET("begin_time = #{record.beginTime,jdbcType=VARCHAR}");
        sql.SET("cascade_flag = #{record.cascadeFlag,jdbcType=INTEGER}");
        sql.SET("cascade_id = #{record.cascadeId,jdbcType=INTEGER}");
        sql.SET("comp_index = #{record.compIndex,jdbcType=VARCHAR}");
        sql.SET("day_count = #{record.dayCount,jdbcType=INTEGER}");
        sql.SET("holiday_name = #{record.holidayName,jdbcType=VARCHAR}");
        sql.SET("locId = #{record.locid,jdbcType=INTEGER}");
        sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        sql.SET("sta_flag = #{record.staFlag,jdbcType=INTEGER}");
        
        THolidayCriteria example = (THolidayCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(THoliday record) {
        SQL sql = new SQL();
        sql.UPDATE("t_holiday");
        
        if (record.getBeginDay() != null) {
            sql.SET("begin_day = #{beginDay,jdbcType=INTEGER}");
        }
        
        if (record.getBeginMonth() != null) {
            sql.SET("begin_month = #{beginMonth,jdbcType=INTEGER}");
        }
        
        if (record.getBeginTime() != null) {
            sql.SET("begin_time = #{beginTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCascadeFlag() != null) {
            sql.SET("cascade_flag = #{cascadeFlag,jdbcType=INTEGER}");
        }
        
        if (record.getCascadeId() != null) {
            sql.SET("cascade_id = #{cascadeId,jdbcType=INTEGER}");
        }
        
        if (record.getCompIndex() != null) {
            sql.SET("comp_index = #{compIndex,jdbcType=VARCHAR}");
        }
        
        if (record.getDayCount() != null) {
            sql.SET("day_count = #{dayCount,jdbcType=INTEGER}");
        }
        
        if (record.getHolidayName() != null) {
            sql.SET("holiday_name = #{holidayName,jdbcType=VARCHAR}");
        }
        
        if (record.getLocid() != null) {
            sql.SET("locId = #{locid,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getStaFlag() != null) {
            sql.SET("sta_flag = #{staFlag,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, THolidayCriteria example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}