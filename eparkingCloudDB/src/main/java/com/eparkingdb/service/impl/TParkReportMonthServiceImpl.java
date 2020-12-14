package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportMonth;
import com.common.entity.eparkingCloud.TParkReportMonthCriteria;
import com.eparkingdb.dao.TParkReportMonthMapper;
import com.eparkingdb.service.TParkReportMonthService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TParkReportMonthService接口实现类
* @author 谢轩然
* @date 2020/08/03 16:21
*/
@Service
public class TParkReportMonthServiceImpl  implements TParkReportMonthService {

    private  static final Logger logger= LoggerFactory.getLogger( TParkReportMonthServiceImpl.class);
    @Autowired
    private TParkReportMonthMapper tParkReportMonthMapper;

    /**
    * 设置查询条件
    * @param tParkReportMonth
    * @return
    */
    private  TParkReportMonthCriteria setCriteria(TParkReportMonth tParkReportMonth,String beginTime, String endTime){
        TParkReportMonthCriteria tParkReportMonthCriteria= new TParkReportMonthCriteria();
        if(tParkReportMonth!=null){
        TParkReportMonthCriteria.Criteria criteria=tParkReportMonthCriteria.createCriteria();
        if(tParkReportMonth.getId()!=null){
        criteria.andIdEqualTo(tParkReportMonth.getId());
        }
            if(tParkReportMonth.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReportMonth.getParkId());
            }
            if(beginTime!=null&&endTime!=null&&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }
        }
        return  tParkReportMonthCriteria;
    }

    /**
    * 获取数据总量
    * @param tParkReportMonth
    * @return
    */
    private Integer getCount(TParkReportMonth tParkReportMonth,String beginTime, String endTime){
    Integer total =(int)tParkReportMonthMapper.countByExample(setCriteria(tParkReportMonth,beginTime,endTime));
    return total;
    }

    /**
    *查询tParkReportMonth(分页)
    * @param tParkReportMonth
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TParkReportMonth> getTParkReportMonthbyPage(TParkReportMonth tParkReportMonth,String beginTime, String endTime, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"create_date desc");
        List<TParkReportMonth> tParkReportMonths=getTParkReportMonth(tParkReportMonth,beginTime,endTime);
            Integer countNums =getCount(tParkReportMonth,beginTime,endTime);
            PageBean<TParkReportMonth> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tParkReportMonths);
            return pageData;
        }

    /**
    * 查询tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    public List<TParkReportMonth> getTParkReportMonth(TParkReportMonth tParkReportMonth,String beginTime, String endTime){
    List<TParkReportMonth>  tParkReportMonths=tParkReportMonthMapper.selectByExample(setCriteria(tParkReportMonth,beginTime,endTime));
    return tParkReportMonths;
    }

    /**
    * 更新tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    public String UpdateTParkReportMonth(TParkReportMonth tParkReportMonth)
    {
            String msg="";
            try{
            if(tParkReportMonth.getId()!=null){
            tParkReportMonthMapper.updateByPrimaryKeySelective(tParkReportMonth);
                msg="更新成功";
            }
            else
            {
            tParkReportMonthMapper.insertSelective(tParkReportMonth);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tParkReportMonth
    * @param tParkReportMonth
    * @return
    */
    public String DeleteTParkReportMonth(TParkReportMonth tParkReportMonth){
            String msg="";
            if(tParkReportMonth.getId()!=null){
            tParkReportMonthMapper.deleteByPrimaryKey(tParkReportMonth.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tParkReportMonth
    * @param id
    * @return
    */
    public TParkReportMonth getTParkReportMonthByID(Integer id) {
        return  tParkReportMonthMapper.selectByPrimaryKey(id);
    }
}
