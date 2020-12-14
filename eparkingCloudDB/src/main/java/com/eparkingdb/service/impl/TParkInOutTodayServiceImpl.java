package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOutToday;
import com.common.entity.eparkingCloud.TParkInOutTodayCriteria;
import com.eparkingdb.dao.TParkInOutTodayMapper;
import com.eparkingdb.service.TParkInOutTodayService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TParkInOutTodayService接口实现类
* @author 徐名佳
* @date 2020/12/04 15:50
*/
@Service
public class TParkInOutTodayServiceImpl implements TParkInOutTodayService {

    private  static final Logger logger= LoggerFactory.getLogger( TParkInOutTodayServiceImpl.class);
    @Autowired
    private TParkInOutTodayMapper tParkInOutTodayMapper;

    /**
    * 设置查询条件
    * @param tParkInOutToday
    * @return
    */
    private TParkInOutTodayCriteria setCriteria(TParkInOutToday tParkInOutToday){
        TParkInOutTodayCriteria tParkInOutTodayCriteria= new TParkInOutTodayCriteria();
        if(tParkInOutToday!=null){
        TParkInOutTodayCriteria.Criteria criteria=tParkInOutTodayCriteria.createCriteria();
        if(tParkInOutToday.getId()!=null){
        criteria.andIdEqualTo(tParkInOutToday.getId());
        }

        }
        return  tParkInOutTodayCriteria;
    }

    /**
    * 获取数据总量
    * @param tParkInOutToday
    * @return
    */
    private Integer getCount(TParkInOutToday tParkInOutToday){
    Integer total =(int)tParkInOutTodayMapper.countByExample(setCriteria(tParkInOutToday));
    return total;
    }

    /**
    *查询tParkInOutToday信息(分页)
    * @param tParkInOutToday
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TParkInOutToday> getTParkInOutTodaybyPage(TParkInOutToday tParkInOutToday, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TParkInOutToday> tParkInOutTodays=getTParkInOutToday(tParkInOutToday);
            Integer countNums =getCount(tParkInOutToday);
            PageBean<TParkInOutToday> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tParkInOutTodays);
            return pageData;
        }

    /**
    * 查询tParkInOutToday信息
    * @param tParkInOutToday
    * @return
    */
    public List<TParkInOutToday> getTParkInOutToday(TParkInOutToday tParkInOutToday){
    List<TParkInOutToday>  tParkInOutTodays=tParkInOutTodayMapper.selectByExample(setCriteria(tParkInOutToday));
    return tParkInOutTodays;
    }

    /**
    * 更新tParkInOutToday信息
    * @param tParkInOutToday
    * @return
    */
    public String updateTParkInOutToday(TParkInOutToday tParkInOutToday)
    {
            String msg="";
            try{
            if(tParkInOutToday.getId()!=null){
            tParkInOutTodayMapper.updateByPrimaryKeySelective(tParkInOutToday);
                msg="更新TParkInOutToday成功";
            }
            else
            {
            tParkInOutTodayMapper.insertSelective(tParkInOutToday);
                msg="新建TParkInOutToday成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tParkInOutToday信息
    * @param tParkInOutToday
    * @return
    */
    public String DeleteTParkInOutToday(TParkInOutToday tParkInOutToday){
            String msg="";
            if(tParkInOutToday.getId()!=null){
            tParkInOutTodayMapper.deleteByPrimaryKey(tParkInOutToday.getId());
            msg="删除TParkInOutToday成功";
            }else{
            tParkInOutTodayMapper.deleteByExample(setCriteria(tParkInOutToday));
            }
            return msg;
    }

    /**
    * 根据ID查询tParkInOutToday信息
    * @param id
    * @return
    */
    public TParkInOutToday getTParkInOutTodaybyId(Integer id) {
        return  tParkInOutTodayMapper.selectByPrimaryKey(id);
    }
}
