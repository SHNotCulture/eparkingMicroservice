package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TApikey;
import com.common.entity.eparkingCloud.TApikeyCriteria;
import com.eparkingdb.dao.TApikeyMapper;
import com.eparkingdb.service.TApikeyService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TApikeyService接口实现类
* @author 谢轩然
* @date 2020/04/08 17:46
*/
@Service
public class TApikeyServiceImpl  implements TApikeyService {

    private  static final Logger logger= LoggerFactory.getLogger( TApikeyServiceImpl.class);
    @Autowired
    private TApikeyMapper tApikeyMapper;

    /**
    * 设置查询条件
    * @param tApikey
    * @return
    */
    private  TApikeyCriteria setCriteria(TApikey tApikey){
        TApikeyCriteria tApikeyCriteria= new TApikeyCriteria();
        if(tApikey!=null) {
            TApikeyCriteria.Criteria criteria = tApikeyCriteria.createCriteria();
            if (tApikey.getId() != null) {
                criteria.andIdEqualTo(tApikey.getId());
            }
            if (tApikey.getApiId() != null) {
                criteria.andApiIdEqualTo(tApikey.getApiId());
            }
        }
        return  tApikeyCriteria;
    }

    /**
    * 获取数据总量
    * @param tApikey
    * @return
    */
    private Integer getCount(TApikey tApikey){
    Integer total =(int)tApikeyMapper.countByExample(setCriteria(tApikey));
    return total;
    }

    /**
    *查询(分页)tApikey
    * @param tApikey
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TApikey> getTApikeybyPage(TApikey tApikey, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TApikey> TApikeys=getTApikey(tApikey);
            Integer countNums =getCount(tApikey);
            PageBean<TApikey> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(TApikeys);
            return pageData;
        }

    /**
    * 查询tApikey
    * @param tApikey
    * @return
    */
    public List<TApikey> getTApikey(TApikey tApikey){
    List<TApikey>  tApikeys=tApikeyMapper.selectByExample(setCriteria(tApikey));
    return tApikeys;
    }

    /**
    * 更新tApikey
    * @param tApikey
    * @return
    */
    public String UpdateTApikey(TApikey tApikey)
    {
            String msg="";
            try{
            if(tApikey.getId()!=null){
            tApikeyMapper.updateByPrimaryKeySelective(tApikey);
                msg="更新TApikey成功";
            }
            else
            {
            tApikeyMapper.insertSelective(tApikey);
                msg="新建TApikey成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tApikey
    * @param tApikey
    * @return
    */
    public String DeleteTApikey(TApikey tApikey){
            String msg="";
            if(tApikey.getId()!=null){
            tApikeyMapper.deleteByPrimaryKey(tApikey.getId());
            msg="删除TApikey成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tApikey
    * @param id
    * @return
    */
    public TApikey getTApikeyByID(Integer id) {
        return  tApikeyMapper.selectByPrimaryKey(id);
    }
}
