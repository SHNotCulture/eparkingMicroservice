package com.common.entity.eparkingCloud;

public class TUserRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_role.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_role.company_user_id
     *
     * @mbg.generated
     */
    private Integer companyUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_role.role_power_id
     *
     * @mbg.generated
     */
    private Integer rolePowerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_role.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_role.id
     *
     * @return the value of t_user_role.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_role.id
     *
     * @param id the value for t_user_role.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_role.company_user_id
     *
     * @return the value of t_user_role.company_user_id
     *
     * @mbg.generated
     */
    public Integer getCompanyUserId() {
        return companyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_role.company_user_id
     *
     * @param companyUserId the value for t_user_role.company_user_id
     *
     * @mbg.generated
     */
    public void setCompanyUserId(Integer companyUserId) {
        this.companyUserId = companyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_role.role_power_id
     *
     * @return the value of t_user_role.role_power_id
     *
     * @mbg.generated
     */
    public Integer getRolePowerId() {
        return rolePowerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_role.role_power_id
     *
     * @param rolePowerId the value for t_user_role.role_power_id
     *
     * @mbg.generated
     */
    public void setRolePowerId(Integer rolePowerId) {
        this.rolePowerId = rolePowerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_role.remark
     *
     * @return the value of t_user_role.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_role.remark
     *
     * @param remark the value for t_user_role.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}