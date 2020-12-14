package com.eparking.service.impl;

import com.eparking.service.PropertyAutobillService;
import org.springframework.stereotype.Service;

@Service
public class PropertyAutobillServiceImpl implements PropertyAutobillService {

/*    @Autowired
    private TPropertyAutobillMapper tPropertyAutobillMapper;

    *//**
     * 设置查询条件
     * @param tPropertyAutobill
     * @return
     *//*
    private TPropertyAutobillCriteria setCriteria(TPropertyAutobill tPropertyAutobill){
        TPropertyAutobillCriteria tPropertyAutobillCriteria = new TPropertyAutobillCriteria();
        if(tPropertyAutobill!=null){
            TPropertyAutobillCriteria.Criteria criteria = tPropertyAutobillCriteria.createCriteria();
            if(tPropertyAutobill.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyAutobill.getParkId());
            }
            if (tPropertyAutobill.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyAutobill.getCompanyId());
            }
            if (tPropertyAutobill.getBillItemId()!=null){
                criteria.aneparkingdbillItemIdEqualTo(tPropertyAutobill.getBillItemId());
            }
            if(tPropertyAutobill.getBillName()!=null && tPropertyAutobill.getBillName()!=""){
                criteria.aneparkingdbillNameLike("%"+tPropertyAutobill.getBillName()+"%");
            }
            if (tPropertyAutobill.getCheckoutDate()!=null && tPropertyAutobill.getCheckoutDate()!=""){
                criteria.andCheckoutDateEqualTo(tPropertyAutobill.getCheckoutDate());
            }
            if (tPropertyAutobill.getAutobillPushDate()!=null && tPropertyAutobill.getAutobillPushDate()!=""){
                criteria.andAutobillPushDateEqualTo(tPropertyAutobill.getAutobillPushDate());
            }
            if (tPropertyAutobill.getAmount()!=null){
                criteria.andAmountEqualTo(tPropertyAutobill.getAmount());
            }
            if (tPropertyAutobill.getCreateTime()!=null && tPropertyAutobill.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyAutobill.getCreateTime());
            }
            if (tPropertyAutobill.getUpdateTime()!=null && tPropertyAutobill.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tPropertyAutobill.getUpdateTime());
            }
        }
        return  tPropertyAutobillCriteria;
    }
    *//**
     * 获取数据总量
     * @param tPropertyAutobill
     * @return
     *//*
    private Integer getCount(TPropertyAutobill tPropertyAutobill){
        Integer total =(int)tPropertyAutobillMapper.countByExample(setCriteria(tPropertyAutobill));
        return total;
    }

    @Override
    public PageBean<TPropertyAutobill> getPropertyAutobillbyPage(TPropertyAutobill tPropertyAutobill, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TPropertyAutobill> tPropertyAutobillLists=getPropertyAutobill(tPropertyAutobill);
        Integer countNums =getCount(tPropertyAutobill);
        PageBean<TPropertyAutobill> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tPropertyAutobillLists);
        return pageData;
    }

    @Override
    public List<TPropertyAutobill> getPropertyAutobill(TPropertyAutobill tPropertyAutobill) {
        List<TPropertyAutobill> tPropertyAutobillLists = tPropertyAutobillMapper.selectByExample(setCriteria(tPropertyAutobill));
        return tPropertyAutobillLists;
    }

    @Override
    public String updatePropertyAutobill(TPropertyAutobill tPropertyAutobill) {
        String msg="";
        if (tPropertyAutobill.getId()!=null){//编辑
            tPropertyAutobillMapper.updateByPrimaryKeySelective(tPropertyAutobill);
            msg = "编辑成功";
        }else{//新增
            tPropertyAutobillMapper.insertSelective(tPropertyAutobill);
            msg = "成功添加";
        }
        return msg;
    }

    @Override
    public String deletePropertyAutobill(TPropertyAutobill tPropertyAutobill) {
        tPropertyAutobill = tPropertyAutobillMapper.selectByPrimaryKey(tPropertyAutobill.getId());
        tPropertyAutobillMapper.deleteByPrimaryKey(tPropertyAutobill.getId());
        return "删除成功";
    }*/
}
