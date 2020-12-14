package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkReportYear;
import com.common.entity.eparkingCloud.TParkReportYearCriteria;
import com.eparkingdb.dao.TParkReportYearMapper;
import com.eparkingdb.service.TParkReportYearService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TParkReportYearService接口实现类
* @author 谢轩然
* @date 2020/08/04 10:38
*/
@Service
public class TParkReportYearServiceImpl  implements TParkReportYearService {

    private  static final Logger logger= LoggerFactory.getLogger( TParkReportYearServiceImpl.class);
    @Autowired
    private TParkReportYearMapper tParkReportYearMapper;

    /**
    * 设置查询条件
    * @param tParkReportYear
    * @return
    */
    private  TParkReportYearCriteria setCriteria(TParkReportYear tParkReportYear,String beginTime, String endTime){
        TParkReportYearCriteria tParkReportYearCriteria= new TParkReportYearCriteria();
        if(tParkReportYear!=null){
        TParkReportYearCriteria.Criteria criteria=tParkReportYearCriteria.createCriteria();
        if(tParkReportYear.getId()!=null){
        criteria.andIdEqualTo(tParkReportYear.getId());
        }
            if(tParkReportYear.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReportYear.getParkId());
            }
            if(beginTime!=null&&endTime!=null &&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }
        }
        return  tParkReportYearCriteria;
    }

    /**
    * 获取数据总量
    * @param tParkReportYear
    * @return
    */
    private Integer getCount(TParkReportYear tParkReportYear,String beginTime, String endTime){
    Integer total =(int)tParkReportYearMapper.countByExample(setCriteria(tParkReportYear,beginTime,endTime));
    return total;
    }

    /**
    *查询tParkReportYear(分页)
    * @param tParkReportYear
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TParkReportYear> getTParkReportYearbyPage(TParkReportYear tParkReportYear,String beginTime, String endTime, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"create_date desc");
        List<TParkReportYear> tParkReportYears=getTParkReportYear(tParkReportYear,beginTime,endTime);
            Integer countNums =getCount(tParkReportYear,beginTime,endTime);
            PageBean<TParkReportYear> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tParkReportYears);
            return pageData;
        }

    /**
    * 查询tParkReportYear
    * @param tParkReportYear
    * @return
    */
    public List<TParkReportYear> getTParkReportYear(TParkReportYear tParkReportYear,String beginTime, String endTime){
    List<TParkReportYear>  tParkReportYears=tParkReportYearMapper.selectByExample(setCriteria(tParkReportYear,beginTime,endTime));
    return tParkReportYears;
    }

    /**
    * 更新tParkReportYear
    * @param tParkReportYear
    * @return
    */
    public String UpdateTParkReportYear(TParkReportYear tParkReportYear)
    {
            String msg="";
            try{
            if(tParkReportYear.getId()!=null){
            tParkReportYearMapper.updateByPrimaryKeySelective(tParkReportYear);
                msg="更新成功";
            }
            else
            {
            tParkReportYearMapper.insertSelective(tParkReportYear);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tParkReportYear
    * @param tParkReportYear
    * @return
    */
    public String DeleteTParkReportYear(TParkReportYear tParkReportYear){
            String msg="";
            if(tParkReportYear.getId()!=null){
            tParkReportYearMapper.deleteByPrimaryKey(tParkReportYear.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tParkReportYear
    * @param id
    * @return
    */
    public TParkReportYear getTParkReportYearByID(Integer id) {
        return  tParkReportYearMapper.selectByPrimaryKey(id);
    }
}
