package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.entity.eparkingCloud.TMonthBindingCarCriteria;
import com.eparkingdb.dao.TMonthBindingCarMapper;
import com.eparkingdb.service.TMonthBindingCarService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TMonthBindingCarService接口实现类
* @author 谢轩然
* @date 2020/05/26 17:04
*/
@Service
public class TMonthBindingCarServiceImpl  implements TMonthBindingCarService {

    private  static final Logger logger= LoggerFactory.getLogger( TMonthBindingCarServiceImpl.class);
    @Autowired
    private TMonthBindingCarMapper tMonthBindingCarMapper;

    /**
    * 设置查询条件
    * @param tMonthBindingCar
    * @return
    */
    private  TMonthBindingCarCriteria setCriteria(TMonthBindingCar tMonthBindingCar){
        TMonthBindingCarCriteria tMonthBindingCarCriteria= new TMonthBindingCarCriteria();
        if(tMonthBindingCar!=null){
        TMonthBindingCarCriteria.Criteria criteria=tMonthBindingCarCriteria.createCriteria();
        if(tMonthBindingCar.getId()!=null){
        criteria.andIdEqualTo(tMonthBindingCar.getId());
        }
            if(tMonthBindingCar.getParkId()!=null) {
                criteria.andParkIdEqualTo(tMonthBindingCar.getParkId());
            }
            if (tMonthBindingCar.getParkCarId()!=null){
                criteria.andParkCarIdEqualTo(tMonthBindingCar.getParkCarId());
            }
            if (tMonthBindingCar.getCarPlate()!=null && tMonthBindingCar.getCarPlate()!=""){
                criteria.andCarPlateLike("%"+tMonthBindingCar.getCarPlate()+"%");
            }
            if(tMonthBindingCar.getCarType()!=null){
                criteria.andCarTypeEqualTo(tMonthBindingCar.getCarType());
            }
            if(tMonthBindingCar.getUpdateTime()!=null && tMonthBindingCar.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tMonthBindingCar.getUpdateTime());
            }
            if(tMonthBindingCar.getUserId()!=null){
                criteria.andUserIdEqualTo(tMonthBindingCar.getUserId());
            }
            if(tMonthBindingCar.getCreateTime()!=null && tMonthBindingCar.getCreateTime()!=""){
                criteria.andCreateTimeEqualTo(tMonthBindingCar.getCreateTime());
            }
            tMonthBindingCarCriteria.setOrderByClause("id desc");
        }
        return  tMonthBindingCarCriteria;
    }

    /**
    * 获取数据总量
    * @param tMonthBindingCar
    * @return
    */
    private Integer getCount(TMonthBindingCar tMonthBindingCar){
    Integer total =(int)tMonthBindingCarMapper.countByExample(setCriteria(tMonthBindingCar));
    return total;
    }

    /**
    *查询tMonthBindingCar(分页)
    * @param tMonthBindingCar
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TMonthBindingCar> getTMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TMonthBindingCar> tMonthBindingCars=getTMonthBindingCar(tMonthBindingCar);
            Integer countNums =getCount(tMonthBindingCar);
            PageBean<TMonthBindingCar> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tMonthBindingCars);
            return pageData;
        }

    /**
    * 查询tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    public List<TMonthBindingCar> getTMonthBindingCar(TMonthBindingCar tMonthBindingCar){
    List<TMonthBindingCar>  tMonthBindingCars=tMonthBindingCarMapper.selectByExample(setCriteria(tMonthBindingCar));
    return tMonthBindingCars;
    }

    /**
    * 更新tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    public String UpdateTMonthBindingCar(TMonthBindingCar tMonthBindingCar)
    {
            String msg="";
            try{
            if(tMonthBindingCar.getId()!=null){
            tMonthBindingCarMapper.updateByPrimaryKeySelective(tMonthBindingCar);
                msg="更新成功";
            }
            else
            {
            tMonthBindingCarMapper.insertSelective(tMonthBindingCar);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tMonthBindingCar
    * @param tMonthBindingCar
    * @return
    */
    public String DeleteTMonthBindingCar(TMonthBindingCar tMonthBindingCar){
            String msg="";
            if(tMonthBindingCar.getId()!=null){
            tMonthBindingCarMapper.deleteByPrimaryKey(tMonthBindingCar.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tMonthBindingCar
    * @param id
    * @return
    */
    public TMonthBindingCar getTMonthBindingCarByID(Integer id) {
        return  tMonthBindingCarMapper.selectByPrimaryKey(id);
    }
}
