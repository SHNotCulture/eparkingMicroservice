package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCriteria;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.eparkingdb.dao.TCarownerPaymentMapper;
import com.eparkingdb.service.TCarownerPaymentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TCarownerPaymentService接口实现类
* @author 谢轩然
* @date 2020/09/11 16:29
*/
@Service
public class TCarownerPaymentServiceImpl  implements TCarownerPaymentService {

    private  static final Logger logger= LoggerFactory.getLogger( TCarownerPaymentServiceImpl.class);
    @Autowired
    private TCarownerPaymentMapper tCarownerPaymentMapper;

    /**
    * 设置查询条件
    * @param tCarownerPayment
    * @return
    */
    private  TCarownerPaymentCriteria setCriteria(TCarownerPayment tCarownerPayment,String beginDate,String endDate){
        TCarownerPaymentCriteria tCarownerPaymentCriteria= new TCarownerPaymentCriteria();
        if(tCarownerPayment!=null){
        TCarownerPaymentCriteria.Criteria criteria=tCarownerPaymentCriteria.createCriteria();
        if(tCarownerPayment.getId()!=null){
        criteria.andIdEqualTo(tCarownerPayment.getId());
        }
        if(tCarownerPayment.getCompanyId()!=null){
            criteria.andCompanyIdEqualTo(tCarownerPayment.getCompanyId());
        }
            if(tCarownerPayment.getParkId()!=null){
                criteria.andParkIdEqualTo(tCarownerPayment.getParkId());
            }
            if(tCarownerPayment.getParkCarId()!=null){
                criteria.andParkCarIdEqualTo(tCarownerPayment.getParkCarId());
            }
            if(tCarownerPayment.getCarplate()!=null && tCarownerPayment.getCarplate()!=""){
                criteria.andCarplateLike("%"+tCarownerPayment.getCarplate()+"%");
            }
            if(tCarownerPayment.getRechargeNo()!=null && tCarownerPayment.getRechargeNo()!=""){
                criteria.andRechargeNoEqualTo(tCarownerPayment.getRechargeNo());
            }
            if(tCarownerPayment.getNeedPay()!=null){
                criteria.andNeedPayEqualTo(tCarownerPayment.getNeedPay());
            }
            if(tCarownerPayment.getActualPay()!=null){
                criteria.andActualPayEqualTo(tCarownerPayment.getActualPay());
            }
            if(tCarownerPayment.getWalletBanance()!=null && tCarownerPayment.getWalletBanance()!=""){
                criteria.andWalletBananceEqualTo(tCarownerPayment.getWalletBanance());
            }
            if(tCarownerPayment.getOperType()!=null){
                criteria.andOperTypeEqualTo(tCarownerPayment.getOperType());
            }
            if (beginDate!=null&beginDate.length()>0){
                criteria.andPayTimeGreaterThanOrEqualTo(beginDate);
            }
            if (endDate!=null&endDate.length()>0){
                criteria.andPayTimeLessThanOrEqualTo(endDate);
            }
            if(tCarownerPayment.getPayType()!=null){
                criteria.andPayTypeEqualTo(tCarownerPayment.getPayType());
            }
            if(tCarownerPayment.getRemark()!=null && tCarownerPayment.getRemark()!=""){
                criteria.andRemarkEqualTo(tCarownerPayment.getRemark());
            }
            if(tCarownerPayment.getOperator()!=null && tCarownerPayment.getOperator()!=""){
                criteria.andOperatorEqualTo(tCarownerPayment.getOperator());
            }
        }
        return  tCarownerPaymentCriteria;
    }

    /**
    * 获取数据总量
    * @param tCarownerPayment
    * @return
    */
    private Integer getCount(TCarownerPayment tCarownerPayment,String beginDate,String endDate){
    Integer total =(int)tCarownerPaymentMapper.countByExample(setCriteria(tCarownerPayment,beginDate,endDate));
    return total;
    }

    /**
    *查询tCarownerPayment(分页)
    * @param tCarownerPayment
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCarownerPayment> getTCarownerPaymentbyPage(TCarownerPayment tCarownerPayment,String beginDate,String endDate, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCarownerPayment> tCarownerPayments=getTCarownerPayment(tCarownerPayment,beginDate,endDate);
            Integer countNums =getCount(tCarownerPayment,beginDate,endDate);
            PageBean<TCarownerPayment> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCarownerPayments);
            return pageData;
        }

    /**
    * 查询tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    public List<TCarownerPayment> getTCarownerPayment(TCarownerPayment tCarownerPayment,String beginDate,String endDate){
    List<TCarownerPayment>  tCarownerPayments=tCarownerPaymentMapper.selectByExample(setCriteria(tCarownerPayment,beginDate,endDate));
    return tCarownerPayments;
    }

    /**
    * 更新tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    public String UpdateTCarownerPayment(TCarownerPayment tCarownerPayment)
    {
            String msg="";
            try{
            if(tCarownerPayment.getId()!=null){
            tCarownerPaymentMapper.updateByPrimaryKeySelective(tCarownerPayment);
                msg="更新TCarownerPayment成功";
            }
            else
            {
            tCarownerPaymentMapper.insertSelective(tCarownerPayment);
                msg="新建TCarownerPayment成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tCarownerPayment
    * @param tCarownerPayment
    * @return
    */
    public String DeleteTCarownerPayment(TCarownerPayment tCarownerPayment){
            String msg="";
            if(tCarownerPayment.getId()!=null){
            tCarownerPaymentMapper.deleteByPrimaryKey(tCarownerPayment.getId());
            msg="删除TCarownerPayment成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tCarownerPayment
    * @param id
    * @return
    */
    public TCarownerPayment getTCarownerPaymentByID(Integer id) {
        return  tCarownerPaymentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<TCarownerPayment> getTCarownerPaymentNewbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"id desc");
        TCarownerPaymentCriteria tCarownerPaymentCriteria = new TCarownerPaymentCriteria();
        if(tCarownerPaymentCus!=null){
            TCarownerPaymentCriteria.Criteria criteria=tCarownerPaymentCriteria.createCriteria();
            if(tCarownerPaymentCus.getId()!=null){
                criteria.andIdEqualTo(tCarownerPaymentCus.getId());
            }
            if(tCarownerPaymentCus.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tCarownerPaymentCus.getCompanyId());
            }
            if(tCarownerPaymentCus.getParkId()!=null){
                criteria.andParkIdEqualTo(tCarownerPaymentCus.getParkId());
            }
            if(tCarownerPaymentCus.getParkCarId()!=null){
                criteria.andParkCarIdEqualTo(tCarownerPaymentCus.getParkCarId());
            }
            if(tCarownerPaymentCus.getCarplate()!=null && tCarownerPaymentCus.getCarplate()!=""){
                criteria.andCarplateLike("%"+tCarownerPaymentCus.getCarplate()+"%");
            }
            if(tCarownerPaymentCus.getRechargeNo()!=null && tCarownerPaymentCus.getRechargeNo()!=""){
                criteria.andRechargeNoEqualTo(tCarownerPaymentCus.getRechargeNo());
            }
            if(tCarownerPaymentCus.getNeedPayMin()!=null){
                criteria.andNeedPayGreaterThanOrEqualTo(tCarownerPaymentCus.getNeedPayMin());
            }
            if(tCarownerPaymentCus.getNeedPayMax()!=null){
                criteria.andNeedPayLessThanOrEqualTo(tCarownerPaymentCus.getNeedPayMax());
            }
            if(tCarownerPaymentCus.getActualPayMin()!=null){
                criteria.andActualPayGreaterThanOrEqualTo(tCarownerPaymentCus.getActualPayMin());
            }
            if(tCarownerPaymentCus.getActualPayMax()!=null){
                criteria.andActualPayLessThanOrEqualTo(tCarownerPaymentCus.getActualPayMax());
            }
            if(tCarownerPaymentCus.getWalletBalanceMin()!=null && !tCarownerPaymentCus.getWalletBalanceMin().equals("") && !tCarownerPaymentCus.getWalletBalanceMin().equals("null")){
                criteria.andWalletBananceGreaterThanOrEqualTo(tCarownerPaymentCus.getWalletBalanceMin());
            }
            if(tCarownerPaymentCus.getWalletBalanceMax()!=null && !tCarownerPaymentCus.getWalletBalanceMax().equals("") && !tCarownerPaymentCus.getWalletBalanceMax().equals("null")){
                criteria.andWalletBananceLessThanOrEqualTo(tCarownerPaymentCus.getWalletBalanceMax());
            }
            if(tCarownerPaymentCus.getOperType()!=null){
                criteria.andOperTypeEqualTo(tCarownerPaymentCus.getOperType());
            }
            if (tCarownerPaymentCus.getPayTimeMin()!=null && !tCarownerPaymentCus.getPayTimeMin().equals("") && !tCarownerPaymentCus.getPayTimeMin().equals("null")){
                criteria.andPayTimeGreaterThanOrEqualTo(tCarownerPaymentCus.getPayTimeMin());
            }
            if (tCarownerPaymentCus.getPayTimeMax()!=null && !tCarownerPaymentCus.getPayTimeMax().equals("") && !tCarownerPaymentCus.getPayTimeMax().equals("null")){
                criteria.andPayTimeLessThanOrEqualTo(tCarownerPaymentCus.getPayTimeMax());
            }
            if(tCarownerPaymentCus.getPayType()!=null){
                criteria.andPayTypeEqualTo(tCarownerPaymentCus.getPayType());
            }
            if(tCarownerPaymentCus.getRemark()!=null && !tCarownerPaymentCus.getRemark().equals("")){
                criteria.andRemarkEqualTo(tCarownerPaymentCus.getRemark());
            }
            if(tCarownerPaymentCus.getOperator()!=null && !tCarownerPaymentCus.getOperator().equals("")){
                criteria.andOperatorEqualTo(tCarownerPaymentCus.getOperator());
            }
        }
        List<TCarownerPayment> tCarownerPayments = tCarownerPaymentMapper.selectByExample(tCarownerPaymentCriteria);
        Integer countNums = (int) tCarownerPaymentMapper.countByExample(tCarownerPaymentCriteria);
        PageBean<TCarownerPayment> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCarownerPayments);
        return pageData;
    }
}
