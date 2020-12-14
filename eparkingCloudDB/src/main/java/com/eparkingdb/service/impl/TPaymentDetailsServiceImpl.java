package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TPaymentDetails;
import com.common.entity.eparkingCloud.TPaymentDetailsCriteria;
import com.eparkingdb.dao.TPaymentDetailsMapper;
import com.eparkingdb.service.TPaymentDetailsService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TPaymentDetailsService接口实现类
* @author 谢轩然
* @date 2020/10/13 17:31
*/
@Service
public class TPaymentDetailsServiceImpl  implements TPaymentDetailsService {

    private  static final Logger logger= LoggerFactory.getLogger( TPaymentDetailsServiceImpl.class);
    @Autowired
    private TPaymentDetailsMapper tPaymentDetailsMapper;

    /**
    * 设置查询条件
    * @param tPaymentDetails
    * @return
    */
    private  TPaymentDetailsCriteria setCriteria(TPaymentDetails tPaymentDetails){
        TPaymentDetailsCriteria tPaymentDetailsCriteria= new TPaymentDetailsCriteria();
        if(tPaymentDetails!=null){
        TPaymentDetailsCriteria.Criteria criteria=tPaymentDetailsCriteria.createCriteria();
        if(tPaymentDetails.getId()!=null){
        criteria.andIdEqualTo(tPaymentDetails.getId());
        }
            if(tPaymentDetails.getCarPlate()!=null && tPaymentDetails.getCarPlate()!=""){
                criteria.andCarPlateEqualTo(tPaymentDetails.getCarPlate());
            }
            if(tPaymentDetails.getParkId()!=null){
                criteria.andParkIdEqualTo(tPaymentDetails.getParkId());
            }
            if(tPaymentDetails.getOrderId()!=null && tPaymentDetails.getOrderId()!=""){
                criteria.andOrderIdEqualTo(tPaymentDetails.getOrderId());
            }
            if(tPaymentDetails.getPaymentMethod()!=null){
                criteria.andPaymentMethodEqualTo(tPaymentDetails.getPaymentMethod());
            }
            if(tPaymentDetails.getAcquiringChannel()!=null){
                criteria.andAcquiringChannelEqualTo(tPaymentDetails.getAcquiringChannel());
            }
            if(tPaymentDetails.getPaymentChannel()!=null){
                criteria.andPaymentChannelEqualTo(tPaymentDetails.getPaymentChannel());
            }
            if(tPaymentDetails.getPaymentOrderId()!=null && tPaymentDetails.getPaymentOrderId()!=""){
                criteria.andPaymentOrderIdEqualTo(tPaymentDetails.getPaymentOrderId());
            }
            if(tPaymentDetails.getInTime()!=null && tPaymentDetails.getInTime()!=""){
                criteria.andInTimeEqualTo(tPaymentDetails.getInTime());
            }
            if(tPaymentDetails.getOutTime()!=null && tPaymentDetails.getOutTime()!=""){
                criteria.andOutTimeEqualTo(tPaymentDetails.getOutTime());
            }
            if(tPaymentDetails.getAmount()!=null){
                criteria.andAmountEqualTo(tPaymentDetails.getAmount());
            }
        }
        return  tPaymentDetailsCriteria;
    }

    /**
    * 获取数据总量
    * @param tPaymentDetails
    * @return
    */
    private Integer getCount(TPaymentDetails tPaymentDetails){
    Integer total =(int)tPaymentDetailsMapper.countByExample(setCriteria(tPaymentDetails));
    return total;
    }

    /**
    *查询tPaymentDetails(分页)
    * @param tPaymentDetails
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TPaymentDetails> getTPaymentDetailsbyPage(TPaymentDetails tPaymentDetails, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TPaymentDetails> tPaymentDetailss=getTPaymentDetails(tPaymentDetails);
            Integer countNums =getCount(tPaymentDetails);
            PageBean<TPaymentDetails> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tPaymentDetailss);
            return pageData;
        }

    /**
    * 查询tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public List<TPaymentDetails> getTPaymentDetails(TPaymentDetails tPaymentDetails){
    List<TPaymentDetails>  tPaymentDetailss=tPaymentDetailsMapper.selectByExample(setCriteria(tPaymentDetails));
    return tPaymentDetailss;
    }

    /**
    * 更新tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public String UpdateTPaymentDetails(TPaymentDetails tPaymentDetails)
    {
            String msg="";
            try{
            if(tPaymentDetails.getId()!=null){
            tPaymentDetailsMapper.updateByPrimaryKeySelective(tPaymentDetails);
                msg="更新成功";
            }
            else
            {
            tPaymentDetailsMapper.insertSelective(tPaymentDetails);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tPaymentDetails
    * @param tPaymentDetails
    * @return
    */
    public String DeleteTPaymentDetails(TPaymentDetails tPaymentDetails){
            String msg="";
            if(tPaymentDetails.getId()!=null){
            tPaymentDetailsMapper.deleteByPrimaryKey(tPaymentDetails.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tPaymentDetails
    * @param id
    * @return
    */
    public TPaymentDetails getTPaymentDetailsByID(Integer id) {
        return  tPaymentDetailsMapper.selectByPrimaryKey(id);
    }
}
