package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkStatisticsDay;
import com.common.entity.eparkingCloud.TParkStatisticsDayCriteria;
import com.eparkingdb.dao.TParkStatisticsDayMapper;
import com.eparkingdb.service.TParkStatisticsDayService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TParkStatisticsDayService接口实现类
* @author 李书瀚
* @date 2020/06/09 16:05
*/
@Service
public class TParkStatisticsDayServiceImpl  implements TParkStatisticsDayService {

    private  static final Logger logger= LoggerFactory.getLogger( TParkStatisticsDayServiceImpl.class);
    @Autowired
    private TParkStatisticsDayMapper tParkStatisticsDayMapper;

    /**
    * 设置查询条件
    * @param tParkStatisticsDay
    * @return
    */
    private  TParkStatisticsDayCriteria setCriteria(TParkStatisticsDay tParkStatisticsDay){
        TParkStatisticsDayCriteria tParkStatisticsDayCriteria= new TParkStatisticsDayCriteria();
        if(tParkStatisticsDay!=null){
        TParkStatisticsDayCriteria.Criteria criteria=tParkStatisticsDayCriteria.createCriteria();
        if(tParkStatisticsDay.getId()!=null){
        criteria.andIdEqualTo(tParkStatisticsDay.getId());
        }
        if(tParkStatisticsDay.getParkId()!=null){
            criteria.andParkIdEqualTo(tParkStatisticsDay.getParkId());
        }
        if(tParkStatisticsDay.getStatisticTime()!=null){
            criteria.andStatisticTimeEqualTo(tParkStatisticsDay.getStatisticTime());
        }

        }
        return  tParkStatisticsDayCriteria;
    }

    /**
    * 获取数据总量
    * @param tParkStatisticsDay
    * @return
    */
    private Integer getCount(TParkStatisticsDay tParkStatisticsDay){
    Integer total =(int)tParkStatisticsDayMapper.countByExample(setCriteria(tParkStatisticsDay));
    return total;
    }

    /**
    *查询tParkStatisticsDay信息(分页)
    * @param tParkStatisticsDay
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TParkStatisticsDay> getTParkStatisticsDaybyPage(TParkStatisticsDay tParkStatisticsDay, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TParkStatisticsDay> tParkStatisticsDays=getTParkStatisticsDay(tParkStatisticsDay);
            Integer countNums =getCount(tParkStatisticsDay);
            PageBean<TParkStatisticsDay> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tParkStatisticsDays);
            return pageData;
        }

    /**
    * 查询tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    public List<TParkStatisticsDay> getTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay){
    List<TParkStatisticsDay>  tParkStatisticsDays=tParkStatisticsDayMapper.selectByExample(setCriteria(tParkStatisticsDay));
    return tParkStatisticsDays;
    }

    /**
    * 更新tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    public String UpdateTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay)
    {
            String msg="";
            try{
            if(tParkStatisticsDay.getId()!=null){
            tParkStatisticsDayMapper.updateByPrimaryKeySelective(tParkStatisticsDay);
                msg="更新TParkStatisticsDay成功";
            }
            else
            {
            tParkStatisticsDayMapper.insertSelective(tParkStatisticsDay);
                msg="新建TParkStatisticsDay成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tParkStatisticsDay信息
    * @param tParkStatisticsDay
    * @return
    */
    public String DeleteTParkStatisticsDay(TParkStatisticsDay tParkStatisticsDay){
            String msg="";
            if(tParkStatisticsDay.getId()!=null){
            tParkStatisticsDayMapper.deleteByPrimaryKey(tParkStatisticsDay.getId());
            msg="删除TParkStatisticsDay成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tParkStatisticsDay信息
    * @param id
    * @return
    */
    public TParkStatisticsDay getTParkStatisticsDayByID(Integer id) {
        return  tParkStatisticsDayMapper.selectByPrimaryKey(id);
    }
}
