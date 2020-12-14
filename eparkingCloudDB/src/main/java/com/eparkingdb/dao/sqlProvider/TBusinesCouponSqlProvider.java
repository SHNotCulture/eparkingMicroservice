package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TBusinesCouponCriteria;
import com.common.entity.eparkingCloud.TBusinesCouponCriteria.Criteria;
import com.common.entity.eparkingCloud.TBusinesCouponCriteria.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class TBusinesCouponSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String countByExample(TBusinesCouponCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_busines_coupon");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String deleteByExample(TBusinesCouponCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_busines_coupon");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String insertSelective(TBusinesCoupon record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_busines_coupon");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.VALUES("company_id", "#{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.VALUES("park_id", "#{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBusineId() != null) {
            sql.VALUES("busine_id", "#{busineId,jdbcType=INTEGER}");
        }
        
        if (record.getCarPlate() != null) {
            sql.VALUES("car_plate", "#{carPlate,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponCode() != null) {
            sql.VALUES("coupon_code", "#{couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponPay() != null) {
            sql.VALUES("coupon_pay", "#{couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getParkName() != null) {
            sql.VALUES("park_name", "#{parkName,jdbcType=VARCHAR}");
        }
        
        if (record.getInTime() != null) {
            sql.VALUES("in_time", "#{inTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOutTime() != null) {
            sql.VALUES("out_time", "#{outTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDerateType() != null) {
            sql.VALUES("derate_type", "#{derateType,jdbcType=INTEGER}");
        }
        
        if (record.getDisable() != null) {
            sql.VALUES("disable", "#{disable,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String selectByExample(TBusinesCouponCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("company_id");
        sql.SELECT("park_id");
        sql.SELECT("busine_id");
        sql.SELECT("car_plate");
        sql.SELECT("coupon_code");
        sql.SELECT("coupon_pay");
        sql.SELECT("create_date");
        sql.SELECT("end_date");
        sql.SELECT("remark");
        sql.SELECT("park_name");
        sql.SELECT("in_time");
        sql.SELECT("out_time");
        sql.SELECT("derate_type");
        sql.SELECT("disable");
        sql.SELECT("status");
        sql.FROM("t_busines_coupon");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TBusinesCoupon record = (TBusinesCoupon) parameter.get("record");
        TBusinesCouponCriteria example = (TBusinesCouponCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_busines_coupon");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBusineId() != null) {
            sql.SET("busine_id = #{record.busineId,jdbcType=INTEGER}");
        }
        
        if (record.getCarPlate() != null) {
            sql.SET("car_plate = #{record.carPlate,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponCode() != null) {
            sql.SET("coupon_code = #{record.couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponPay() != null) {
            sql.SET("coupon_pay = #{record.couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getParkName() != null) {
            sql.SET("park_name = #{record.parkName,jdbcType=VARCHAR}");
        }
        
        if (record.getInTime() != null) {
            sql.SET("in_time = #{record.inTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOutTime() != null) {
            sql.SET("out_time = #{record.outTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDerateType() != null) {
            sql.SET("derate_type = #{record.derateType,jdbcType=INTEGER}");
        }
        
        if (record.getDisable() != null) {
            sql.SET("disable = #{record.disable,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_busines_coupon");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        sql.SET("busine_id = #{record.busineId,jdbcType=INTEGER}");
        sql.SET("car_plate = #{record.carPlate,jdbcType=VARCHAR}");
        sql.SET("coupon_code = #{record.couponCode,jdbcType=VARCHAR}");
        sql.SET("coupon_pay = #{record.couponPay,jdbcType=DOUBLE}");
        sql.SET("create_date = #{record.createDate,jdbcType=VARCHAR}");
        sql.SET("end_date = #{record.endDate,jdbcType=VARCHAR}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        sql.SET("park_name = #{record.parkName,jdbcType=VARCHAR}");
        sql.SET("in_time = #{record.inTime,jdbcType=VARCHAR}");
        sql.SET("out_time = #{record.outTime,jdbcType=VARCHAR}");
        sql.SET("derate_type = #{record.derateType,jdbcType=INTEGER}");
        sql.SET("disable = #{record.disable,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        
        TBusinesCouponCriteria example = (TBusinesCouponCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TBusinesCoupon record) {
        SQL sql = new SQL();
        sql.UPDATE("t_busines_coupon");
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBusineId() != null) {
            sql.SET("busine_id = #{busineId,jdbcType=INTEGER}");
        }
        
        if (record.getCarPlate() != null) {
            sql.SET("car_plate = #{carPlate,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponCode() != null) {
            sql.SET("coupon_code = #{couponCode,jdbcType=VARCHAR}");
        }
        
        if (record.getCouponPay() != null) {
            sql.SET("coupon_pay = #{couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=VARCHAR}");
        }
        
        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getParkName() != null) {
            sql.SET("park_name = #{parkName,jdbcType=VARCHAR}");
        }
        
        if (record.getInTime() != null) {
            sql.SET("in_time = #{inTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOutTime() != null) {
            sql.SET("out_time = #{outTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDerateType() != null) {
            sql.SET("derate_type = #{derateType,jdbcType=INTEGER}");
        }
        
        if (record.getDisable() != null) {
            sql.SET("disable = #{disable,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_busines_coupon
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TBusinesCouponCriteria example, boolean includeExamplePhrase) {
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