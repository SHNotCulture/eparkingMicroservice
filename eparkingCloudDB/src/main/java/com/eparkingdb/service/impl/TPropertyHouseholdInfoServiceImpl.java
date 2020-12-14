package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfo;
import com.common.entity.eparkingCloud.TPropertyHouseholdInfoCriteria;
import com.eparkingdb.dao.TPropertyHouseholdInfoMapper;
import com.eparkingdb.service.TPropertyHouseholdInfoService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPropertyHouseholdInfoService接口实现类
* @author 谢轩然
* @date 2020/04/28 15:20
*/
@Service
public class TPropertyHouseholdInfoServiceImpl  implements TPropertyHouseholdInfoService {

    private  static final Logger logger= LoggerFactory.getLogger( TPropertyHouseholdInfoServiceImpl.class);
    @Autowired
    private TPropertyHouseholdInfoMapper tPropertyHouseholdInfoMapper;

    /**
    * 设置查询条件
    * @param tPropertyHouseholdInfo
    * @return
    */
    private  TPropertyHouseholdInfoCriteria setCriteria(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        TPropertyHouseholdInfoCriteria tPropertyHouseholdInfoCriteria = new TPropertyHouseholdInfoCriteria();
        if(tPropertyHouseholdInfo!=null){
            TPropertyHouseholdInfoCriteria.Criteria criteria = tPropertyHouseholdInfoCriteria.createCriteria();
            if(tPropertyHouseholdInfo.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyHouseholdInfo.getParkId());
            }
            if (tPropertyHouseholdInfo.getMemberId()!=null){
                criteria.andMemberIdEqualTo(tPropertyHouseholdInfo.getMemberId());
            }
            if (tPropertyHouseholdInfo.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyHouseholdInfo.getCompanyId());
            }
            if(tPropertyHouseholdInfo.getName()!=null && tPropertyHouseholdInfo.getName()!=""){
                criteria.andNameLike("%"+tPropertyHouseholdInfo.getName()+"%");
            }
            if(tPropertyHouseholdInfo.getSex()!=null){
                criteria.andSexEqualTo(tPropertyHouseholdInfo.getSex());
            }
            if (tPropertyHouseholdInfo.getPhoneNumber()!=null && tPropertyHouseholdInfo.getPhoneNumber()!=""){
                criteria.andPhoneNumberEqualTo(tPropertyHouseholdInfo.getPhoneNumber());
            }
            if (tPropertyHouseholdInfo.getVillageName()!=null && tPropertyHouseholdInfo.getVillageName()!=""){
                criteria.andVillageNameLike("%"+tPropertyHouseholdInfo.getVillageName()+"%");
            }
            if (tPropertyHouseholdInfo.getAddress()!=null && tPropertyHouseholdInfo.getAddress()!=""){
                criteria.andAddressLike("%"+tPropertyHouseholdInfo.getAddress()+"%");
            }
            if (tPropertyHouseholdInfo.getHouseArea()!=null){
                criteria.andHouseAreaEqualTo(tPropertyHouseholdInfo.getHouseArea());
            }
            if (tPropertyHouseholdInfo.getIsAuditing()!=null){
                criteria.andIsAuditingEqualTo(tPropertyHouseholdInfo.getIsAuditing());
            }
            if (tPropertyHouseholdInfo.getCreateTime()!=null && tPropertyHouseholdInfo.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyHouseholdInfo.getCreateTime());
            }
            if (tPropertyHouseholdInfo.getAuditingTime()!=null && tPropertyHouseholdInfo.getAuditingTime()!=""){
                criteria.andAuditingTimeEqualTo(tPropertyHouseholdInfo.getAuditingTime());
            }
            if (tPropertyHouseholdInfo.getAuditingUserId()!=null){
                criteria.andAuditingUserIdEqualTo(tPropertyHouseholdInfo.getAuditingUserId());
            }
            if (tPropertyHouseholdInfo.getUpdateTime()!=null && tPropertyHouseholdInfo.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tPropertyHouseholdInfo.getUpdateTime());
            }
        }
        return  tPropertyHouseholdInfoCriteria;
    }

    /**
    * 获取数据总量
    * @param tPropertyHouseholdInfo
    * @return
    */
    private Integer getCount(TPropertyHouseholdInfo tPropertyHouseholdInfo){
    Integer total =(int)tPropertyHouseholdInfoMapper.countByExample(setCriteria(tPropertyHouseholdInfo));
    return total;
    }

    /**
    *查询tPropertyHouseholdInfo(分页)
    * @param tPropertyHouseholdInfo
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TPropertyHouseholdInfo> getTPropertyHouseholdInfobyPage(TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TPropertyHouseholdInfo> tPropertyHouseholdInfos=getTPropertyHouseholdInfo(tPropertyHouseholdInfo);
            Integer countNums =getCount(tPropertyHouseholdInfo);
            PageBean<TPropertyHouseholdInfo> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tPropertyHouseholdInfos);
            return pageData;
        }

    /**
    * 查询tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    public List<TPropertyHouseholdInfo> getTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo){
    List<TPropertyHouseholdInfo>  tPropertyHouseholdInfos=tPropertyHouseholdInfoMapper.selectByExample(setCriteria(tPropertyHouseholdInfo));
    return tPropertyHouseholdInfos;
    }

    /**
    * 更新tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    public String UpdateTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo)
    {
            String msg="";
            try{
            if(tPropertyHouseholdInfo.getId()!=null){
                tPropertyHouseholdInfoMapper.updateByPrimaryKeySelective(tPropertyHouseholdInfo);
                msg = "编辑成功";
            }
            else
            {
                tPropertyHouseholdInfoMapper.insertSelective(tPropertyHouseholdInfo);
                msg = "成功添加";
            }
            }
            catch (Exception e)
            {
                logger.info(e.toString());
            }
            return msg;
    }

    /**
    * 删除tPropertyHouseholdInfo
    * @param tPropertyHouseholdInfo
    * @return
    */
    public String DeleteTPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo){
            String msg="";
            if(tPropertyHouseholdInfo.getId()!=null){
                tPropertyHouseholdInfo = tPropertyHouseholdInfoMapper.selectByPrimaryKey(tPropertyHouseholdInfo.getId());
                tPropertyHouseholdInfoMapper.deleteByPrimaryKey(tPropertyHouseholdInfo.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tPropertyHouseholdInfo
    * @param id
    * @return
    */
    public TPropertyHouseholdInfo getTPropertyHouseholdInfoByID(Integer id) {
        return  tPropertyHouseholdInfoMapper.selectByPrimaryKey(id);
    }
}
