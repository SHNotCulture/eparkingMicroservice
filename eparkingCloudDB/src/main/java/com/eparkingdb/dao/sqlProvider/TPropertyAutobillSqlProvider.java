package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.TPropertyAutobill;
import com.common.entity.eparkingCloud.TPropertyAutobillCriteria;
import com.common.entity.eparkingCloud.TPropertyAutobillCriteria.Criteria;
import com.common.entity.eparkingCloud.TPropertyAutobillCriteria.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class TPropertyAutobillSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String countByExample(TPropertyAutobillCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_property_autobill");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String deleteByExample(TPropertyAutobillCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_property_autobill");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String insertSelective(TPropertyAutobill record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_property_autobill");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.VALUES("company_id", "#{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.VALUES("park_id", "#{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.VALUES("bill_item_id", "#{billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillName() != null) {
            sql.VALUES("bill_name", "#{billName,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.VALUES("checkout_date", "#{checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAutobillPushDate() != null) {
            sql.VALUES("autobill_push_date", "#{autobillPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.VALUES("amount", "#{amount,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getItemsType() != null) {
            sql.VALUES("items_type", "#{itemsType,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String selectByExample(TPropertyAutobillCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("company_id");
        sql.SELECT("park_id");
        sql.SELECT("bill_item_id");
        sql.SELECT("bill_name");
        sql.SELECT("checkout_date");
        sql.SELECT("autobill_push_date");
        sql.SELECT("amount");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("user_id");
        sql.SELECT("items_type");
        sql.FROM("t_property_autobill");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TPropertyAutobill record = (TPropertyAutobill) parameter.get("record");
        TPropertyAutobillCriteria example = (TPropertyAutobillCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_property_autobill");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.SET("bill_item_id = #{record.billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillName() != null) {
            sql.SET("bill_name = #{record.billName,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.SET("checkout_date = #{record.checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAutobillPushDate() != null) {
            sql.SET("autobill_push_date = #{record.autobillPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{record.amount,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=INTEGER}");
        }
        
        if (record.getItemsType() != null) {
            sql.SET("items_type = #{record.itemsType,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_property_autobill");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        sql.SET("bill_item_id = #{record.billItemId,jdbcType=INTEGER}");
        sql.SET("bill_name = #{record.billName,jdbcType=VARCHAR}");
        sql.SET("checkout_date = #{record.checkoutDate,jdbcType=VARCHAR}");
        sql.SET("autobill_push_date = #{record.autobillPushDate,jdbcType=VARCHAR}");
        sql.SET("amount = #{record.amount,jdbcType=DOUBLE}");
        sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=INTEGER}");
        sql.SET("items_type = #{record.itemsType,jdbcType=INTEGER}");
        
        TPropertyAutobillCriteria example = (TPropertyAutobillCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TPropertyAutobill record) {
        SQL sql = new SQL();
        sql.UPDATE("t_property_autobill");
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.SET("bill_item_id = #{billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillName() != null) {
            sql.SET("bill_name = #{billName,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.SET("checkout_date = #{checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAutobillPushDate() != null) {
            sql.SET("autobill_push_date = #{autobillPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmount() != null) {
            sql.SET("amount = #{amount,jdbcType=DOUBLE}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getItemsType() != null) {
            sql.SET("items_type = #{itemsType,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_autobill
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TPropertyAutobillCriteria example, boolean includeExamplePhrase) {
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