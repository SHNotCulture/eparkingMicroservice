package com.eparking.insideService.impl;

import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.TCompanyUserInsideService;
import com.common.util.ActionRspUtil;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCompanyUserInsideService接口熔断器
* @author 谢轩然
* @date 2020/04/20 11:41
*/
@Component
public class TCompanyUserInsideServiceImpl  implements TCompanyUserInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyUserInsideServiceImpl.class);

    /**
    * 查询tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @Override
    public List<TCompanyUser> getTCompanyUser(TCompanyUser tCompanyUser){
        String str = "查询TCompanyUser失败！";
        logger.info("查询TCompanyUser失败！");
        return null;
    }

    /**
    *查询(分页)tCompanyUser
    * @param tCompanyUser
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCompanyUser> getTCompanyUserbyPage(TCompanyUser tCompanyUser, Integer page, Integer limit){
        String str = "查询TCompanyUser分页失败！";
        logger.info("查询TCompanyUser分页失败！");
        return null;
    }

    /**
    * 更新tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @Override
    public String UpdateTCompanyUser(TCompanyUser tCompanyUser){
        String str = "更新TCompanyUser失败！";
        logger.info("更新TCompanyUser失败！");
        return str;
    }

    /**
    * 删除tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @Override
    public String DeleteTCompanyUser(TCompanyUser tCompanyUser){
        String str = "删除TCompanyUser失败！";
        logger.info("删除TCompanyUser失败！");
        return str;
    }

    @Override
    public TCompanyUser getTCompanyUserByID(Integer id) {
        String str = "根据ID查询物业用户失败！";
        logger.info("根据ID查询物业用户失败！");
        return null;
    }

    @Override
    public TCompanyUser CheckPassword(TCompanyUser tCompanyUser) {
        String str = "核对登录用户信息失败！";
        logger.info("核对登录用户信息失败！");
        return null;
    }





    @Override
    public ActionRsp changePassword(Integer id, String password, String newPassword, String resetPassword) {
        String str = "修改用户密码失败！";
        logger.info("修改用户密码失败！");
        return ActionRspUtil.Error(1,str);
    }

    @Override
    public ActionRsp resetPassword(Integer id) {
        String str = "重置物业用户密码失败！";
        logger.info("重置物业用户密码失败！");
        return ActionRspUtil.Error(1,str);
    }

}