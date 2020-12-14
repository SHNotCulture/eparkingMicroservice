package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompany;
import com.common.entity.eparkingCloud.TCompanyCriteria;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparkingdb.dao.TCompanyMapper;
import com.eparkingdb.dao.TCompanyUserMapper;
import com.eparkingdb.service.TCompanyParkService;
import com.eparkingdb.service.TCompanyService;
import com.common.util.MD5Util;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TCompanyService接口实现类
* @author 谢轩然
* @date 2020/04/09 14:42
*/
@Service
public class TCompanyServiceImpl  implements TCompanyService {

    private  static final Logger logger= LoggerFactory.getLogger( TCompanyServiceImpl.class);
    @Autowired
    private TCompanyMapper tCompanyMapper;
    @Autowired
    private TCompanyParkService tCompanyParkService;
    @Autowired
    private TCompanyUserMapper tCompanyUserMapper;

    /**
    * 设置查询条件
    * @param tCompany
    * @return
    */
    private  TCompanyCriteria setCriteria(TCompany tCompany){
        TCompanyCriteria tCompanyCriteria= new TCompanyCriteria();
        if(tCompany!=null){
            TCompanyCriteria.Criteria criteria=tCompanyCriteria.createCriteria();
            if(tCompany.getCompanyName()!=null)
            {
                criteria.andCompanyNameLike("%"+tCompany.getCompanyName()+"%");
            }
            if(tCompany.getCompanyType()!=null)
            {
                criteria.andCompanyTypeEqualTo(tCompany.getCompanyType());
            }
            if(tCompany.getStatus()!=null)
            {
                criteria.andStatusEqualTo(tCompany.getStatus());
            }
            if(tCompany.getId()!=null)
            {
                criteria.andIdEqualTo(tCompany.getId());
            }
        }
        return  tCompanyCriteria;
    }

    /**
    * 获取数据总量
    * @param tCompany
    * @return
    */
    private Integer getCount(TCompany tCompany){
    Integer total =(int)tCompanyMapper.countByExample(setCriteria(tCompany));
    return total;
    }

    /**
    *查询tCompany(分页)
    * @param tCompany
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCompany> getTCompanybyPage(TCompany tCompany, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCompany> tCompanys=getTCompany(tCompany);
            Integer countNums =getCount(tCompany);
            PageBean<TCompany> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCompanys);
            return pageData;
        }

    /**
    * 查询tCompany
    * @param tCompany
    * @return
    */
    public List<TCompany> getTCompany(TCompany tCompany){
    List<TCompany>  tCompanys=tCompanyMapper.selectByExample(setCriteria(tCompany));
    return tCompanys;
    }

    /**
    * 更新tCompany
    * @param tCompany
    * @return
    */
    public String UpdateTCompany(TCompany tCompany)
    {
//        logger.info(tCompany.toString());
        String result="";
        try{
            List<TCompany> tCompanyList=getCompanyDistnict(tCompany);
            if(tCompanyList.size()==0){
                //修改
                if(tCompany.getId()!=null){
                    tCompanyMapper.updateByPrimaryKeySelective(tCompany);
                }
                else
                {
                    //新增物业公司信息只可能是系统管理员
                    tCompanyMapper.insertSelective(tCompany);
                    //得到新增后的公司信息
                    TCompany tCompanyRes=getCompanyDistnict(tCompany).get(0);
                    //实例化物业管理员
                    TCompanyUser user=new TCompanyUser();
                    user.setCompanyId(tCompanyRes.getId());
                    user.setRoleId(1);//默认物业超级账户角色
                    user.setUserName(tCompanyRes.getCompanyName());
                    user.setUserAccout(tCompanyRes.getAccount());
                    user.setPassword(MD5Util.MD5Encode(tCompanyRes.getPassword()));
                    user.setIsAdmin("1");//默认物业超级账户
                    user.setEntityType(1);//默认物业后台
                    user.setParentUser(0);//默认超级管理员admin_ID
                    user.setParkIds("0");
                    //查询该物业下所有的车场信息
/*                    TCompanyPark companyPark=new TCompanyPark();
                    companyPark.setCompanyId(tCompanyRes.getId());
                    String ParkIds="";
                    List<TCompanyPark> tCompanyParkList=tCompanyParkService.getTCompanyPark(companyPark);
                    if(tCompanyParkList!=null)
                    {
                        for (TCompanyPark park:tCompanyParkList) {
                            ParkIds+=park.getId()+",";
                        }
                    }
                    user.setParkIds(ParkIds);*///管理员拥有该物业下所有的车场权限*//*
                    tCompanyUserMapper.insertSelective(user);
                }
                result="更新成功";
            }
            else
            {
                result="更新失败,物业信息重复";

            }
        }
        catch (Exception e)
        {

        }



        return result;
    }

    /**
    * 删除tCompany
    * @param tCompany
    * @return
    */
    public String DeleteTCompany(TCompany tCompany){
            String msg="";
            if(tCompany.getId()!=null){
            tCompanyMapper.deleteByPrimaryKey(tCompany.getId());
            msg="删除TCompany成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tCompany
    * @param id
    * @return
    */
    public TCompany getTCompanyByID(Integer id) {
        return  tCompanyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TCompany> getCompanyDistnict(TCompany tCompany) {
        TCompanyCriteria tCompanyCriteria= new TCompanyCriteria();
        if(tCompany!=null){
            TCompanyCriteria.Criteria criteria=tCompanyCriteria.createCriteria();
            if(tCompany.getAccount()!=null)
            {
                criteria.andAccountEqualTo(tCompany.getAccount());
            }
            if(tCompany.getId()!=null)
            {
                criteria.andIdNotEqualTo(tCompany.getId());
            }
        }
        List<TCompany> tCompanyList=tCompanyMapper.selectByExample(tCompanyCriteria);
        return tCompanyList;
    }
}
