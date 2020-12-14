package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.dao.TBlacklistMapper;
import com.eparkingdb.dao.TParkPortMapper;
import com.eparkingdb.service.TBlacklistService;
import com.common.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TBlacklistService接口实现类
* @author 谢轩然
* @date 2020/05/14 16:29
*/
@Service
public class TBlacklistServiceImpl  implements TBlacklistService {

    private  static final Logger logger= LoggerFactory.getLogger(TBlacklistServiceImpl.class);
    @Autowired
    private TBlacklistMapper tBlacklistMapper;
    @Autowired
    private TParkPortMapper tParkPortMapper;
    @Autowired
    private CustomizeMapper customizeMapper;

    /**
    * 设置查询条件
    * @param tBlacklist
    * @return
    */
    private  TBlacklistCriteria setCriteria(TBlacklist tBlacklist){
        TBlacklistCriteria tBlacklistCriteria= new TBlacklistCriteria();
        if(tBlacklist!=null){
        TBlacklistCriteria.Criteria criteria=tBlacklistCriteria.createCriteria();
        if(tBlacklist.getId()!=null){
        criteria.andIdEqualTo(tBlacklist.getId());
        }
            if(tBlacklist.getParkId()!=null) {
                criteria.andParkIdEqualTo(tBlacklist.getParkId());
            }
            if (tBlacklist.getCarPlate()!=null){
                criteria.andCarPlateLike("%"+tBlacklist.getCarPlate()+"%");
            }
        }
        return  tBlacklistCriteria;
    }

    /**
    * 获取数据总量
    * @param tBlacklist
    * @return
    */
    private Integer getCount(TBlacklist tBlacklist){
    Integer total =(int)tBlacklistMapper.countByExample(setCriteria(tBlacklist));
    return total;
    }

    /**
    *查询tBlacklist(分页)
    * @param tBlacklist
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TBlacklist> getTBlacklistbyPage(TBlacklist tBlacklist, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TBlacklist> tBlacklists=getTBlacklist(tBlacklist);
            Integer countNums =getCount(tBlacklist);
            PageBean<TBlacklist> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tBlacklists);
            return pageData;
        }

    /**
    * 查询tBlacklist
    * @param tBlacklist
    * @return
    */
    public List<TBlacklist> getTBlacklist(TBlacklist tBlacklist){
    List<TBlacklist>  tBlacklists=tBlacklistMapper.selectByExample(setCriteria(tBlacklist));
    return tBlacklists;
    }

    /**
    * 更新tBlacklist
    * @param tBlacklist
    * @return
    */
    public String UpdateTBlacklist(TBlacklist tBlacklist)
    {
/*        String msg;
        TBlacklistCriteria tBlacklistCriteria = new TBlacklistCriteria();
        TBlacklistCriteria.Criteria criteria = tBlacklistCriteria.createCriteria();
        criteria.andParkIdEqualTo(tBlacklist.getParkId());
        TBlacklist tBlacklistnew = new TBlacklist();
        if (tBlacklist.getId()!=null){//编辑
            TBlacklist tBlacklistPar = tBlacklistMapper.selectByPrimaryKey(tBlacklist.getId());
            if (!tBlacklistPar.getCarPlate().equals(tBlacklist.getCarPlate())){//修改了车牌
                criteria.andCarPlateEqualTo(tBlacklist.getCarPlate());
                if (tBlacklistMapper.selectByExample(tBlacklistCriteria).size()>0){
                    msg = "该车牌已存在";
                }else {
                    tBlacklistnew=tBlacklist;
                    tBlacklistMapper.updateByPrimaryKey(tBlacklist);
                    msg = "修改成功";
                }
            }else {
                tBlacklistnew=tBlacklist;
                tBlacklistMapper.updateByPrimaryKey(tBlacklist);
                msg = "修改成功";
            }
        }else{//新增
            criteria.andCarPlateEqualTo(tBlacklist.getCarPlate());
            if (tBlacklistMapper.selectByExample(tBlacklistCriteria).size()>0){
                msg = "该车牌已存在";
            }else{
                tBlacklistMapper.insert(tBlacklist);
                tBlacklistnew=tBlacklistMapper.selectByExample(tBlacklistCriteria).get(0);
                msg = "成功添加";}
        }*/
        String msg="";
        TBlacklist tBlacklistnew= new TBlacklist();
        TBlacklistCriteria tBlacklistCriteria = new TBlacklistCriteria();
        TBlacklistCriteria.Criteria criteria = tBlacklistCriteria.createCriteria();
        criteria.andParkIdEqualTo(tBlacklist.getParkId());
        criteria.andCarPlateEqualTo(tBlacklist.getCarPlate());
        //新增操作
        if(tBlacklist.getId()==null){
            //新增的车牌如果已经是黑名单车
            if (tBlacklistMapper.selectByExample(tBlacklistCriteria).size()>0) {
                msg = "该车牌已存在";
            }
               else{
                    //允许添加为黑名单车
                    tBlacklistnew=tBlacklist;
                    tBlacklistMapper.insertSelective(tBlacklist);
                    msg = "新增成功";
                }
            }
        else{
            //编辑操作
            criteria.andIdNotEqualTo(tBlacklist.getId());
            Integer size = tBlacklistMapper.selectByExample(tBlacklistCriteria).size();
            if (size>0) {
                msg = "该车牌已存在";
            }else {
                tBlacklistMapper.updateByPrimaryKey(tBlacklist);
                tBlacklistnew=tBlacklist;
                msg = "修改成功";
            }
        }
        if (tBlacklistnew.getId()==null){
            tBlacklistnew.setId(getTBlacklist(tBlacklistnew).get(0).getId());
        }
        SynchronizeBlack(tBlacklistnew,"false");
        return msg;
    }

    /**
    * 删除tBlacklist
    * @param tBlacklist
    * @return
    */
    public String DeleteTBlacklist(TBlacklist tBlacklist){
        tBlacklist = tBlacklistMapper.selectByPrimaryKey(tBlacklist.getId());
        tBlacklistMapper.deleteByPrimaryKey(tBlacklist.getId());
        SynchronizeBlack(tBlacklist,"true");
        return "删除成功";
    }

    /**
    * 根据ID查询tBlacklist
    * @param id
    * @return
    */
    public TBlacklist getTBlacklistByID(Integer id) {
        return  tBlacklistMapper.selectByPrimaryKey(id);
    }

    /**
     * 同步任务
     * @param tBlacklist
     * @param type
     */
    public synchronized void SynchronizeBlack(TBlacklist tBlacklist,String type){
        BlackListTask blackListTask = new BlackListTask();
        blackListTask.setId(tBlacklist.getId());
        blackListTask.setCarPlate(StringUtil.enCode(tBlacklist.getCarPlate()));
        blackListTask.setBeginTime(tBlacklist.getBeginTime());
        blackListTask.setParkId(tBlacklist.getParkId());
        blackListTask.setEndTime(tBlacklist.getEndTime());
        blackListTask.setCreatePerson(StringUtil.enCode(tBlacklist.getCreatePerson()));
        blackListTask.setDelete(type);
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(tBlacklist.getParkId());
        criteria.andPortTypeEqualTo(0);
        List<TParkPort> tParkPortList = tParkPortMapper.selectByExample(tParkPortCriteria);
        for (TParkPort tParkPort : tParkPortList){
            customizeMapper.insertBlackTask(tBlacklist.getParkId(),blackListTask,tParkPort.getComputerIndex());
        }
    }
}
