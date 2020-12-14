package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBindingParking;
import com.common.entity.eparkingCloud.TBindingParkingCriteria;
import com.eparkingdb.dao.TBindingParkingMapper;
import com.eparkingdb.service.TBindingParkingService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TBindingParkingService接口实现类
* @author 谢轩然
* @date 2020/04/08 18:18
*/
@Service
public class TBindingParkingServiceImpl  implements TBindingParkingService {

    private  static final Logger logger= LoggerFactory.getLogger( TBindingParkingServiceImpl.class);
    @Autowired
    private TBindingParkingMapper tBindingParkingMapper;

    /**
    * 设置查询条件
    * @param tBindingParking
    * @return
    */
    private  TBindingParkingCriteria setCriteria(TBindingParking tBindingParking){
        TBindingParkingCriteria tBindingParkingCriteria= new TBindingParkingCriteria();
        if(tBindingParking!=null){
        TBindingParkingCriteria.Criteria criteria=tBindingParkingCriteria.createCriteria();
        if(tBindingParking.getId()!=null){
        criteria.andIdEqualTo(tBindingParking.getId());
        }

        }
        return  tBindingParkingCriteria;
    }

    /**
    * 获取数据总量
    * @param tBindingParking
    * @return
    */
    private Integer getCount(TBindingParking tBindingParking){
    Integer total =(int)tBindingParkingMapper.countByExample(setCriteria(tBindingParking));
    return total;
    }

    /**
    *查询tBindingParking(分页)
    * @param tBindingParking
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TBindingParking> getTBindingParkingbyPage(TBindingParking tBindingParking, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TBindingParking> tBindingParkings=getTBindingParking(tBindingParking);
            Integer countNums =getCount(tBindingParking);
            PageBean<TBindingParking> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tBindingParkings);
            return pageData;
        }

    /**
    * 查询tBindingParking
    * @param tBindingParking
    * @return
    */
    public List<TBindingParking> getTBindingParking(TBindingParking tBindingParking){
    List<TBindingParking>  tBindingParkings=tBindingParkingMapper.selectByExample(setCriteria(tBindingParking));
    return tBindingParkings;
    }

    /**
    * 更新tBindingParking
    * @param tBindingParking
    * @return
    */
    public String UpdateTBindingParking(TBindingParking tBindingParking)
    {
            String msg="";
            try{
            if(tBindingParking.getId()!=null){
            tBindingParkingMapper.updateByPrimaryKeySelective(tBindingParking);
                msg="更新TBindingParking成功";
            }
            else
            {
            tBindingParkingMapper.insertSelective(tBindingParking);
                msg="新建TBindingParking成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tBindingParking
    * @param tBindingParking
    * @return
    */
    public String DeleteTBindingParking(TBindingParking tBindingParking){
            String msg="";
            if(tBindingParking.getId()!=null){
            tBindingParkingMapper.deleteByPrimaryKey(tBindingParking.getId());
            msg="删除TBindingParking成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tBindingParking
    * @param id
    * @return
    */
    public TBindingParking getTBindingParkingByID(Integer id) {
        return  tBindingParkingMapper.selectByPrimaryKey(id);
    }
}
