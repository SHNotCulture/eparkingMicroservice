package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TParkPort;
import com.common.entity.eparkingCloud.YuncsisResult;
import com.eparking.insideService.TParkPortInsideService;
import com.eparking.service.ParkPortService;
import com.eparking.service.YuncsisApiService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkPortServiceImpl implements ParkPortService {
    private static final Logger logger = LoggerFactory.getLogger(ParkPortServiceImpl.class);
/*    @Autowired
    private TParkPortMapper tParkPortMapper;*/
    @Autowired
    private TParkPortInsideService tParkPortInsideService;
    @Autowired
    private YuncsisApiService yuncsisApiService;


    /**
     * 设置查询条件
     *
     * @param tParkPort
     * @return
     */
/*    private TParkPortCriteria setCriteria(TParkPort tParkPort) {
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        if (tParkPort != null) {
            TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
            if (tParkPort.getParkId() != null) {
                criteria.andParkIdEqualTo(tParkPort.getParkId());
            }
            if (tParkPort.getPortId() != null) {
                criteria.andPortIdEqualTo(tParkPort.getPortId());
            }
            if (tParkPort.getPortName() != null && tParkPort.getPortName() != "") {
                criteria.andPortNameLike("%" + tParkPort.getPortName() + "%");
            }
            if (tParkPort.getPortType() != null) {
                criteria.andPortTypeEqualTo(tParkPort.getPortType());
            }
            if (tParkPort.getComputerIndex() != null && tParkPort.getComputerIndex() != "") {
                criteria.andComputerIndexEqualTo(tParkPort.getComputerIndex());
            }
            if(tParkPort.getIpcType()!=null){
                criteria.andIpcTypeEqualTo(tParkPort.getIpcType());
            }
        }
        return tParkPortCriteria;
    }*/

    /**
     * 获取数据总量
     *
     * @param tParkPort
     * @return
     */
/*    private Integer getCount(TParkPort tParkPort) {
        Integer total = (int) tParkPortMapper.countByExample(setCriteria(tParkPort));
        return total;
    }*/

    /**
     * 查询通道信息(分页)
     *
     * @param tParkPort
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TParkPort> getParkPortbyPage(TParkPort tParkPort, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkPort> tParkPortLists=getParkPort(tParkPort);
        Integer countNums =getCount(tParkPort);
        PageBean<TParkPort> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkPortLists);
        return pageData;*/
        return tParkPortInsideService.getTParkPortbyPage(tParkPort, page, limit);
    }


    @Override
    public List<TParkPort> getParkPort(TParkPort tParkPort) {
//        List<TParkPort> tParkPortLists = tParkPortMapper.selectByExample(setCriteria(tParkPort));
        List<TParkPort> tParkPortLists = tParkPortInsideService.getTParkPort(tParkPort);
        return tParkPortLists;
    }


    @Override
    public String updateParkPort(TParkPort tParkPort) {
//        String msg="";

/*        if (tParkPort.getId()!=null){//编辑
            tParkPortMapper.updateByPrimaryKeySelective(tParkPort);
            msg = "更新成功";
        }else{//新增
            tParkPortMapper.insertSelective(tParkPort);
            msg = "新建成功";
        }*/

//        return msg;
        String msg = tParkPortInsideService.UpdateTParkPort(tParkPort);
        if(msg.equals("更新成功")){
            Map map = updateParkPortMap(tParkPort,1);
            YuncsisResult yuncsisResult = yuncsisApiService.updateLaneinfo(map);
//            logger.info(yuncsisResult.getSuccess());
        }else if(msg.equals("新建成功")){
            List<TParkPort> tParkPortList = tParkPortInsideService.getTParkPort(tParkPort);
            tParkPort.setId(tParkPortList.get(0).getId());
            Map map = updateParkPortMap(tParkPort,1);
            YuncsisResult yuncsisResult = yuncsisApiService.updateLaneinfo(map);
//            logger.info(yuncsisResult.getSuccess());
        }
        return msg;
    }

    @Override
    public String deleteParkPort(TParkPort tParkPort) {
/*        tParkPort = tParkPortMapper.selectByPrimaryKey(tParkPort.getId());
        tParkPortMapper.deleteByPrimaryKey(tParkPort.getId());
        String msg="";*/
//        return "删除成功";
        String msg="";
        if(tParkPort.getId()!=null){
            msg = tParkPortInsideService.DeleteTParkPort(tParkPort);
            if (msg.equals("删除成功")) {
                Map map = updateParkPortMap(tParkPort,2);
                yuncsisApiService.updateLaneinfo(map);
            }
        }

        return msg;
    }

    public Map updateParkPortMap(TParkPort tParkPort,Integer flag){
        Map map = new HashMap();
        map.put("flag",flag);
        map.put("cloudId",tParkPort.getId());
        map.put("parkId",tParkPort.getParkId());
        map.put("parkName",tParkPort.getParkName());
        map.put("laneId",tParkPort.getId());
        map.put("laneName",tParkPort.getPortName());
        map.put("laneControlSn",tParkPort.getLaneControlSn());
        map.put("ipcIp",tParkPort.getIpcIp());
        map.put("ipcSn",tParkPort.getIpcSn());
        map.put("auxIpc1Sn",tParkPort.getAuxIpc1Sn());
        map.put("auxIpc2Sn",tParkPort.getAuxIpc2Sn());
        map.put("laneType",tParkPort.getPortType());
        map.put("areaNumber",tParkPort.getAreaNumber());
        map.put("intervene",tParkPort.getIntervene());
        map.put("actived",tParkPort.getActived());
        map.put("lotsLedIp",tParkPort.getLotsLedIp());
        map.put("lotsLedStr",tParkPort.getLotsLedStr());
        map.put("isInCarUpdate",tParkPort.getIsInCarUpdate());
        map.put("laneRxMode",tParkPort.getLaneRxMode());
        map.put("voiceMode",tParkPort.getVoiceMode());
        map.put("voiceVolume",tParkPort.getVoiceVolume());
        map.put("ipcType",tParkPort.getIpcType());
        return map;
    }
}
