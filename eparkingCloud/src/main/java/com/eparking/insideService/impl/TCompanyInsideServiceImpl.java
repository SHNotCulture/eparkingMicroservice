package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCompany;
import com.eparking.insideService.TCompanyInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCompanyInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/15 16:12
*/
@Component
public class TCompanyInsideServiceImpl  implements TCompanyInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyInsideServiceImpl.class);

    /**
    * 查询tCompany
    * @param tCompany
    * @return
    */
    @Override
    public List<TCompany> getTCompany(TCompany tCompany){
        String str = "查询TCompany失败！";
        logger.info("查询TCompany失败！");
        return null;
    }

    /**
    *查询(分页)tCompany
    * @param tCompany
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCompany> getTCompanybyPage(TCompany tCompany, Integer page, Integer limit){
        String str = "查询TCompany分页失败！";
        logger.info("查询TCompany分页失败！");
        return null;
    }

    /**
    * 更新tCompany
    * @param tCompany
    * @return
    */
    @Override
    public String UpdateTCompany(TCompany tCompany){
        String str = "更新TCompany失败！";
        logger.info("更新TCompany失败！");
        return str;
    }

    /**
    * 删除tCompany
    * @param tCompany
    * @return
    */
    @Override
    public String DeleteTCompany(TCompany tCompany){
        String str = "删除TCompany失败！";
        logger.info("删除TCompany失败！");
        return str;
    }

    @Override
    public TCompany getTCompanyById(Integer id) {
        String str = "查询TCompany失败！";
        logger.info("查询TCompany失败！");
        return null;
    }

    @Override
    public List<TCompany> getCompanyDistnict(TCompany tCompany) {
        String str = "查询是否存在重复物业信息失败！";
        logger.info("查询是否存在重复物业信息失败！");
        return null;
    }
}