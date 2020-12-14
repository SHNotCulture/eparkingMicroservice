package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.entity.eparkingCloud.TParkInOutCriteria;
import com.eparkingdb.dao.TParkInOutMapper;
import com.eparkingdb.service.TParkInOutService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TParkInOutServiceImpl implements TParkInOutService {
    private  static final Logger logger= LoggerFactory.getLogger(TParkInOutServiceImpl.class);
    @Autowired
    private TParkInOutMapper tParkInOutMapper;



    /**
     * 设置查询条件
     * @param tParkInOut
     * @return
     */
    private  TParkInOutCriteria setCriteria(TParkInOut tParkInOut){
        TParkInOutCriteria tParkInOutCriteria= new TParkInOutCriteria();
        if(tParkInOut!=null){
            TParkInOutCriteria.Criteria criteria=tParkInOutCriteria.createCriteria();
            if(tParkInOut.getId()!=null){
                criteria.andIdEqualTo(tParkInOut.getId());
            }
            if (tParkInOut.getUpdatedincarplate() != null) {
                criteria.andUpdatedincarplateIsUpdated();
                criteria.andUpdatedoutcarplateIsUpdated();
            }
            if (tParkInOut.getOutPortId() != null) {
                criteria.andOutPortIdEqualTo(tParkInOut.getOutPortId());
            }
            if (tParkInOut.getInCarPlate() != null && tParkInOut.getInCarPlate().length() > 0) {
                criteria.andInCarPlateLike("%" + tParkInOut.getInCarPlate() + "%");
            }
            if(tParkInOut.getEpaytype()!=null){
                criteria.andEpaytypeEqualTo(tParkInOut.getEpaytype());
            }
            if (tParkInOut.getOutType() != null) {
                criteria.andOutTypeEqualTo(tParkInOut.getOutType());
            }
            if (tParkInOut.getInCardCode() != null) {
                if (tParkInOut.getInCardCode().equals("actual_pay")) {
                    criteria.andActualPayGreaterThan(0.0);
                }
                if (tParkInOut.getInCardCode().equals("qpass_pay")) {
                    criteria.andQpassPayGreaterThan(0.0);
                }
                if (tParkInOut.getInCardCode().equals("coupon")) {
                    criteria.andCouponGreaterThan(0.0);
                }
                if (tParkInOut.getInCardCode().equals("before_pay")) {
                    criteria.aneparkingdbeforePayGreaterThan(0.0);
                }
                if (tParkInOut.getInCardCode().equals("localcoupon")) {
                    criteria.andLocalcouponGreaterThan(0.0);
                }
            }
            if (tParkInOut.getInPortName() != null && tParkInOut.getInPortName() != "") {
                criteria.andInPortNameEqualTo(tParkInOut.getInPortName());
            }
            if (tParkInOut.getOutPortName() != null && tParkInOut.getOutPortName() != "") {
                criteria.andOutPortNameEqualTo(tParkInOut.getOutPortName());
            }
            if (tParkInOut.getDutyPerson() != null && tParkInOut.getDutyPerson().length() > 0) {
                criteria.andDutyPersonLike("%" + tParkInOut.getDutyPerson() + "%");
            }
            if (tParkInOut.getInTime() != null && tParkInOut.getInTime().length() > 0) {
                criteria.andInTimeGreaterThanOrEqualTo(tParkInOut.getInTime());
            }
            if (tParkInOut.getOutTime() != null && tParkInOut.getOutTime().length() > 0) {
                criteria.andInTimeLessThanOrEqualTo(tParkInOut.getOutTime());
            }
            if (tParkInOut.getChargeType() != null && !tParkInOut.getChargeType().equals("")) {
                criteria.andChargeTypeEqualTo(tParkInOut.getChargeType());
            }
            if (tParkInOut.getCloudOrderId() != null && !tParkInOut.getCloudOrderId().equals("")) {
                criteria.andCloudOrderIdLike("%" + tParkInOut.getCloudOrderId() + "%");
            }
            if (tParkInOut.getSettleDate() != null) {
                String temp[] = tParkInOut.getSettleDate().split("@");
                if (temp[0].length() > 2) {
                    criteria.andOccurTimeGreaterThanOrEqualTo(temp[0]);
                }
                if (temp[1].length() > 2) {
                    criteria.andOccurTimeLessThanOrEqualTo(temp[1]);
                }
            }
            if(tParkInOut.getCarNatureDesc()!=null && !tParkInOut.getCarNatureDesc().equals("")){
                criteria.andCarNatureDescLike("%"+tParkInOut.getCarNatureDesc()+"%");
            }
            if(tParkInOut.getChargeTypeDesc()!=null && !tParkInOut.getChargeTypeDesc().equals("")){
                criteria.andChargeTypeDescLike("%"+tParkInOut.getChargeTypeDesc()+"%");
            }
            if(tParkInOut.getInCarType()!=null){
                criteria.andInCarTypeEqualTo(tParkInOut.getInCarType());
            }
            if(tParkInOut.getInType()!=null){
                criteria.andInTypeEqualTo(tParkInOut.getInType());
            }
            if(tParkInOut.getCashPayCode()!=null && tParkInOut.getCashPayCode()!=""){
                criteria.andCashPayCodeEqualTo(tParkInOut.getCashPayCode());
            }
            if(tParkInOut.getPrePayCode()!=null && tParkInOut.getPrePayCode()!=""){
                criteria.andPrePayCodeEqualTo(tParkInOut.getPrePayCode());
            }
            if(tParkInOut.getBehalfPayCode()!=null && tParkInOut.getBehalfPayCode()!=""){
                criteria.aneparkingdbehalfPayCodeEqualTo(tParkInOut.getBehalfPayCode());
            }
            if(tParkInOut.getqPassPayCode()!=null && tParkInOut.getqPassPayCode()!=""){
                criteria.andQPassPayCodeEqualTo(tParkInOut.getqPassPayCode());
            }
            if(tParkInOut.getFreePayCode()!=null && tParkInOut.getFreePayCode()!=""){
                criteria.andFreePayCodeEqualTo(tParkInOut.getFreePayCode());
            }
            if(tParkInOut.getChargeTypeDesc()!=null && tParkInOut.getChargeTypeDesc()!=""){
                criteria.andChargeTypeDescEqualTo(tParkInOut.getChargeTypeDesc());
            }
            if(tParkInOut.getRemarks()!=null && tParkInOut.getRemarks()!=""){
                criteria.andRemarksEqualTo(tParkInOut.getRemarks());
            }
            if(tParkInOut.getCarNatureDesc()!=null && !tParkInOut.getCarNatureDesc().equals("")){
                criteria.andCarNatureDescLike("%"+tParkInOut.getCarNatureDesc()+"%");
            }
            if(tParkInOut.getCarStatus()!=null){
                criteria.andCarStatusEqualTo(tParkInOut.getCarStatus());
            }
            if(tParkInOut.getInCarType()!=null){
                criteria.andInCarTypeEqualTo(tParkInOut.getInCarType());
            }
            if(tParkInOut.getInType()!=null){
                criteria.andInTypeEqualTo(tParkInOut.getInType());
            }
            if(tParkInOut.getOrderId()!=null){
                criteria.andOrderIdEqualTo(tParkInOut.getOrderId());
            }
        }
        return  tParkInOutCriteria;
    }

    /**
     * 获取数据总量
     * @param tParkInOut
     * @return
     */
    private Integer getCount(TParkInOut tParkInOut){
        Integer total =(int)tParkInOutMapper.countByExample(setCriteria(tParkInOut),tParkInOut.getParkId());
        return total;
    }

    /**
     * 查询tParkInOut
     * @param tParkInOut
     * @return
     */
    public List<TParkInOut> getTParkInOutUnlimit(TParkInOut tParkInOut){
        List<TParkInOut>  tParkInOuts=tParkInOutMapper.selectByExample(setCriteria(tParkInOut),tParkInOut.getParkId());
        return tParkInOuts;
    }

    @Override
    public PageBean<TParkInOut> getTParkinoutUnlimitByPage(TParkInOut tParkInOut, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "occur_time desc");
        List<TParkInOut> tParkInOuts = getTParkInOutUnlimit(tParkInOut);
        Integer countNums = getCount(tParkInOut);
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOuts);
        return pageData;
    }

    /**
     * 更新tParkInOut
     * @param tParkInOut
     * @return
     */
    public String UpdateTParkInOut(TParkInOut tParkInOut)
    {
        String msg="";
        try{
            if(tParkInOut.getId()!=null){
                tParkInOutMapper.updateByPrimaryKeySelective(tParkInOut,tParkInOut.getParkId());
                msg="更新成功";
            }
            else
            {
                tParkInOutMapper.insertSelective(tParkInOut,tParkInOut.getParkId());
                msg="新建成功";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return msg;
    }

    /**
     * 删除tParkInOut
     * @param tParkInOut
     * @return
     */
    public String DeleteTParkInOut(TParkInOut tParkInOut){
        String msg="";
        if(tParkInOut.getId()!=null){
            tParkInOutMapper.deleteByPrimaryKey(tParkInOut.getId());
            msg="删除成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tParkInOut
     * @param id
     * @return
     */
    public TParkInOut getTParkInOutByID(String id) {
        return  tParkInOutMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据orderID获取停车记录
     * @param orderId
     * @param parkId
     * @return
     */
    public TParkInOut getTParkInOutbyorderId(String orderId,Integer parkId){
        TParkInOut tParkInOut=new TParkInOut();
        TParkInOut tParkInOutSel=new TParkInOut();
        tParkInOutSel.setParkId(parkId);
        tParkInOutSel.setOrderId(orderId);
        List<TParkInOut>  tParkInOutList=getTParkInOutUnlimit(tParkInOutSel);
        if(tParkInOutList.size()==1){
            tParkInOut=tParkInOutList.get(0);
        }
        return tParkInOut;
    }

    @Override
    public List<TParkInOut> getTParkInOutForStatistics(TParkInOut tParkInOut, String outTimeBegin, String outTimeEnd) {
        TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        if(tParkInOut.getParkId()!=null)
        {
            criteria.andParkIdEqualTo(tParkInOut.getParkId());
        }
        if(tParkInOut.getEpaytype()!=null){
            criteria.andEpaytypeEqualTo(tParkInOut.getEpaytype());
        }
        criteria.andOutTimeGreaterThanOrEqualTo(outTimeBegin);
        criteria.andOutTimeLessThanOrEqualTo(outTimeEnd);
        return tParkInOutMapper.selectByExample(tParkInOutCriteria,tParkInOut.getParkId());
    }
}
