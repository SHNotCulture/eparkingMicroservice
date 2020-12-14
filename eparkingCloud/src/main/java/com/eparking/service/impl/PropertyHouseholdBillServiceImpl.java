package com.eparking.service.impl;

import com.eparking.service.PropertyHouseholdBillService;
import org.springframework.stereotype.Service;

@Service
public class PropertyHouseholdBillServiceImpl implements PropertyHouseholdBillService {

/*
    @Autowired
    private TPropertyHouseholdBillMapper tPropertyHouseholdBillMapper;

    */
/**
     * 设置查询条件
     * @param tPropertyHouseholdBill
     * @return
     *//*


    private TPropertyHouseholdBillCriteria setCriteria(List<Integer> list,TPropertyHouseholdBill tPropertyHouseholdBill){
        TPropertyHouseholdBillCriteria tPropertyHouseholdBillCriteria = new TPropertyHouseholdBillCriteria();
        if(tPropertyHouseholdBill!=null){
            TPropertyHouseholdBillCriteria.Criteria criteria = tPropertyHouseholdBillCriteria.createCriteria();
            if(tPropertyHouseholdBill.getParkId()!=null) {
                criteria.andParkIdEqualTo(tPropertyHouseholdBill.getParkId());
            }
            if (tPropertyHouseholdBill.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tPropertyHouseholdBill.getCompanyId());
            }
            if(tPropertyHouseholdBill.getMemberId()!=null){
                criteria.andMemberIdEqualTo(tPropertyHouseholdBill.getMemberId());
            }
            if(tPropertyHouseholdBill.getBillItemId()!=null){
                criteria.andBillItemIdEqualTo(tPropertyHouseholdBill.getBillItemId());
            }
            if (tPropertyHouseholdBill.getBillItemName()!=null && tPropertyHouseholdBill.getBillItemName()!=""){
                criteria.andBillItemNameLike("%"+tPropertyHouseholdBill.getBillItemName()+"%");
            }
            if (tPropertyHouseholdBill.getBillSn()!=null && tPropertyHouseholdBill.getBillSn()!=""){
                criteria.andBillSnEqualTo(tPropertyHouseholdBill.getBillSn());
            }
            if (tPropertyHouseholdBill.getCheckoutDate()!=null && tPropertyHouseholdBill.getCheckoutDate()!=""){
                criteria.andCheckoutDateEqualTo(tPropertyHouseholdBill.getCheckoutDate());
            }
            if (tPropertyHouseholdBill.getBillPushDate()!=null && tPropertyHouseholdBill.getBillPushDate()!=""){
                criteria.andBillPushDateEqualTo(tPropertyHouseholdBill.getBillPushDate());
            }
            if (tPropertyHouseholdBill.getAmout()!=null){
                criteria.andAmoutEqualTo(tPropertyHouseholdBill.getAmout());
            }
            if (tPropertyHouseholdBill.getActualPay()!=null){
                criteria.andActualPayEqualTo(tPropertyHouseholdBill.getActualPay());
            }
            if (tPropertyHouseholdBill.getPaid()!=null){
                criteria.andPaidEqualTo(tPropertyHouseholdBill.getPaid());
            }
            if (tPropertyHouseholdBill.getOrderId()!=null && tPropertyHouseholdBill.getOrderId()!=""){
                criteria.andOrderIdEqualTo(tPropertyHouseholdBill.getOrderId());
            }
            if (tPropertyHouseholdBill.getPayType()!=null){
                criteria.andPayTypeEqualTo(tPropertyHouseholdBill.getPayType());
            }
            if (tPropertyHouseholdBill.getCreateTime()!=null && tPropertyHouseholdBill.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tPropertyHouseholdBill.getCreateTime());
            }
            if (tPropertyHouseholdBill.getPushTime()!=null && tPropertyHouseholdBill.getPushTime()!=""){
                criteria.andPushTimeEqualTo(tPropertyHouseholdBill.getPushTime());
            }
            if (tPropertyHouseholdBill.getPushed()!=null){
                criteria.andPushedEqualTo(tPropertyHouseholdBill.getPushed());
            }
            if (tPropertyHouseholdBill.getPayTime()!=null && tPropertyHouseholdBill.getPayTime()!=""){
                criteria.andPayTimeEqualTo(tPropertyHouseholdBill.getPayTime());
            }
            if (list.size()>0){
                criteria.andHouseholdIdIn(list);
            }
        }
        return  tPropertyHouseholdBillCriteria;
    }

    */
/**
     * 获取数据总量
     * @param tPropertyHouseholdBill
     * @return
     *//*

    private Integer getCount(List<Integer> list,TPropertyHouseholdBill tPropertyHouseholdBill){
        Integer total =(int)tPropertyHouseholdBillMapper.countByExample(setCriteria(list,tPropertyHouseholdBill));
        return total;
    }

    @Override
    public PageBean<TPropertyHouseholdBill> getPropertyHouseholdBillbyPage(List<Integer> list,TPropertyHouseholdBill tPropertyHouseholdBill, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TPropertyHouseholdBill> tPropertyHouseholdBillLists=getPropertyHouseholdBill(list,tPropertyHouseholdBill);
        Integer countNums =getCount(list,tPropertyHouseholdBill);
        PageBean<TPropertyHouseholdBill> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tPropertyHouseholdBillLists);
        return pageData;
    }

    @Override
    public List<TPropertyHouseholdBill> getPropertyHouseholdBill(List<Integer> list,TPropertyHouseholdBill tPropertyHouseholdBill) {
        List<TPropertyHouseholdBill> tPropertyHouseholdBillLists = tPropertyHouseholdBillMapper.selectByExample(setCriteria(list,tPropertyHouseholdBill));
        return tPropertyHouseholdBillLists;
    }

    @Override
    public String updatePropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill) {
        String msg="";
        if (tPropertyHouseholdBill.getId()!=null){//编辑
            tPropertyHouseholdBill.setCreateTime(DateUtil.getCurDateTime());
            tPropertyHouseholdBill.setPayTime(DateUtil.getCurDateTime());
            tPropertyHouseholdBillMapper.updateByPrimaryKeySelective(tPropertyHouseholdBill);
            msg = "编辑成功";
        }else{//新增
            tPropertyHouseholdBill.setCreateTime(DateUtil.getCurDateTime());
            tPropertyHouseholdBillMapper.insertSelective(tPropertyHouseholdBill);
            msg = "成功添加";
        }
        return msg;
    }

    @Override
    public String deletePropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill) {
        tPropertyHouseholdBill = tPropertyHouseholdBillMapper.selectByPrimaryKey(tPropertyHouseholdBill.getId());
        tPropertyHouseholdBillMapper.deleteByPrimaryKey(tPropertyHouseholdBill.getId());
        return "删除成功";
    }
*/


}
