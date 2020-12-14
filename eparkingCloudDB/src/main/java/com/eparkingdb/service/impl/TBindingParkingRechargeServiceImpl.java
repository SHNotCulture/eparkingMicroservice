package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBindingParkingRecharge;
import com.common.entity.eparkingCloud.TBindingParkingRechargeCriteria;
import com.eparkingdb.dao.TBindingParkingRechargeMapper;
import com.eparkingdb.service.TBindingParkingRechargeService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TBindingParkingRechargeService接口实现类
* @author 谢轩然
* @date 2020/04/08 18:25
*/
@Service
public class TBindingParkingRechargeServiceImpl  implements TBindingParkingRechargeService {

    private  static final Logger logger= LoggerFactory.getLogger( TBindingParkingRechargeServiceImpl.class);
    @Autowired
    private TBindingParkingRechargeMapper tBindingParkingRechargeMapper;

    /**
    * 设置查询条件
    * @param tBindingParkingRecharge
    * @return
    */
    private  TBindingParkingRechargeCriteria setCriteria(TBindingParkingRecharge tBindingParkingRecharge){
        TBindingParkingRechargeCriteria tBindingParkingRechargeCriteria= new TBindingParkingRechargeCriteria();
        if(tBindingParkingRecharge!=null){
        TBindingParkingRechargeCriteria.Criteria criteria=tBindingParkingRechargeCriteria.createCriteria();
        if(tBindingParkingRecharge.getId()!=null){
        criteria.andIdEqualTo(tBindingParkingRecharge.getId());
        }

        }
        return  tBindingParkingRechargeCriteria;
    }

    /**
    * 获取数据总量
    * @param tBindingParkingRecharge
    * @return
    */
    private Integer getCount(TBindingParkingRecharge tBindingParkingRecharge){
    Integer total =(int)tBindingParkingRechargeMapper.countByExample(setCriteria(tBindingParkingRecharge));
    return total;
    }

    /**
    *查询tBindingParkingRecharge(分页)
    * @param tBindingParkingRecharge
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TBindingParkingRecharge> getTBindingParkingRechargebyPage(TBindingParkingRecharge tBindingParkingRecharge, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TBindingParkingRecharge> tBindingParkingRecharges=getTBindingParkingRecharge(tBindingParkingRecharge);
            Integer countNums =getCount(tBindingParkingRecharge);
            PageBean<TBindingParkingRecharge> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tBindingParkingRecharges);
            return pageData;
        }

    /**
    * 查询tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    public List<TBindingParkingRecharge> getTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge){
    List<TBindingParkingRecharge>  tBindingParkingRecharges=tBindingParkingRechargeMapper.selectByExample(setCriteria(tBindingParkingRecharge));
    return tBindingParkingRecharges;
    }

    /**
    * 更新tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    public String UpdateTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge)
    {
            String msg="";
            try{
            if(tBindingParkingRecharge.getId()!=null){
            tBindingParkingRechargeMapper.updateByPrimaryKeySelective(tBindingParkingRecharge);
                msg="更新TBindingParkingRecharge成功";
            }
            else
            {
            tBindingParkingRechargeMapper.insertSelective(tBindingParkingRecharge);
                msg="新建TBindingParkingRecharge成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tBindingParkingRecharge
    * @param tBindingParkingRecharge
    * @return
    */
    public String DeleteTBindingParkingRecharge(TBindingParkingRecharge tBindingParkingRecharge){
            String msg="";
            if(tBindingParkingRecharge.getId()!=null){
            tBindingParkingRechargeMapper.deleteByPrimaryKey(tBindingParkingRecharge.getId());
            msg="删除TBindingParkingRecharge成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tBindingParkingRecharge
    * @param id
    * @return
    */
    public TBindingParkingRecharge getTBindingParkingRechargeByID(Integer id) {
        return  tBindingParkingRechargeMapper.selectByPrimaryKey(id);
    }
}
