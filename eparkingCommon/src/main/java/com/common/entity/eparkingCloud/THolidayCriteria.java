package com.common.entity.eparkingCloud;

import java.util.ArrayList;
import java.util.List;

public class THolidayCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public THolidayCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIeparkingdbetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayIsNull() {
            addCriterion("begin_day is null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayIsNotNull() {
            addCriterion("begin_day is not null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayEqualTo(Integer value) {
            addCriterion("begin_day =", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayNotEqualTo(Integer value) {
            addCriterion("begin_day <>", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayGreaterThan(Integer value) {
            addCriterion("begin_day >", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_day >=", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayLessThan(Integer value) {
            addCriterion("begin_day <", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayLessThanOrEqualTo(Integer value) {
            addCriterion("begin_day <=", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayIn(List<Integer> values) {
            addCriterion("begin_day in", values, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayNotIn(List<Integer> values) {
            addCriterion("begin_day not in", values, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayBetween(Integer value1, Integer value2) {
            addCriterion("begin_day between", value1, value2, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginDayNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_day not between", value1, value2, "beginDay");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthIsNull() {
            addCriterion("begin_month is null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthIsNotNull() {
            addCriterion("begin_month is not null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthEqualTo(Integer value) {
            addCriterion("begin_month =", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthNotEqualTo(Integer value) {
            addCriterion("begin_month <>", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthGreaterThan(Integer value) {
            addCriterion("begin_month >", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_month >=", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthLessThan(Integer value) {
            addCriterion("begin_month <", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthLessThanOrEqualTo(Integer value) {
            addCriterion("begin_month <=", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthIn(List<Integer> values) {
            addCriterion("begin_month in", values, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthNotIn(List<Integer> values) {
            addCriterion("begin_month not in", values, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthBetween(Integer value1, Integer value2) {
            addCriterion("begin_month between", value1, value2, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_month not between", value1, value2, "beginMonth");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeEqualTo(String value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeNotEqualTo(String value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeGreaterThan(String value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeLessThan(String value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeLessThanOrEqualTo(String value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeLike(String value) {
            addCriterion("begin_time like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeNotLike(String value) {
            addCriterion("begin_time not like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeIn(List<String> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeNotIn(List<String> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeBetween(String value1, String value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria aneparkingdbeginTimeNotBetween(String value1, String value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagIsNull() {
            addCriterion("cascade_flag is null");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagIsNotNull() {
            addCriterion("cascade_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagEqualTo(Integer value) {
            addCriterion("cascade_flag =", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagNotEqualTo(Integer value) {
            addCriterion("cascade_flag <>", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagGreaterThan(Integer value) {
            addCriterion("cascade_flag >", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("cascade_flag >=", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagLessThan(Integer value) {
            addCriterion("cascade_flag <", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagLessThanOrEqualTo(Integer value) {
            addCriterion("cascade_flag <=", value, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagIn(List<Integer> values) {
            addCriterion("cascade_flag in", values, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagNotIn(List<Integer> values) {
            addCriterion("cascade_flag not in", values, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagBetween(Integer value1, Integer value2) {
            addCriterion("cascade_flag between", value1, value2, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("cascade_flag not between", value1, value2, "cascadeFlag");
            return (Criteria) this;
        }

        public Criteria andCascadeIdIsNull() {
            addCriterion("cascade_id is null");
            return (Criteria) this;
        }

        public Criteria andCascadeIdIsNotNull() {
            addCriterion("cascade_id is not null");
            return (Criteria) this;
        }

        public Criteria andCascadeIdEqualTo(Integer value) {
            addCriterion("cascade_id =", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdNotEqualTo(Integer value) {
            addCriterion("cascade_id <>", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdGreaterThan(Integer value) {
            addCriterion("cascade_id >", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cascade_id >=", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdLessThan(Integer value) {
            addCriterion("cascade_id <", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdLessThanOrEqualTo(Integer value) {
            addCriterion("cascade_id <=", value, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdIn(List<Integer> values) {
            addCriterion("cascade_id in", values, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdNotIn(List<Integer> values) {
            addCriterion("cascade_id not in", values, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIeparkingdbetween(Integer value1, Integer value2) {
            addCriterion("cascade_id between", value1, value2, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCascadeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cascade_id not between", value1, value2, "cascadeId");
            return (Criteria) this;
        }

        public Criteria andCompIndexIsNull() {
            addCriterion("comp_index is null");
            return (Criteria) this;
        }

        public Criteria andCompIndexIsNotNull() {
            addCriterion("comp_index is not null");
            return (Criteria) this;
        }

        public Criteria andCompIndexEqualTo(String value) {
            addCriterion("comp_index =", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexNotEqualTo(String value) {
            addCriterion("comp_index <>", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexGreaterThan(String value) {
            addCriterion("comp_index >", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexGreaterThanOrEqualTo(String value) {
            addCriterion("comp_index >=", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexLessThan(String value) {
            addCriterion("comp_index <", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexLessThanOrEqualTo(String value) {
            addCriterion("comp_index <=", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexLike(String value) {
            addCriterion("comp_index like", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexNotLike(String value) {
            addCriterion("comp_index not like", value, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexIn(List<String> values) {
            addCriterion("comp_index in", values, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexNotIn(List<String> values) {
            addCriterion("comp_index not in", values, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexBetween(String value1, String value2) {
            addCriterion("comp_index between", value1, value2, "compIndex");
            return (Criteria) this;
        }

        public Criteria andCompIndexNotBetween(String value1, String value2) {
            addCriterion("comp_index not between", value1, value2, "compIndex");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNull() {
            addCriterion("day_count is null");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNotNull() {
            addCriterion("day_count is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountEqualTo(Integer value) {
            addCriterion("day_count =", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotEqualTo(Integer value) {
            addCriterion("day_count <>", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThan(Integer value) {
            addCriterion("day_count >", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_count >=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThan(Integer value) {
            addCriterion("day_count <", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThanOrEqualTo(Integer value) {
            addCriterion("day_count <=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountIn(List<Integer> values) {
            addCriterion("day_count in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotIn(List<Integer> values) {
            addCriterion("day_count not in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountBetween(Integer value1, Integer value2) {
            addCriterion("day_count between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotBetween(Integer value1, Integer value2) {
            addCriterion("day_count not between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andHolidayNameIsNull() {
            addCriterion("holiday_name is null");
            return (Criteria) this;
        }

        public Criteria andHolidayNameIsNotNull() {
            addCriterion("holiday_name is not null");
            return (Criteria) this;
        }

        public Criteria andHolidayNameEqualTo(String value) {
            addCriterion("holiday_name =", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameNotEqualTo(String value) {
            addCriterion("holiday_name <>", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameGreaterThan(String value) {
            addCriterion("holiday_name >", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameGreaterThanOrEqualTo(String value) {
            addCriterion("holiday_name >=", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameLessThan(String value) {
            addCriterion("holiday_name <", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameLessThanOrEqualTo(String value) {
            addCriterion("holiday_name <=", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameLike(String value) {
            addCriterion("holiday_name like", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameNotLike(String value) {
            addCriterion("holiday_name not like", value, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameIn(List<String> values) {
            addCriterion("holiday_name in", values, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameNotIn(List<String> values) {
            addCriterion("holiday_name not in", values, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameBetween(String value1, String value2) {
            addCriterion("holiday_name between", value1, value2, "holidayName");
            return (Criteria) this;
        }

        public Criteria andHolidayNameNotBetween(String value1, String value2) {
            addCriterion("holiday_name not between", value1, value2, "holidayName");
            return (Criteria) this;
        }

        public Criteria andLocidIsNull() {
            addCriterion("locId is null");
            return (Criteria) this;
        }

        public Criteria andLocidIsNotNull() {
            addCriterion("locId is not null");
            return (Criteria) this;
        }

        public Criteria andLocidEqualTo(Integer value) {
            addCriterion("locId =", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidNotEqualTo(Integer value) {
            addCriterion("locId <>", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidGreaterThan(Integer value) {
            addCriterion("locId >", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidGreaterThanOrEqualTo(Integer value) {
            addCriterion("locId >=", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidLessThan(Integer value) {
            addCriterion("locId <", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidLessThanOrEqualTo(Integer value) {
            addCriterion("locId <=", value, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidIn(List<Integer> values) {
            addCriterion("locId in", values, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidNotIn(List<Integer> values) {
            addCriterion("locId not in", values, "locid");
            return (Criteria) this;
        }

        public Criteria andLocieparkingdbetween(Integer value1, Integer value2) {
            addCriterion("locId between", value1, value2, "locid");
            return (Criteria) this;
        }

        public Criteria andLocidNotBetween(Integer value1, Integer value2) {
            addCriterion("locId not between", value1, value2, "locid");
            return (Criteria) this;
        }

        public Criteria andParkIdIsNull() {
            addCriterion("park_id is null");
            return (Criteria) this;
        }

        public Criteria andParkIdIsNotNull() {
            addCriterion("park_id is not null");
            return (Criteria) this;
        }

        public Criteria andParkIdEqualTo(Integer value) {
            addCriterion("park_id =", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotEqualTo(Integer value) {
            addCriterion("park_id <>", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThan(Integer value) {
            addCriterion("park_id >", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("park_id >=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThan(Integer value) {
            addCriterion("park_id <", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdLessThanOrEqualTo(Integer value) {
            addCriterion("park_id <=", value, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdIn(List<Integer> values) {
            addCriterion("park_id in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotIn(List<Integer> values) {
            addCriterion("park_id not in", values, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIeparkingdbetween(Integer value1, Integer value2) {
            addCriterion("park_id between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andParkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("park_id not between", value1, value2, "parkId");
            return (Criteria) this;
        }

        public Criteria andStaFlagIsNull() {
            addCriterion("sta_flag is null");
            return (Criteria) this;
        }

        public Criteria andStaFlagIsNotNull() {
            addCriterion("sta_flag is not null");
            return (Criteria) this;
        }

        public Criteria andStaFlagEqualTo(Integer value) {
            addCriterion("sta_flag =", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagNotEqualTo(Integer value) {
            addCriterion("sta_flag <>", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagGreaterThan(Integer value) {
            addCriterion("sta_flag >", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("sta_flag >=", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagLessThan(Integer value) {
            addCriterion("sta_flag <", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagLessThanOrEqualTo(Integer value) {
            addCriterion("sta_flag <=", value, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagIn(List<Integer> values) {
            addCriterion("sta_flag in", values, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagNotIn(List<Integer> values) {
            addCriterion("sta_flag not in", values, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagBetween(Integer value1, Integer value2) {
            addCriterion("sta_flag between", value1, value2, "staFlag");
            return (Criteria) this;
        }

        public Criteria andStaFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("sta_flag not between", value1, value2, "staFlag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holiday
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holiday
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}