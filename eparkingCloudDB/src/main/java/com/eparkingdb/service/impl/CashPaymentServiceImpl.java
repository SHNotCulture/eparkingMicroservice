package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkDuty;
import com.common.entity.eparkingCloud.TParkDutyCriteria;
import com.eparkingdb.dao.TParkDutyMapper;
import com.eparkingdb.service.CashPaymentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashPaymentServiceImpl implements CashPaymentService {
    private  static final Logger logger= LoggerFactory.getLogger(CashPaymentServiceImpl.class);

    @Autowired
    private TParkDutyMapper tParkDutyMapper;


    /**
     *设置查询条件
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
    private TParkDutyCriteria setCriteria(TParkDuty tParkDuty, String beginTime, String endTime){
        TParkDutyCriteria tParkDutyCriteria=new TParkDutyCriteria();
        if(tParkDuty != null ){
            TParkDutyCriteria.Criteria criteria=tParkDutyCriteria.createCriteria();
            if(tParkDuty.getParkId()!=null){
                criteria.andParkIdEqualTo(tParkDuty.getParkId());
            }
            if(tParkDuty.getDutyPerson() !=null && !tParkDuty.getDutyPerson().equals("")) {
                criteria.andDutyPersonEqualTo(tParkDuty.getDutyPerson());
            }
            if(beginTime !=null && !beginTime.equals("")){
                criteria.aneparkingdbeginTimeGreaterThanOrEqualTo(beginTime);
            }
            if(endTime != null && !endTime.equals("")){
                criteria.aneparkingdbeginTimeLessThanOrEqualTo(endTime);
            }

        }
        return  tParkDutyCriteria;
    }

    /**
     *获取数据总量
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
    private Integer getCount(TParkDuty tParkDuty,String beginTime, String endTime){
        Integer total =(int)tParkDutyMapper.countByExample(setCriteria(tParkDuty,beginTime,endTime));
        return total;
    }
    /**
     * 查询现金支付对账（分页）
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkDuty> getTParkDutybyPage(TParkDuty tParkDuty, String beginTime, String endTime, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TParkDuty> tParkDuties=getTParkDuty(tParkDuty,beginTime,endTime);
        Integer countNums =getCount(tParkDuty,beginTime,endTime);
        PageBean<TParkDuty> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkDuties);
        return pageData;
    }

    /**
     * 查询现金支付对账
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public List<TParkDuty> getTParkDuty(TParkDuty tParkDuty,String beginTime, String endTime) {
        List<TParkDuty> tParkDutyList=tParkDutyMapper.selectByExample(setCriteria(tParkDuty,beginTime,endTime));
        return tParkDutyList;
    }

    @Override
    public String UpdateTParkDuty(TParkDuty tParkDuty) {
        String msg="";
        try{
            if(tParkDuty.getId()!=null){
                tParkDutyMapper.updateByPrimaryKeySelective(tParkDuty);
                msg="更新TParkDuty成功";
            }
            else
            {
                tParkDutyMapper.insertSelective(tParkDuty);
                msg="新建TParkDuty成功";
            }
        }
        catch (Exception e)
        {

        }
        return msg;
    }

    @Override
    public String DeleteTParkDuty(TParkDuty tParkDuty) {
        String msg="";
        if(tParkDuty.getId()!=null){
            tParkDutyMapper.deleteByPrimaryKey(tParkDuty.getId());
            msg="删除TParkDuty成功";
        }
        return msg;
    }


}
