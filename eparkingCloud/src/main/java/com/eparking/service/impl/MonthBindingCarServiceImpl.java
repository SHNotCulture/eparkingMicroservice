package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.insideService.MqttClientInsideService;
import com.eparking.insideService.TMonthBindingCarInsideService;
import com.eparking.insideService.TParkCarInsideService;
import com.eparking.service.MonthBindingCarService;
import com.eparking.service.MonthlyCarService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonthBindingCarServiceImpl implements MonthBindingCarService {
    private static final Logger logger = LoggerFactory.getLogger(MonthBindingCarServiceImpl.class);
/*    @Autowired
    private TMonthBindingCarMapper tMonthBindingCarMapper;*/
    @Autowired
    private TMonthBindingCarInsideService tMonthBindingCarInsideService;
    @Autowired
    private MonthlyCarService monthlyCarService;
    @Autowired
    private TParkCarInsideService tParkCarInsideService;
    @Autowired
    private MqttClientInsideService mqttClientInsideService;

    /**
     * 设置查询条件
     * @param tMonthBindingCar
     * @return
     */
/*    private TMonthBindingCarCriteria setCriteria(TMonthBindingCar tMonthBindingCar){
        TMonthBindingCarCriteria tMonthBindingCarCriteria = new TMonthBindingCarCriteria();
        if(tMonthBindingCar!=null){
            TMonthBindingCarCriteria.Criteria criteria = tMonthBindingCarCriteria.createCriteria();
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
        }
        return  tMonthBindingCarCriteria;
    }*/
    /**
     * 获取数据总量
     * @param tMonthBindingCar
     * @return
     */
/*    private Integer getCount(TMonthBindingCar tMonthBindingCar){
        Integer total =(int)tMonthBindingCarMapper.countByExample(setCriteria(tMonthBindingCar));
        return total;
    }*/

    @Override
    public PageBean<TMonthBindingCar> getMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TMonthBindingCar> tMonthBindingCarLists=getMonthBindingCar(tMonthBindingCar);
        Integer countNums =getCount(tMonthBindingCar);
        PageBean<TMonthBindingCar> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tMonthBindingCarLists);
        return pageData;*/
        return tMonthBindingCarInsideService.getTMonthBindingCarbyPage(tMonthBindingCar, page, limit);
    }

    @Override
    public List<TMonthBindingCar> getMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
//        List<TMonthBindingCar> tMonthBindingCarLists = tMonthBindingCarMapper.selectByExample(setCriteria(tMonthBindingCar));
        List<TMonthBindingCar> tMonthBindingCarLists = tMonthBindingCarInsideService.getTMonthBindingCar(tMonthBindingCar);
        return tMonthBindingCarLists;
    }

    @Override
    public String updateMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
/*        String msg="";

        if (tMonthBindingCar.getId()!=null){//编辑
            tMonthBindingCarMapper.updateByPrimaryKeySelective(tMonthBindingCar);
            msg = "编辑成功";
        }else{//新增
            tMonthBindingCarMapper.insertSelective(tMonthBindingCar);
            msg = "成功添加";
        }

        return msg;*/
        String result="";
        String carPlateStr = tMonthBindingCar.getCarPlate();
        String carPlateErrorStr="";
        String carPlateSuccesStr="";
        carPlateStr.replaceAll("，",",");
        for(String carplateOne:carPlateStr.split(",")){
            carplateOne=carplateOne.trim();
/*            TMonthBindingCar monthBindingCarCheckOne = new TMonthBindingCar();
            monthBindingCarCheckOne.setCarPlate(carplateOne);
            if(tMonthBindingCarInsideService.getTMonthBindingCar(monthBindingCarCheckOne).size()<=0){*/
            TMonthBindingCar tMonthBindingCarCheck = new TMonthBindingCar();
            tMonthBindingCarCheck.setCarPlate(carplateOne);
            tMonthBindingCarCheck.setParkId(tMonthBindingCar.getParkId());
            if(checkCarplateOnlyOne(tMonthBindingCarCheck)){
                if(StringUtil.checkCarplate(carplateOne)==true) {
                    tMonthBindingCar.setCarPlate(carplateOne);
                    result = tMonthBindingCarInsideService.UpdateTMonthBindingCar(tMonthBindingCar);
                    List<TMonthBindingCar> tMonthBindingCarList = tMonthBindingCarInsideService.getTMonthBindingCar(tMonthBindingCar);
                    TMonthBindingCar monthBindingCar = tMonthBindingCarList.get(0);
//                    String url = "http://s2.eparking.top:8083/eparkingMqttClient/inside/bindingCar";
//        String url= "http://192.168.10.104:8083/inside/bindingCar";
                    Map map = new HashMap();
                    map.put("carType", monthBindingCar.getCarType().toString());
                    map.put("parkCarId", monthBindingCar.getParkCarId().toString());
                    map.put("carPlate", monthBindingCar.getCarPlate());
                    map.put("updateTime", monthBindingCar.getUpdateTime());
                    map.put("parkId", monthBindingCar.getParkId().toString());
                    map.put("flag", "1"); //1增加  2删除
                    map.put("carId", monthBindingCar.getId().toString());
                    String resultBindCar = mqttClientInsideService.bindingCar(map);
//                    String resultBindCar = HttpClientUtil.doPostJson(url, JsonUtil.mapToJson(map));
//                    logger.info("resultBindCar: "+resultBindCar);
                    TParkCar tParkCar = monthlyCarService.getTParkCarById(tMonthBindingCar.getParkCarId());

                    TMonthBindingCar tMonthBindingCarSel = new TMonthBindingCar();
                    tMonthBindingCarSel.setParkCarId(monthBindingCar.getParkCarId());
                    //查询ParkCarId下所有绑定车
                    List<TMonthBindingCar> tMonthBindingCarlist = tMonthBindingCarInsideService.getTMonthBindingCar(tMonthBindingCarSel);
                    Integer i = 5;
                    String carPlate = "";
                    for (TMonthBindingCar tMonthBindingCarReturn : tMonthBindingCarlist) {
                        if (i > 0) {
                            if (carPlate.equals("")) {
                                carPlate = tMonthBindingCarReturn.getCarPlate();
                            } else {
                                carPlate = carPlate + "," + tMonthBindingCarReturn.getCarPlate();
                            }
                            i--;

                        }
                    }
                    tParkCar.setCarPlate(carPlate);
                    tParkCarInsideService.UpdateTParkCar(tParkCar);
                    //编辑或新增后下发任务
                    monthlyCarService.syncParkCarTask(tParkCar);
                    if(carPlateSuccesStr.equals("")){
                        carPlateSuccesStr=carplateOne;
                    }else{
                        carPlateSuccesStr=carPlateSuccesStr+","+carplateOne;
                    }
                }else{
                    if(carPlateErrorStr.equals("")){
                        carPlateErrorStr=carplateOne;
                    }else{
                        carPlateErrorStr=carPlateErrorStr+","+carplateOne;
                    }
//                throw new ActionRspException(ActionRspEnum.Carplate_ERROR);
                }
            }else{
                if(carPlateErrorStr.equals("")){
                    carPlateErrorStr=carplateOne;
                }else{
                    carPlateErrorStr=carPlateErrorStr+","+carplateOne;
                }
            }
        }
        if(carPlateErrorStr.length()>0){
            result="车牌:"+carPlateErrorStr+"不合法或已存在，请重新输入";
        }else{
            result="成功添加车牌:"+carPlateSuccesStr;
        }
        return result;
    }

    @Override
    public String deleteMonthBindingCar(TMonthBindingCar tMonthBindingCar) {
/*        tMonthBindingCar = tMonthBindingCarMapper.selectByPrimaryKey(tMonthBindingCar.getId());
        tMonthBindingCarMapper.deleteByPrimaryKey(tMonthBindingCar.getId());

        return "删除成功";*/
//        System.out.println(tMonthBindingCar.toString());
        Map map= new HashMap();
//        String url= "http://s2.eparking.top:8083/eparkingMqttClient/inside/bindingCar";
        map.put("carType",tMonthBindingCar.getCarType().toString());
        map.put("parkCarId",tMonthBindingCar.getParkCarId().toString());
        map.put("carPlate",tMonthBindingCar.getCarPlate());
        map.put("updateTime", tMonthBindingCar.getUpdateTime());
        map.put("parkId",tMonthBindingCar.getParkId().toString());
        map.put("flag", "2"); //1增加  2删除
        map.put("carId", tMonthBindingCar.getId().toString());
//        String resultBindCar = HttpClientUtil.doPostJson(url, JsonUtil.mapToJson(map));
        String resultBindCar = mqttClientInsideService.bindingCar(map);
//        logger.info("resultBindCar: "+resultBindCar);
        String result = tMonthBindingCarInsideService.DeleteTMonthBindingCar(tMonthBindingCar);
        TParkCar tParkCar = monthlyCarService.getTParkCarById(tMonthBindingCar.getParkCarId());
        TMonthBindingCar tMonthBindingCarSel=new TMonthBindingCar();
        tMonthBindingCarSel.setParkCarId(tMonthBindingCar.getParkCarId());
        //查询ParkCarId下所有绑定车
        List<TMonthBindingCar> tMonthBindingCarlist = tMonthBindingCarInsideService.getTMonthBindingCar(tMonthBindingCarSel);
        Integer i=5;
        String carPlate = "";
        for(TMonthBindingCar tMonthBindingCarReturn:tMonthBindingCarlist){
            if(i>0){
                if(carPlate.equals("")){
                    carPlate=tMonthBindingCarReturn.getCarPlate();
                }else{
                    carPlate=carPlate+","+tMonthBindingCarReturn.getCarPlate();
                }
                i--;
            }
        }
        tParkCar.setBindingNum(tMonthBindingCarlist.size());
        tParkCar.setCarPlate(carPlate);
        monthlyCarService.UpdateTParkCar(tParkCar);
        return result;
    }

    @Override
    public Boolean checkCarplateOnlyOne(TMonthBindingCar tMonthBindingCar) {
        TMonthBindingCar monthBindingCarCheckOne = new TMonthBindingCar();
        monthBindingCarCheckOne.setCarPlate(tMonthBindingCar.getCarPlate());
        monthBindingCarCheckOne.setParkId(tMonthBindingCar.getParkId());
        Integer num = tMonthBindingCarInsideService.getTMonthBindingCar(monthBindingCarCheckOne).size();
        if(num>0){
            return false;
        }
        return true;
    }
}
