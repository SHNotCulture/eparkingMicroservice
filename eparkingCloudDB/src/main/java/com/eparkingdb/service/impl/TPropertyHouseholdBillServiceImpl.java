package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPropertyHouseholdBill;
import com.common.entity.eparkingCloud.TPropertyHouseholdBillCriteria;
import com.eparkingdb.dao.TPropertyHouseholdBillMapper;
import com.eparkingdb.service.TPropertyHouseholdBillService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPropertyHouseholdBillService接口实现类
* @author 谢轩然
* @date 2020/04/29 15:37
*/
@Service
public class TPropertyHouseholdBillServiceImpl implements TPropertyHouseholdBillService {

    private  static final Logger logger= LoggerFactory.getLogger( TPropertyHouseholdBillServiceImpl.class);
    @Autowired
    private TPropertyHouseholdBillMapper tPropertyHouseholdBillMapper;

    /**
    * 设置查询条件
    * @param tPropertyHouseholdBill
    * @return
    */
    private TPropertyHouseholdBillCriteria setCriteria(TPropertyHouseholdBill tPropertyHouseholdBill, List<Integer> list){
        TPropertyHouseholdBillCriteria tPropertyHouseholdBillCriteria= new TPropertyHouseholdBillCriteria();
        if(tPropertyHouseholdBill!=null){
        TPropertyHouseholdBillCriteria.Criteria criteria=tPropertyHouseholdBillCriteria.createCriteria();
        if(tPropertyHouseholdBill.getId()!=null){
        criteria.andIdEqualTo(tPropertyHouseholdBill.getId());
        }
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

    /**
    * 获取数据总量
    * @param tPropertyHouseholdBill
    * @return
    */
    private Integer getCount(List<Integer> list,TPropertyHouseholdBill tPropertyHouseholdBill){
        Integer total =(int)tPropertyHouseholdBillMapper.countByExample(setCriteria(tPropertyHouseholdBill,list));
        return total;
    }

    /**
     *查询tPropertyHouseholdBill(分页)
     * @param tPropertyHouseholdBill
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TPropertyHouseholdBill> getTPropertyHouseholdBillbyPage(TPropertyHouseholdBill tPropertyHouseholdBill, List<Integer> list, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TPropertyHouseholdBill> tPropertyHouseholdBillLists=getTPropertyHouseholdBill(tPropertyHouseholdBill,list);
        Integer countNums =getCount(list,tPropertyHouseholdBill);
        PageBean<TPropertyHouseholdBill> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tPropertyHouseholdBillLists);
        return pageData;
    }

    /**
     * 查询tPropertyHouseholdBill
     * @param tPropertyHouseholdBill
     * @return
     */
    @Override
    public List<TPropertyHouseholdBill> getTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill, List<Integer> list) {
        List<TPropertyHouseholdBill> tPropertyHouseholdBillLists = tPropertyHouseholdBillMapper.selectByExample(setCriteria(tPropertyHouseholdBill,list));
        return tPropertyHouseholdBillLists;
    }

    /**
    * 更新tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    public String UpdateTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill)
    {
            String msg="";
            try{
            if(tPropertyHouseholdBill.getId()!=null){
            tPropertyHouseholdBillMapper.updateByPrimaryKeySelective(tPropertyHouseholdBill);
                msg="编辑成功";
            }
            else
            {
            tPropertyHouseholdBillMapper.insertSelective(tPropertyHouseholdBill);
                msg="成功添加";
            }
            }
            catch (Exception e)
            {
                e.toString();
            }
            return msg;
    }

    /**
    * 删除tPropertyHouseholdBill
    * @param tPropertyHouseholdBill
    * @return
    */
    public String DeleteTPropertyHouseholdBill(TPropertyHouseholdBill tPropertyHouseholdBill){
            String msg="";
            if(tPropertyHouseholdBill.getId()!=null){
            tPropertyHouseholdBillMapper.deleteByPrimaryKey(tPropertyHouseholdBill.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tPropertyHouseholdBill
    * @param id
    * @return
    */
    public TPropertyHouseholdBill getTPropertyHouseholdBillByID(Integer id) {
        return  tPropertyHouseholdBillMapper.selectByPrimaryKey(id);
    }
}
