package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkCar;
import com.common.entity.eparkingCloud.TParkCarCriteria;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.dao.TParkCarMapper;
import com.eparkingdb.service.TParkCarService;
import com.common.util.*;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢轩然
 * @Description: TParkCarService接口实现类
 * @date 2020/05/14 15:43
 */
@Service
public class TParkCarServiceImpl implements TParkCarService {

    private static final Logger logger = LoggerFactory.getLogger(TParkCarServiceImpl.class);
    @Autowired
    private TParkCarMapper tParkCarMapper;
    @Autowired
    private CustomizeMapper customizeMapper;


    /**
     * 设置查询条件
     *
     * @param tParkCar
     * @return
     */
    private TParkCarCriteria setCriteria(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        if (tParkCar != null) {
            TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
            if (tParkCar.getId() != null) {
                criteria.andIdEqualTo(tParkCar.getId());
            }
            if (tParkCar.getParkId() != null) {
                criteria.andParkIdEqualTo(tParkCar.getParkId());
            }
            if (tParkCar.getStatus() != null) {
                criteria.andStatusEqualTo(tParkCar.getStatus());
            }
            criteria.andParkingSpaceNotEqualTo(4);
           if (tParkCar.getParkingSpace() != null) {
                criteria.andParkingSpaceEqualTo(tParkCar.getParkingSpace());
            }
            if (tParkCar.getPayRule() != null) {
                criteria.andPayRuleEqualTo(tParkCar.getPayRule());
            }
            if (tParkCar.getParkCode() != null && !tParkCar.getParkCode().equals("")) {
                criteria.andParkCodeLike("%" + tParkCar.getParkCode() + "%");
            }
            if (tParkCar.getCarPlate() != null && !tParkCar.getCarPlate().equals("")) {
                criteria.andCarPlateLike("%" + tParkCar.getCarPlate() + "%");
            }
            if (endDateSelectStart!=null && !endDateSelectStart.equals("")){
                criteria.andEndDateGreaterThanOrEqualTo(endDateSelectStart);
            }
            if (endDateSelectEnd!=null && !endDateSelectEnd.equals("")){
                criteria.andEndDateLessThanOrEqualTo(endDateSelectEnd);
            }
            if(tParkCar.getCarNature()!=null){
                criteria.andCarNatureEqualTo(tParkCar.getCarNature());
            }
            if(tParkCar.getTempPayRule()!=null){
                criteria.andTempPayRuleEqualTo(tParkCar.getTempPayRule());
            }
/*            if (tParkCar.getStopTime() != null && tParkCar.getStopTime().length() > 0 && tParkCar.getLockTime() != null && tParkCar.getLockTime().length() > 0) {
                criteria.andStopTimeBetween(tParkCar.getStopTime(), tParkCar.getLockTime());
            }
            if (tParkCar.getStopTime() != null && tParkCar.getStopTime().length() > 0 && (tParkCar.getLockTime() == null || tParkCar.getLockTime().length() == 0)) {
                criteria.andStopTimeGreaterThanOrEqualTo(tParkCar.getStopTime());
            }
            if ((tParkCar.getStopTime() == null || tParkCar.getStopTime().length() == 0) && tParkCar.getLockTime() != null && tParkCar.getLockTime().length() > 0) {
                criteria.andStopTimeLessThanOrEqualTo(tParkCar.getLockTime());
            }*/
        }
        return tParkCarCriteria;
    }

    //车主车位查询条件
    private TParkCarCriteria setOwnerCriteria(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        if (tParkCar != null) {
            TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
            if (tParkCar.getId() != null) {
                criteria.andIdEqualTo(tParkCar.getId());
            }
            if (tParkCar.getParkId() != null) {
                criteria.andParkIdEqualTo(tParkCar.getParkId());
            }
            if (tParkCar.getStatus() != null) {
                criteria.andStatusEqualTo(tParkCar.getStatus());
            }
            if (tParkCar.getParkingSpace() != null) {
                criteria.andParkingSpaceEqualTo(tParkCar.getParkingSpace());
            }
            if (tParkCar.getPayRule() != null) {
                criteria.andPayRuleEqualTo(tParkCar.getPayRule());
            }
            if (tParkCar.getParkCode() != null && tParkCar.getParkCode().length() > 0) {
                criteria.andParkCodeLike("%" + tParkCar.getParkCode() + "%");
            }
            if (tParkCar.getCarPlate() != null && tParkCar.getCarPlate().length() > 0) {
                criteria.andCarPlateLike("%" + tParkCar.getCarPlate() + "%");
            }
            if (endDateSelectStart!=null && endDateSelectStart.length()>0){
                criteria.andEndDateGreaterThanOrEqualTo(endDateSelectStart);
            }
            if (endDateSelectEnd!=null && endDateSelectEnd.length()>0){
                criteria.andEndDateLessThanOrEqualTo(endDateSelectEnd);
            }
            if(tParkCar.getCarNature()!=null){
                criteria.andCarNatureEqualTo(tParkCar.getCarNature());
            }
            if(tParkCar.getTempPayRule()!=null){
                criteria.andTempPayRuleEqualTo(tParkCar.getTempPayRule());
            }
            if(tParkCar.getParkCode()!=null && tParkCar.getParkCode()!=""){
                criteria.andParkCodeEqualTo(tParkCar.getParkCode());
            }
/*            if (tParkCar.getStopTime() != null && tParkCar.getStopTime().length() > 0 && tParkCar.getLockTime() != null && tParkCar.getLockTime().length() > 0) {
                criteria.andStopTimeBetween(tParkCar.getStopTime(), tParkCar.getLockTime());
            }
            if (tParkCar.getStopTime() != null && tParkCar.getStopTime().length() > 0 && (tParkCar.getLockTime() == null || tParkCar.getLockTime().length() == 0)) {
                criteria.andStopTimeGreaterThanOrEqualTo(tParkCar.getStopTime());
            }
            if ((tParkCar.getStopTime() == null || tParkCar.getStopTime().length() == 0) && tParkCar.getLockTime() != null && tParkCar.getLockTime().length() > 0) {
                criteria.andStopTimeLessThanOrEqualTo(tParkCar.getLockTime());
            }*/
        }
        return tParkCarCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tParkCar
     * @return
     */
    private Integer getCount(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        Integer total = (int) tParkCarMapper.countByExample(setCriteria(tParkCar,endDateSelectStart,endDateSelectEnd));
        return total;
    }

    private Integer getOwnerCount(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        Integer total = (int) tParkCarMapper.countByExample(setOwnerCriteria(tParkCar,endDateSelectStart,endDateSelectEnd));
        return total;
    }

    /**
     * 查询tParkCar(分页)
     *
     * @param tParkCar
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkCar> getTParkCarbyPage(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TParkCar> tParkCars = getTParkCar(tParkCar,endDateSelectStart,endDateSelectEnd);
        Integer countNums = getCount(tParkCar,endDateSelectStart,endDateSelectEnd);
        PageBean<TParkCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkCars);
        return pageData;
    }

    public PageBean<TParkCar> getOwnerCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TParkCar> tParkCars = getOwnerCar(tParkCar,endDateSelectStart,endDateSelectEnd);
        Integer countNums = getOwnerCount(tParkCar,endDateSelectStart,endDateSelectEnd);
        PageBean<TParkCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkCars);
        return pageData;
    }

    /**
     * 查询tParkCar
     *
     * @param tParkCar
     * @return
     */
    public List<TParkCar> getTParkCar(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        List<TParkCar> tParkCars = tParkCarMapper.selectByExample(setCriteria(tParkCar,endDateSelectStart,endDateSelectEnd));
        return tParkCars;
    }

    public List<TParkCar> getOwnerCar(TParkCar tParkCar,String endDateSelectStart,String endDateSelectEnd) {
        List<TParkCar> tParkCars = tParkCarMapper.selectByExample(setOwnerCriteria(tParkCar,endDateSelectStart,endDateSelectEnd));
        return tParkCars;
    }

    /**
     * 更新tParkCar
     *
     * @param tParkCar
     * @return
     */
    public String UpdateTParkCar(TParkCar tParkCar) {
        String msg = "";
        try {
            if (tParkCar.getId() != null) {
                tParkCarMapper.updateByPrimaryKeySelective(tParkCar);
                msg = "更新成功";
            } else {
                tParkCarMapper.insertSelective(tParkCar);
                msg = "新建成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
     * 删除tParkCar
     *
     * @param tParkCar
     * @return
     */
    public String DeleteTParkCar(TParkCar tParkCar) {
        String msg = "";
        if (tParkCar.getId() != null) {
            tParkCarMapper.deleteByPrimaryKey(tParkCar.getId());
            msg = "删除TParkCar成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tParkCar
     *
     * @param id
     * @return
     */
    public TParkCar getTParkCarByID(Integer id) {
        return tParkCarMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<TParkCar> getCloseParkCar(String closeType, String payRule, String parkid) {
        return customizeMapper.selectCloseParkCar(closeType, payRule, parkid);
    }

    public PageBean<TParkCar> getCloseParkCarbyPage(String closeType, String payRule, String parkid, Integer page, Integer limit) {
        //查询总记录数量
        Integer tParkCarCount = (int) customizeMapper.selectCloseParkCarCount(closeType, payRule, parkid);
        PageHelper.startPage(page, limit, "id desc");
        //查询分页数据，limit数量
        List<TParkCar> tParkCars = customizeMapper.selectCloseParkCar(closeType, payRule, parkid);
        //总记录数量赋值
        Integer countNums = tParkCarCount;
        //返回分页数据
        PageBean<TParkCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkCars);
        return pageData;
    }

    @Override
    public String Resetport(String portids, String ids) {
        customizeMapper.Resetport(portids, ids);
        return "修改成功";
    }

    @Override
    public Integer insertTParkCarMap(TParkCar tParkCar) {
        return tParkCarMapper.insert(tParkCar);
    }

    @Override
    public List<TParkCar> whetherTParkCar(Integer parkId, String carPlate,String endDateSelectStart,String endDateSelectEnd) {
        TParkCar tParkCar = new TParkCar();
        tParkCar.setParkId(parkId);
        tParkCar.setCarPlate(carPlate);
        tParkCar.setEndDate(DateUtil.getCurDateTime());
        return tParkCarMapper.selectByExample(setCriteria(tParkCar,endDateSelectStart,endDateSelectEnd));
    }

    @Override
    public Integer getPresentCarNum(String parkIDlist) {
        List<Integer> idList = JsonUtil.json2List(parkIDlist,Integer.class);
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
        criteria.andParkIdIn(idList);
        Integer carNum = (int)tParkCarMapper.countByExample(tParkCarCriteria);
        return carNum;
    }

    @Override
    public List<TParkCar> getTParkCarForStatistics(TParkCar tParkCar,String date) {
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
        criteria.andParkIdEqualTo(tParkCar.getParkId());
        criteria.andCompanyIdEqualTo(tParkCar.getCompanyId());
        criteria.andBeginDateEqualTo(date);
        return tParkCarMapper.selectByExample(tParkCarCriteria);
    }

    @Override
    public PageBean<TParkCar> getTParkCarFuzzybyPage(TParkCar tParkCar,String parkCarIdList, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit) {
        List<Integer> idList = new ArrayList<>();
        if(parkCarIdList.length()>0){
            for(String one:parkCarIdList.split(",")){
                idList.add(Integer.valueOf(one));
            }
        }
        PageHelper.startPage(page, limit, "id desc");
        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
        if (idList.size()>0){
            criteria.andIdIn(idList);
        }
        if (tParkCar.getParkId() != null) {
            criteria.andParkIdEqualTo(tParkCar.getParkId());
        }
        if (tParkCar.getStatus() != null) {
            criteria.andStatusEqualTo(tParkCar.getStatus());
        }
        criteria.andParkingSpaceNotEqualTo(4);
        if (tParkCar.getParkingSpace() != null) {
            criteria.andParkingSpaceEqualTo(tParkCar.getParkingSpace());
        }
        if (tParkCar.getPayRule() != null) {
            criteria.andPayRuleEqualTo(tParkCar.getPayRule());
        }
        if (tParkCar.getParkCode() != null && !tParkCar.getParkCode().equals("")) {
            criteria.andParkCodeLike("%" + tParkCar.getParkCode() + "%");
        }
       /* if (tParkCar.getCarPlate() != null && !tParkCar.getCarPlate().equals("")) {
            criteria.andCarPlateLike("%" + tParkCar.getCarPlate() + "%");
        }*/
        if (endDateSelectStart!=null && !endDateSelectStart.equals("")){
            criteria.andEndDateGreaterThanOrEqualTo(endDateSelectStart);
        }
        if (endDateSelectEnd!=null && !endDateSelectEnd.equals("")){
            criteria.andEndDateLessThanOrEqualTo(endDateSelectEnd);
        }
        if(tParkCar.getCarNature()!=null){
            criteria.andCarNatureEqualTo(tParkCar.getCarNature());
        }
        if(tParkCar.getTempPayRule()!=null){
            criteria.andTempPayRuleEqualTo(tParkCar.getTempPayRule());
        }
        List<TParkCar> tParkCars = tParkCarMapper.selectByExample(tParkCarCriteria);
        Integer countNums = (int) tParkCarMapper.countByExample(tParkCarCriteria);
        PageBean<TParkCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkCars);
        return pageData;
    }

    @Override
    public TParkCar fineparkingdbyCarPlate(String inCarPlate, Integer parkId) {
        TParkCar tParkCar = new TParkCar();
        tParkCar.setParkId(parkId);
        tParkCar.setCarPlate(inCarPlate);
        return tParkCarMapper.selectByParkIdAndCarPlate(tParkCar);
    }
}
