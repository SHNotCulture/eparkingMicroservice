package com.eparking.service;

import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:422018-8-29
 * @Modified By:
 */
public interface UserService {
    PageBean<TCompanyUser> getCompanyUserbyPage(TCompanyUser tCompanyUser, Integer page, Integer limit);
    List<TCompanyUser> getCompanyUser(TCompanyUser tCompanyUser);
    String UpdateCompanyUser(TCompanyUser tCompanyUser);
    String DeleteCompanyUser(TCompanyUser tCompanyUser);
    TCompanyUser CheckPassword(TCompanyUser tCompanyUser);
    TCompanyUser selectByPrimaryKey(Integer id);
}
