package com.eparking.service.impl;

import com.eparking.service.PropertyBillItemsService;
import org.springframework.stereotype.Service;

@Service
public class PropertyBillItemsServiceImpl implements PropertyBillItemsService {

/*    @Autowired
    private TPropertyBillItemsMapper tPropertyBillItemsMapper;
    @Autowired
    private PropertyAutobillService propertyAutobillService;

    *//**
     * 设置查询条件
     * @param tPropertyBillItems
     * @return
     *//*
    private TPropertyBillItemsCriteria setCriteria(TPropertyBillItems tPropertyBillItems){
        TPropertyBillItemsCriteria tPropertyBillItemsCriteria = new TPropertyBillItemsCriteria();
        if(tPropertyBillItems!=null){
            TPropertyBillItemsCriteria.Criteria criteria = tPropertyBillItemsCriteria.createCriteria();
            if(tPropertyBillItems.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyBillItems.getParkId());
            }
            if (tPropertyBillItems.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyBillItems.getCompanyId());
            }
            if(tPropertyBillItems.getBillName()!=null && tPropertyBillItems.getBillName()!=""){
                criteria.aneparkingdbillNameLike("%"+tPropertyBillItems.getBillName()+"%");
            }
            if (tPropertyBillItems.getItemsType()!=null){
                criteria.andItemsTypeEqualTo(tPropertyBillItems.getItemsType());
            }
            if (tPropertyBillItems.getItemsAmount()!=null){
                criteria.andItemsAmountEqualTo(tPropertyBillItems.getItemsAmount());
            }
            if (tPropertyBillItems.getCreateTime()!=null && tPropertyBillItems.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyBillItems.getCreateTime());
            }
            if (tPropertyBillItems.getUpdateTime()!=null && tPropertyBillItems.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tPropertyBillItems.getUpdateTime());
            }
            if(tPropertyBillItems.getId()!=null){

                criteria.andIdEqualTo(tPropertyBillItems.getId());
            }
        }
        return  tPropertyBillItemsCriteria;
    }
    *//**
     * 获取数据总量
     * @param tPropertyBillItems
     * @return
     *//*
    private Integer getCount(TPropertyBillItems tPropertyBillItems){
        Integer total =(int)tPropertyBillItemsMapper.countByExample(setCriteria(tPropertyBillItems));
        return total;
    }

    @Override
    public PageBean<TPropertyBillItems> getPropertyBillItemsbyPage(TPropertyBillItems tPropertyBillItems, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TPropertyBillItems> tPropertyBillItemsLists=getPropertyBillItems(tPropertyBillItems);
        Integer countNums =getCount(tPropertyBillItems);
        PageBean<TPropertyBillItems> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tPropertyBillItemsLists);
        return pageData;
    }

    @Override
    public List<TPropertyBillItems> getPropertyBillItems(TPropertyBillItems tPropertyBillItems) {
        List<TPropertyBillItems> tPropertyBillItemsLists = tPropertyBillItemsMapper.selectByExample(setCriteria(tPropertyBillItems));
        return tPropertyBillItemsLists;
    }

    @Override
    public String updatePropertyBillItems(TPropertyBillItems tPropertyBillItems) {
        String msg="";
        if (tPropertyBillItems.getId()!=null){//编辑
            tPropertyBillItemsMapper.updateByPrimaryKeySelective(tPropertyBillItems);
            msg = "编辑成功";
        }else{//新增
            tPropertyBillItemsMapper.insertSelective(tPropertyBillItems);
            msg = "成功添加";
        }
        return msg;
    }

    @Override
    public String deletePropertyBillItems(TPropertyBillItems tPropertyBillItems) {
        TPropertyAutobill tPropertyAutobill = new TPropertyAutobill();
        tPropertyAutobill.setParkId(tPropertyBillItems.getParkId());
        tPropertyAutobill.setCompanyId(tPropertyBillItems.getCompanyId());
        tPropertyAutobill.setBillItemId(tPropertyBillItems.getId());
        tPropertyAutobill.setBillName(tPropertyBillItems.getBillName());
        if(propertyAutobillService.getPropertyAutobill(tPropertyAutobill).size()>0){
            throw new ActionRspException(ActionRspEnum.BillItemsUsed_ERROR);
        }else{
            tPropertyBillItems = tPropertyBillItemsMapper.selectByPrimaryKey(tPropertyBillItems.getId());
            tPropertyBillItemsMapper.deleteByPrimaryKey(tPropertyBillItems.getId());
            return "删除成功";
        }
    }*/
}
