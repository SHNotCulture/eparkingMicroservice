package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.TPropertyHouseholdBill;
import com.common.entity.eparkingCloud.TPropertyHouseholdBillCriteria;
import com.common.entity.eparkingCloud.TPropertyHouseholdBillCriteria.Criteria;
import com.common.entity.eparkingCloud.TPropertyHouseholdBillCriteria.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class TPropertyHouseholdBillSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String countByExample(TPropertyHouseholdBillCriteria example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_property_household_bill");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String deleteByExample(TPropertyHouseholdBillCriteria example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_property_household_bill");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String insertSelective(TPropertyHouseholdBill record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_property_household_bill");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.VALUES("company_id", "#{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.VALUES("park_id", "#{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getHouseholdId() != null) {
            sql.VALUES("household_id", "#{householdId,jdbcType=INTEGER}");
        }
        
        if (record.getMemberId() != null) {
            sql.VALUES("member_id", "#{memberId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.VALUES("bill_item_id", "#{billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemName() != null) {
            sql.VALUES("bill_item_name", "#{billItemName,jdbcType=VARCHAR}");
        }
        
        if (record.getBillSn() != null) {
            sql.VALUES("bill_sn", "#{billSn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.VALUES("checkout_date", "#{checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBillPushDate() != null) {
            sql.VALUES("bill_push_date", "#{billPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmout() != null) {
            sql.VALUES("amout", "#{amout,jdbcType=DOUBLE}");
        }
        
        if (record.getActualPay() != null) {
            sql.VALUES("actual_pay", "#{actualPay,jdbcType=DOUBLE}");
        }
        
        if (record.getPaid() != null) {
            sql.VALUES("paid", "#{paid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getPayType() != null) {
            sql.VALUES("pay_type", "#{payType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushTime() != null) {
            sql.VALUES("push_time", "#{pushTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushed() != null) {
            sql.VALUES("pushed", "#{pushed,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.VALUES("pay_time", "#{payTime,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String selectByExample(TPropertyHouseholdBillCriteria example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("company_id");
        sql.SELECT("park_id");
        sql.SELECT("household_id");
        sql.SELECT("member_id");
        sql.SELECT("bill_item_id");
        sql.SELECT("bill_item_name");
        sql.SELECT("bill_sn");
        sql.SELECT("checkout_date");
        sql.SELECT("bill_push_date");
        sql.SELECT("amout");
        sql.SELECT("actual_pay");
        sql.SELECT("paid");
        sql.SELECT("order_id");
        sql.SELECT("pay_type");
        sql.SELECT("create_time");
        sql.SELECT("push_time");
        sql.SELECT("pushed");
        sql.SELECT("pay_time");
        sql.FROM("t_property_household_bill");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        TPropertyHouseholdBill record = (TPropertyHouseholdBill) parameter.get("record");
        TPropertyHouseholdBillCriteria example = (TPropertyHouseholdBillCriteria) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_property_household_bill");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        }
        
        if (record.getHouseholdId() != null) {
            sql.SET("household_id = #{record.householdId,jdbcType=INTEGER}");
        }
        
        if (record.getMemberId() != null) {
            sql.SET("member_id = #{record.memberId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.SET("bill_item_id = #{record.billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemName() != null) {
            sql.SET("bill_item_name = #{record.billItemName,jdbcType=VARCHAR}");
        }
        
        if (record.getBillSn() != null) {
            sql.SET("bill_sn = #{record.billSn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.SET("checkout_date = #{record.checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBillPushDate() != null) {
            sql.SET("bill_push_date = #{record.billPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmout() != null) {
            sql.SET("amout = #{record.amout,jdbcType=DOUBLE}");
        }
        
        if (record.getActualPay() != null) {
            sql.SET("actual_pay = #{record.actualPay,jdbcType=DOUBLE}");
        }
        
        if (record.getPaid() != null) {
            sql.SET("paid = #{record.paid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{record.orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getPayType() != null) {
            sql.SET("pay_type = #{record.payType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushTime() != null) {
            sql.SET("push_time = #{record.pushTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushed() != null) {
            sql.SET("pushed = #{record.pushed,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{record.payTime,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_property_household_bill");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("company_id = #{record.companyId,jdbcType=INTEGER}");
        sql.SET("park_id = #{record.parkId,jdbcType=INTEGER}");
        sql.SET("household_id = #{record.householdId,jdbcType=INTEGER}");
        sql.SET("member_id = #{record.memberId,jdbcType=INTEGER}");
        sql.SET("bill_item_id = #{record.billItemId,jdbcType=INTEGER}");
        sql.SET("bill_item_name = #{record.billItemName,jdbcType=VARCHAR}");
        sql.SET("bill_sn = #{record.billSn,jdbcType=VARCHAR}");
        sql.SET("checkout_date = #{record.checkoutDate,jdbcType=VARCHAR}");
        sql.SET("bill_push_date = #{record.billPushDate,jdbcType=VARCHAR}");
        sql.SET("amout = #{record.amout,jdbcType=DOUBLE}");
        sql.SET("actual_pay = #{record.actualPay,jdbcType=DOUBLE}");
        sql.SET("paid = #{record.paid,jdbcType=INTEGER}");
        sql.SET("order_id = #{record.orderId,jdbcType=VARCHAR}");
        sql.SET("pay_type = #{record.payType,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=VARCHAR}");
        sql.SET("push_time = #{record.pushTime,jdbcType=VARCHAR}");
        sql.SET("pushed = #{record.pushed,jdbcType=INTEGER}");
        sql.SET("pay_time = #{record.payTime,jdbcType=VARCHAR}");
        
        TPropertyHouseholdBillCriteria example = (TPropertyHouseholdBillCriteria) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TPropertyHouseholdBill record) {
        SQL sql = new SQL();
        sql.UPDATE("t_property_household_bill");
        
        if (record.getCompanyId() != null) {
            sql.SET("company_id = #{companyId,jdbcType=INTEGER}");
        }
        
        if (record.getParkId() != null) {
            sql.SET("park_id = #{parkId,jdbcType=INTEGER}");
        }
        
        if (record.getHouseholdId() != null) {
            sql.SET("household_id = #{householdId,jdbcType=INTEGER}");
        }
        
        if (record.getMemberId() != null) {
            sql.SET("member_id = #{memberId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemId() != null) {
            sql.SET("bill_item_id = #{billItemId,jdbcType=INTEGER}");
        }
        
        if (record.getBillItemName() != null) {
            sql.SET("bill_item_name = #{billItemName,jdbcType=VARCHAR}");
        }
        
        if (record.getBillSn() != null) {
            sql.SET("bill_sn = #{billSn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckoutDate() != null) {
            sql.SET("checkout_date = #{checkoutDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBillPushDate() != null) {
            sql.SET("bill_push_date = #{billPushDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAmout() != null) {
            sql.SET("amout = #{amout,jdbcType=DOUBLE}");
        }
        
        if (record.getActualPay() != null) {
            sql.SET("actual_pay = #{actualPay,jdbcType=DOUBLE}");
        }
        
        if (record.getPaid() != null) {
            sql.SET("paid = #{paid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getPayType() != null) {
            sql.SET("pay_type = #{payType,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushTime() != null) {
            sql.SET("push_time = #{pushTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPushed() != null) {
            sql.SET("pushed = #{pushed,jdbcType=INTEGER}");
        }
        
        if (record.getPayTime() != null) {
            sql.SET("pay_time = #{payTime,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_property_household_bill
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, TPropertyHouseholdBillCriteria example, boolean includeExamplePhrase) {
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