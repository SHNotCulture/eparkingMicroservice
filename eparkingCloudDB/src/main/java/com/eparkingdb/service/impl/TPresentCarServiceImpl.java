package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.service.TPresentCarService;
import com.common.util.DateUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPresentCarServiceImpl implements TPresentCarService {
    private  static final Logger logger= LoggerFactory.getLogger(TPresentCarServiceImpl.class);
    @Autowired
    private CustomizeMapper customizeMapper;
    /**
     *设置查询条件
     * @param tParkInOut
     * @param timeType
     * @return
     */
    private String setCriteria(TParkInOut tParkInOut,String timeType){
        String bDate = null;
        String eData = null;
        String nData = DateUtil.getCurDateTime();
        switch (timeType) {
            case "stageOne":
                bDate = DateUtil.getStartDateInterval(nData,-30);
                break;
            case "stageTwo":
                bDate = DateUtil.getStartDateInterval(nData,-60);
                eData = DateUtil.getStartDateInterval(nData,-30);
                break;
            case "stageThree":
                bDate = DateUtil.getStartDateInterval(nData,-(60*24));
                eData = DateUtil.getStartDateInterval(nData,-60);
                break;
            case "stageFour":
                bDate = DateUtil.getStartDateInterval(nData,-(60*24*7));
                eData = DateUtil.getStartDateInterval(nData,-(60*24));
                break;
            case "stageFive":
                eData = DateUtil.getStartDateInterval(nData,-(60*24*7));
                break;
            default:
                bDate = null;
                eData = null;
                break;
        }
        /*TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();*/
        StringBuffer stringBuffer = new StringBuffer();
        if (bDate!=null && eData!=null){
            stringBuffer.append(" and in_time between '"+bDate+"' and '"+eData+"'");
        }if (bDate==null && eData!=null){
            stringBuffer.append(" and in_time<='"+eData+"'");
        }if (bDate!=null && eData==null){
            stringBuffer.append(" and in_time>='"+bDate+"'");
        }
        if(tParkInOut!=null){
            if (tParkInOut.getInPortId()!=null){
                stringBuffer.append(" and in_port_id="+tParkInOut.getInPortId()+"");
            }if (tParkInOut.getCarNature()!=null && tParkInOut.getCarNature()!=0){
                stringBuffer.append(" and car_nature="+tParkInOut.getCarNature()+"");
            }
            if (!StringUtils.isBlank(tParkInOut.getInCarPlate())){
                stringBuffer.append(" and in_car_plate like '%"+tParkInOut.getInCarPlate()+"%'");
            }
            if (tParkInOut.getInTime()!=null || tParkInOut.getOutTime()!=null){if (tParkInOut.getInTime().length()>1 || tParkInOut.getOutTime().length()>1){
                stringBuffer.append(" and in_time between '"+tParkInOut.getInTime()+"' and '"+tParkInOut.getOutTime()+"'");
            }}
            if (tParkInOut.getUpdatedincarplate()!=null){
                stringBuffer.append(" and LENGTH(updatedInCarplate)>0");
            }
            if (tParkInOut.getCarNatureDesc()!=null && !tParkInOut.getCarNatureDesc().equals("")){
                stringBuffer.append(" and car_nature_desc like '%"+tParkInOut.getCarNatureDesc()+"%'");
            }
        }
        return  stringBuffer.toString();
    }

    @Override
    public PageBean<TParkInOut> getTPresentCarbyPage(TParkInOut tParkInOut, String parkId, String timeType, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"occur_time desc");
        List<TParkInOut> tParkInOuts=getTPresentCar(tParkInOut,timeType,parkId);
        Integer countNums =getCount(tParkInOut,timeType,parkId);
        PageBean<TParkInOut> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkInOuts);
        return pageData;
    }

    /**
     * 表单搜索查询在场车辆
     * @param tParkInOut
     * @return
     */
    @Override
    public List<TParkInOut> getTPresentCar(TParkInOut tParkInOut,String timeType,String parkId) {
        String andSql = setCriteria(tParkInOut,timeType);
        List<TParkInOut> tParkInOutList = customizeMapper.selectPresentCar(parkId,andSql);
        for (TParkInOut parkInOut:tParkInOutList) {
            parkInOut.setStopTime(DateUtil.nowBetweenStrtime(parkInOut.getInTime()));
        }
        return tParkInOutList;
    }

    /**
     * 获取数据总量
     * @param tParkInOut
     * @param timeType
     * @return
     */
    private Integer getCount(TParkInOut tParkInOut,String timeType,String parkId){
        String andSql = setCriteria(tParkInOut,timeType);
        Integer total = customizeMapper.selectPresentCar(parkId,andSql).size();
        return total;
    }
}
