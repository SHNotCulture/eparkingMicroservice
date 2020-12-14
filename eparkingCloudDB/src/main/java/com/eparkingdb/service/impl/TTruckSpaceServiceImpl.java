package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TTruckSpace;
import com.common.entity.eparkingCloud.TTruckSpaceCriteria;
import com.eparkingdb.dao.TTruckSpaceMapper;
import com.eparkingdb.service.TTruckSpaceService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TTruckSpaceService接口实现类
* @author 谢轩然
* @date 2020/05/15 11:22
*/
@Service
public class TTruckSpaceServiceImpl  implements TTruckSpaceService {

    private  static final Logger logger= LoggerFactory.getLogger( TTruckSpaceServiceImpl.class);
    @Autowired
    private TTruckSpaceMapper tTruckSpaceMapper;

    /**
    * 设置查询条件
    * @param tTruckSpace
    * @return
    */
    private  TTruckSpaceCriteria setCriteria(TTruckSpace tTruckSpace){
        TTruckSpaceCriteria tTruckSpaceCriteria= new TTruckSpaceCriteria();
        if(tTruckSpace!=null){
        TTruckSpaceCriteria.Criteria criteria=tTruckSpaceCriteria.createCriteria();
        if(tTruckSpace.getId()!=null){
        criteria.andIdEqualTo(tTruckSpace.getId());
        }
            if(tTruckSpace.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tTruckSpace.getParkId());
            }
            if(tTruckSpace.getParkingState()!=null)
            {
                criteria.andParkingStateEqualTo(tTruckSpace.getParkingState());
            }
            if(tTruckSpace.getParkingSpace()!=null)
            {
                criteria.andParkingSpaceEqualTo(tTruckSpace.getParkingSpace());
            }
            if(tTruckSpace.getCompanyId()!=null)
            {
                criteria.andCompanyIdEqualTo(tTruckSpace.getCompanyId());
            }
        }
        return  tTruckSpaceCriteria;
    }

    /**
    * 获取数据总量
    * @param tTruckSpace
    * @return
    */
    private Integer getCount(TTruckSpace tTruckSpace){
    Integer total =(int)tTruckSpaceMapper.countByExample(setCriteria(tTruckSpace));
    return total;
    }

    /**
    *查询tTruckSpace(分页)
    * @param tTruckSpace
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TTruckSpace> getTTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TTruckSpace> tTruckSpaces=getTTruckSpace(tTruckSpace);
            Integer countNums =getCount(tTruckSpace);
            PageBean<TTruckSpace> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tTruckSpaces);
            return pageData;
        }

    /**
    * 查询tTruckSpace
    * @param tTruckSpace
    * @return
    */
    public List<TTruckSpace> getTTruckSpace(TTruckSpace tTruckSpace){
    List<TTruckSpace>  tTruckSpaces=tTruckSpaceMapper.selectByExample(setCriteria(tTruckSpace));
    return tTruckSpaces;
    }

    /**
    * 更新tTruckSpace
    * @param tTruckSpace
    * @return
    */
    public String UpdateTTruckSpace(TTruckSpace tTruckSpace)
    {
            String msg="";
            try{
            if(tTruckSpace.getId()!=null){
            tTruckSpaceMapper.updateByPrimaryKeySelective(tTruckSpace);
                msg="更新成功";
            }
            else
            {
                tTruckSpace.setTheirCarPlate(" ");
                tTruckSpace.setUseCarPlate(" ");
            tTruckSpaceMapper.insertSelective(tTruckSpace);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tTruckSpace
    * @param tTruckSpace
    * @return
    */
    public String DeleteTTruckSpace(TTruckSpace tTruckSpace){
            String msg="";
            if(tTruckSpace.getId()!=null){
            tTruckSpaceMapper.deleteByPrimaryKey(tTruckSpace.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tTruckSpace
    * @param id
    * @return
    */
    public TTruckSpace getTTruckSpaceByID(Integer id) {
        return  tTruckSpaceMapper.selectByPrimaryKey(id);
    }
}
