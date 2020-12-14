package com.eparking.service.impl;

import com.eparking.service.PropertyHouseholdInfoService;
import org.springframework.stereotype.Service;

@Service
public class PropertyHouseholdInfoServiceImpl implements PropertyHouseholdInfoService {

/*    @Autowired
    private TPropertyHouseholdInfoMapper tPropertyHouseholdInfoMapper;
    @Autowired
    private CustomizeMapper customizeMapper;

    @Autowired
    private CarParkService carParkService;
    *//**
     * 设置查询条件
     * @param tPropertyHouseholdInfo
     * @return
     *//*
    private TPropertyHouseholdInfoCriteria setCriteria(TPropertyHouseholdInfo tPropertyHouseholdInfo){
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
    *//**
     * 获取数据总量
     * @param tPropertyHouseholdInfo
     * @return
     *//*
    private Integer getCount(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        Integer total =(int)tPropertyHouseholdInfoMapper.countByExample(setCriteria(tPropertyHouseholdInfo));
        return total;
    }

    @Override
    public PageBean<TPropertyHouseholdInfo> getPropertyHouseholeparkingdbyPage(TPropertyHouseholdInfo tPropertyHouseholdInfo, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TPropertyHouseholdInfo> tPropertyHouseholdInfoLists=getPropertyHouseholdInfo(tPropertyHouseholdInfo);
        Integer countNums =getCount(tPropertyHouseholdInfo);
        PageBean<TPropertyHouseholdInfo> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tPropertyHouseholdInfoLists);
        return pageData;
    }

    @Override
    public List<TPropertyHouseholdInfo> getPropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo) {
        List<TPropertyHouseholdInfo> tPropertyHouseholdInfoLists = tPropertyHouseholdInfoMapper.selectByExample(setCriteria(tPropertyHouseholdInfo));
        return tPropertyHouseholdInfoLists;
    }

    @Override
    public String updatePropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo) {
        String msg="";
        if (tPropertyHouseholdInfo.getId()!=null){//编辑
            tPropertyHouseholdInfoMapper.updateByPrimaryKeySelective(tPropertyHouseholdInfo);
            msg = "编辑成功";
        }else{//新增
            tPropertyHouseholdInfoMapper.insertSelective(tPropertyHouseholdInfo);
            msg = "成功添加";
        }
        return msg;
    }

    @Override
    public String deletePropertyHouseholdInfo(TPropertyHouseholdInfo tPropertyHouseholdInfo) {
        tPropertyHouseholdInfo = tPropertyHouseholdInfoMapper.selectByPrimaryKey(tPropertyHouseholdInfo.getId());
        tPropertyHouseholdInfoMapper.deleteByPrimaryKey(tPropertyHouseholdInfo.getId());
        return "删除成功";
    }

    @Override
    public String importHouseHoldInfo(String fileName, MultipartFile file, Integer parkID) throws IOException {
        return null;
    }*/
}
