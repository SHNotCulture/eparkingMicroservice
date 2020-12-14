package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.ExcelTBusinePay;
import com.common.entity.eparkingCloud.TBusinePay;
import com.common.entity.eparkingCloud.TBusinePayCriteria;
import com.eparkingdb.dao.TBusinePayMapper;
import com.eparkingdb.service.TBusinePayService;
import com.common.util.BeanCopyUtil;
import com.common.util.ExportExcelUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: TBusinePayService接口实现类
* @author 谢轩然
* @date 2020/04/09 10:50
*/
@Service
public class TBusinePayServiceImpl  implements TBusinePayService {

    private  static final Logger logger= LoggerFactory.getLogger( TBusinePayServiceImpl.class);
    @Autowired
    private TBusinePayMapper tBusinePayMapper;

    /**
    * 设置查询条件
    * @param tBusinePay
    * @return
    */
    private  TBusinePayCriteria setCriteria(TBusinePay tBusinePay,Integer busineId, String payTimeBegin, String payTimeEnd){
        TBusinePayCriteria tBusinePayCriteria= new TBusinePayCriteria();
        if(tBusinePay!=null){
        TBusinePayCriteria.Criteria criteria=tBusinePayCriteria.createCriteria();
        if(busineId!=null){
            criteria.aneparkingdbusineIdEqualTo(busineId);
        }

        if(tBusinePay.getId()!=null){
        criteria.andIdEqualTo(tBusinePay.getId());
        }
            if(tBusinePay.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tBusinePay.getParkId());
            }
            if(tBusinePay.getBusineName()!=null)
            {
                criteria.aneparkingdbusineNameLike("%"+tBusinePay.getBusineName()+"%");
            }
            if(tBusinePay.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tBusinePay.getCompanyId());
            }
            if(tBusinePay.getOrderNumber()!=null && tBusinePay.getOrderNumber()!=""){
                criteria.andOrderNumberEqualTo(tBusinePay.getOrderNumber());
            }
            if (payTimeBegin!=null && payTimeBegin!=""  && payTimeEnd!="" && payTimeEnd!=null ){
                criteria.andPayTimeBetween(payTimeBegin,payTimeEnd);
            }
            tBusinePayCriteria.setOrderByClause("pay_time DESC");
        }
        return  tBusinePayCriteria;
    }

    /**
    * 获取数据总量
    * @param tBusinePay
    * @return
    */
    private Integer getCount(TBusinePay tBusinePay,Integer busineId, String payTimeBegin, String payTimeEnd){
    Integer total =(int)tBusinePayMapper.countByExample(setCriteria(tBusinePay,busineId,payTimeBegin,payTimeEnd));
    return total;
    }

    /**
    *查询tBusinePay(分页)
    * @param tBusinePay
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TBusinePay> getTBusinePaybyPage(TBusinePay tBusinePay ,Integer busineId, String payTimeBegin, String payTimeEnd, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"pay_time desc");
        List<TBusinePay> tBusinePays=getTBusinePay(tBusinePay,busineId,payTimeBegin,payTimeEnd);
            Integer countNums =getCount(tBusinePay,busineId,payTimeBegin,payTimeEnd);
            PageBean<TBusinePay> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tBusinePays);
            return pageData;
        }



    /**
    * 查询tBusinePay
    * @param tBusinePay
    * @return
    */
    public List<TBusinePay> getTBusinePay(TBusinePay tBusinePay,Integer busineId, String payTimeBegin, String payTimeEnd){
    List<TBusinePay>  tBusinePays=tBusinePayMapper.selectByExample(setCriteria(tBusinePay,busineId,payTimeBegin,payTimeEnd));
    return tBusinePays;
    }

    /**
    * 更新tBusinePay
    * @param tBusinePay
    * @return
    */
    public String UpdateTBusinePay(TBusinePay tBusinePay)
    {
            String msg="";
            try{
            if(tBusinePay.getId()!=null){
            tBusinePayMapper.updateByPrimaryKeySelective(tBusinePay);
                msg="更新TBusinePay成功";
            }
            else
            {
            tBusinePayMapper.insertSelective(tBusinePay);
                msg="新建TBusinePay成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tBusinePay
    * @param tBusinePay
    * @return
    */
    public String DeleteTBusinePay(TBusinePay tBusinePay){
            String msg="";
            if(tBusinePay.getId()!=null){
            tBusinePayMapper.deleteByPrimaryKey(tBusinePay.getId());
            msg="删除TBusinePay成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tBusinePay
    * @param id
    * @return
    */
    public TBusinePay getTBusinePayByID(Integer id) {
        return  tBusinePayMapper.selectByPrimaryKey(id);
    }

    @Override
    public void exportList(TBusinePay tBusinePay, Integer busineId,String payTimeBegin, String payTimeEnd, String title, HttpServletResponse response) {
        List<TBusinePay> tBusinePayList=getTBusinePay(tBusinePay,busineId,payTimeBegin,payTimeEnd);
        List<ExcelTBusinePay> excelTBusinePays=new ArrayList<>();
//        logger.info("tBusinePayList:"+tBusinePayList);
        for (TBusinePay tBusinePayNew : tBusinePayList){
            ExcelTBusinePay excelTBusinePay = new ExcelTBusinePay();
            try {
                excelTBusinePay = (ExcelTBusinePay) BeanCopyUtil.CopyBeanToBean(tBusinePayNew,excelTBusinePay);
            } catch (Exception e) {
                e.printStackTrace();
            }
            excelTBusinePays.add(excelTBusinePay);
        }
        try {
            ExportExcelUtil.exportExcel(response,title,title,title,excelTBusinePays);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
