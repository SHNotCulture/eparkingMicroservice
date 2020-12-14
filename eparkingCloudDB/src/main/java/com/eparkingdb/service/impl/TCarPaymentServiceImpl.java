package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayment;
import com.common.entity.eparkingCloud.TCarPaymentCriteria;
import com.eparkingdb.dao.TCarPaymentMapper;
import com.eparkingdb.service.TCarPaymentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TCarPaymentService接口实现类
* @author 谢轩然
* @date 2020/05/14 16:54
*/
@Service
public class TCarPaymentServiceImpl  implements TCarPaymentService {

    private  static final Logger logger= LoggerFactory.getLogger( TCarPaymentServiceImpl.class);
    @Autowired
    private TCarPaymentMapper tCarPaymentMapper;

    /**
    * 设置查询条件
    * @param tCarPayment
    * @return
    */
    private  TCarPaymentCriteria setCriteria(TCarPayment tCarPayment,String beginData,String endData){
        TCarPaymentCriteria tCarPaymentCriteria= new TCarPaymentCriteria();
        if(tCarPayment!=null){
        TCarPaymentCriteria.Criteria criteria=tCarPaymentCriteria.createCriteria();
        if(tCarPayment.getId()!=null){
        criteria.andIdEqualTo(tCarPayment.getId());
        }
            if (tCarPayment.getParkId()!=null){
                criteria.andParkIdEqualTo(tCarPayment.getParkId());
            }if (tCarPayment.getPayRule()!=null){
                criteria.andPayRuleEqualTo(tCarPayment.getPayRule());
            }if (tCarPayment.getCarplate()!=null){
                criteria.andCarplateLike("%"+tCarPayment.getCarplate()+"%");
            }
            if (beginData.length()>0){
                criteria.andPayTimeGreaterThanOrEqualTo(beginData);
            }
            if (endData.length()>0){
                criteria.andPayTimeLessThanOrEqualTo(endData);
            }
        }
        return  tCarPaymentCriteria;
    }

    /**
    * 获取数据总量
    * @param tCarPayment
    * @return
    */
    private Integer getCount(TCarPayment tCarPayment,String beginData,String endData){
    Integer total =(int)tCarPaymentMapper.countByExample(setCriteria(tCarPayment,beginData,endData));
    return total;
    }

    /**
    *查询tCarPayment(分页)
    * @param tCarPayment
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCarPayment> getTCarPaymentbyPage(TCarPayment tCarPayment,String beginData,String endData, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCarPayment> tCarPayments=getTCarPayment(tCarPayment,beginData,endData);
            Integer countNums =getCount(tCarPayment,beginData,endData);
            PageBean<TCarPayment> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCarPayments);
            return pageData;
        }

    /**
    * 查询tCarPayment
    * @param tCarPayment
    * @return
    */
    public List<TCarPayment> getTCarPayment(TCarPayment tCarPayment,String beginData,String endData){
    List<TCarPayment>  tCarPayments=tCarPaymentMapper.selectByExample(setCriteria(tCarPayment,beginData,endData));
    return tCarPayments;
    }

    /**
    * 更新tCarPayment
    * @param tCarPayment
    * @return
    */
    public String UpdateTCarPayment(TCarPayment tCarPayment)
    {
            String msg="";
            try{
            if(tCarPayment.getId()!=null){
            tCarPaymentMapper.updateByPrimaryKeySelective(tCarPayment);
                msg="更新成功";
            }
            else
            {
            tCarPaymentMapper.insertSelective(tCarPayment);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tCarPayment
    * @param tCarPayment
    * @return
    */
    public String DeleteTCarPayment(TCarPayment tCarPayment){
            String msg="";
            if(tCarPayment.getId()!=null){
            tCarPaymentMapper.deleteByPrimaryKey(tCarPayment.getId());
            msg="删除TCarPayment成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tCarPayment
    * @param id
    * @return
    */
    public TCarPayment getTCarPaymentByID(Integer id) {
        return  tCarPaymentMapper.selectByPrimaryKey(id);
    }
}
