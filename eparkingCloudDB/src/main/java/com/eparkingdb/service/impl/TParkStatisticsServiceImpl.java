package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkStatistics;
import com.common.entity.eparkingCloud.TParkStatisticsCriteria;
import com.eparkingdb.dao.TParkStatisticsMapper;
import com.eparkingdb.service.TCompanyParkService;
import com.eparkingdb.service.TParkStatisticsService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TParkStatisticsService接口实现类
* @author 李书瀚
* @date 2020/06/09 16:05
*/
@Service
public class TParkStatisticsServiceImpl  implements TParkStatisticsService {

    private  static final Logger logger= LoggerFactory.getLogger( TParkStatisticsServiceImpl.class);
    @Autowired
    private TParkStatisticsMapper tParkStatisticsMapper;

    @Autowired
    private TCompanyParkService tCompanyParkInsideService;

    /**
    * 设置查询条件
    * @param tParkStatistics
    * @return
    */
    private  TParkStatisticsCriteria setCriteria(TParkStatistics tParkStatistics){
        TParkStatisticsCriteria tParkStatisticsCriteria= new TParkStatisticsCriteria();
        if(tParkStatistics!=null){
        TParkStatisticsCriteria.Criteria criteria=tParkStatisticsCriteria.createCriteria();
        if(tParkStatistics.getId()!=null){
        criteria.andIdEqualTo(tParkStatistics.getId());
        }

        }
        return  tParkStatisticsCriteria;
    }

    /**
    * 获取数据总量
    * @param tParkStatistics
    * @return
    */
    private Integer getCount(TParkStatistics tParkStatistics){
    Integer total =(int)tParkStatisticsMapper.countByExample(setCriteria(tParkStatistics));
    return total;
    }

    /**
    *查询tParkStatistics信息(分页)
    * @param tParkStatistics
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TParkStatistics> getTParkStatisticsbyPage(TParkStatistics tParkStatistics, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TParkStatistics> tParkStatisticss=getTParkStatistics(tParkStatistics);
            Integer countNums =getCount(tParkStatistics);
            PageBean<TParkStatistics> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tParkStatisticss);
            return pageData;
        }

    /**
    * 查询tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    public List<TParkStatistics> getTParkStatistics(TParkStatistics tParkStatistics){
    List<TParkStatistics>  tParkStatisticss=tParkStatisticsMapper.selectByExample(setCriteria(tParkStatistics));
    return tParkStatisticss;
    }
    public TParkStatistics getTParkStatisticsbyOne(TParkStatistics tParkStatistics){
        List<TParkStatistics>  tParkStatisticsList=tParkStatisticsMapper.selectByExample(setCriteria(tParkStatistics));
        TParkStatistics result=new TParkStatistics();
        if(tParkStatisticsList.size()==0){
           /* tCompanyParkInsideService.getTCompanyParkByID();
            result.setParkSpacesTotal();*/
        }
        return result;
    }

    /**
    * 更新tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    public String UpdateTParkStatistics(TParkStatistics tParkStatistics)
    {
            String msg="";
            try{
            if(tParkStatistics.getId()!=null){
            tParkStatisticsMapper.updateByPrimaryKeySelective(tParkStatistics);
                msg="更新TParkStatistics成功";
            }
            else
            {
            tParkStatisticsMapper.insertSelective(tParkStatistics);
                msg="新建TParkStatistics成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tParkStatistics信息
    * @param tParkStatistics
    * @return
    */
    public String DeleteTParkStatistics(TParkStatistics tParkStatistics){
            String msg="";
            if(tParkStatistics.getId()!=null){
            tParkStatisticsMapper.deleteByPrimaryKey(tParkStatistics.getId());
            msg="删除TParkStatistics成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tParkStatistics信息
    * @param id
    * @return
    */
    public TParkStatistics getTParkStatisticsByID(Integer id) {
        return  tParkStatisticsMapper.selectByPrimaryKey(id);
    }
}
