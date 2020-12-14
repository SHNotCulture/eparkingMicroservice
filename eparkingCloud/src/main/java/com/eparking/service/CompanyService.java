package com.eparking.service;

import com.common.entity.eparkingCloud.TCompany;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:312018-9-14
 * @Modified By:
 */
public interface CompanyService {
    PageBean<TCompany> getCompanybyPage(TCompany tCompany, Integer page, Integer limit);
    List<TCompany> getCompany(TCompany tCompany);
    String UpdateComPany(TCompany tCompany);
    String DeleteComPany(TCompany tCompany);
    TCompany getCompanyById(Integer id);
}
