package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.TConponQrcode;
import com.common.entity.eparkingCloud.TConponQrcodeCriteria;
import com.common.entity.eparkingCloud.TConponQrcodeCriteria.Criteria;
import com.common.entity.eparkingCloud.TConponQrcodeCriteria.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class TConponQrcodeSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String countByExample(TConponQrcodeCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_conpon_qrcode");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String deleteByExample(TConponQrcodeCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_conpon_qrcode");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String insertSelective(TConponQrcode record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_conpon_qrcode");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getQrcodeName() != null) {
            sql.VALUES("qrcode_name", "#{qrcodeName,jdbcType=VARCHAR}");
        }
        
        if (record.getQrcodeUrl() != null) {
            sql.VALUES("qrcode_url", "#{qrcodeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getTriesLimit() != null) {
            sql.VALUES("tries_limit", "#{triesLimit,jdbcType=INTEGER}");
        }
        
        if (record.getUseLimit() != null) {
            sql.VALUES("use_limit", "#{useLimit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=VARCHAR}");
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
        
        if (record.getCouponType() != null) {
            sql.VALUES("coupon_type", "#{couponType,jdbcType=INTEGER}");
        }
        
        if (record.getCouponPay() != null) {
            sql.VALUES("coupon_pay", "#{couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getTicketId() != null) {
            sql.VALUES("ticket_id", "#{ticketId,jdbcType=INTEGER}");
        }
        
        if (record.getValidTime() != null) {
            sql.VALUES("valid_time", "#{validTime,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String selectByExample(TConponQrcodeCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("qrcode_name");
        sql.SELECT("qrcode_url");
        sql.SELECT("tries_limit");
        sql.SELECT("use_limit");
        sql.SELECT("create_time");
        sql.SELECT("company_id");
        sql.SELECT("park_id");
        sql.SELECT("busine_id");
        sql.SELECT("coupon_type");
        sql.SELECT("coupon_pay");
        sql.SELECT("ticket_id");
        sql.SELECT("valid_time");
        sql.FROM("t_conpon_qrcode");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TConponQrcode record = (TConponQrcode) parameter.get("record");
        TConponQrcodeCriteria example = (TConponQrcodeCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_conpon_qrcode");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getQrcodeName() != null) {
            sql.SET("qrcode_name = #{record.qrcodeName,jdbcType=VARCHAR}");
        }
        
        if (record.getQrcodeUrl() != null) {
            sql.SET("qrcode_url = #{record.qrcodeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getTriesLimit() != null) {
            sql.SET("tries_limit = #{record.triesLimit,jdbcType=INTEGER}");
        }
        
        if (record.getUseLimit() != null) {
            sql.SET("use_limit = #{record.useLimit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
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
        
        if (record.getCouponType() != null) {
            sql.SET("coupon_type = #{record.couponType,jdbcType=INTEGER}");
        }
        
        if (record.getCouponPay() != null) {
            sql.SET("coupon_pay = #{record.couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getTicketId() != null) {
            sql.SET("ticket_id = #{record.ticketId,jdbcType=INTEGER}");
        }
        
        if (record.getValidTime() != null) {
            sql.SET("valid_time = #{record.validTime,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_conpon_qrcode");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("qrcode_name = #{record.qrcodeName,jdbcType=VARCHAR}");
        sql.SET("qrcode_url = #{record.qrcodeUrl,jdbcType=VARCHAR}");
        sql.SET("tries_limit = #{record.triesLimit,jdbcType=INTEGER}");
        sql.SET("use_limit = #{record.useLimit,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
        sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        sql.SET("busine_id = #{record.busineId,jdbcType=INTEGER}");
        sql.SET("coupon_type = #{record.couponType,jdbcType=INTEGER}");
        sql.SET("coupon_pay = #{record.couponPay,jdbcType=DOUBLE}");
        sql.SET("ticket_id = #{record.ticketId,jdbcType=INTEGER}");
        sql.SET("valid_time = #{record.validTime,jdbcType=VARCHAR}");
        
        TConponQrcodeCriteria example = (TConponQrcodeCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TConponQrcode record) {
        SQL sql = new SQL();
        sql.UPDATE("t_conpon_qrcode");
        
        if (record.getQrcodeName() != null) {
            sql.SET("qrcode_name = #{qrcodeName,jdbcType=VARCHAR}");
        }
        
        if (record.getQrcodeUrl() != null) {
            sql.SET("qrcode_url = #{qrcodeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getTriesLimit() != null) {
            sql.SET("tries_limit = #{triesLimit,jdbcType=INTEGER}");
        }
        
        if (record.getUseLimit() != null) {
            sql.SET("use_limit = #{useLimit,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBusineId() != null) {
            sql.SET("busine_id = #{busineId,jdbcType=INTEGER}");
        }
        
        if (record.getCouponType() != null) {
            sql.SET("coupon_type = #{couponType,jdbcType=INTEGER}");
        }
        
        if (record.getCouponPay() != null) {
            sql.SET("coupon_pay = #{couponPay,jdbcType=DOUBLE}");
        }
        
        if (record.getTicketId() != null) {
            sql.SET("ticket_id = #{ticketId,jdbcType=INTEGER}");
        }
        
        if (record.getValidTime() != null) {
            sql.SET("valid_time = #{validTime,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_conpon_qrcode
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TConponQrcodeCriteria example, boolean includeExamplePhrase) {
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