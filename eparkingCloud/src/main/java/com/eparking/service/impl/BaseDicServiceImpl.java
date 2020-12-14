package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TDicOuttype;
import com.common.entity.eparkingCloud.TDicPrepayType;
import com.common.entity.eparkingCloud.TParkPort;
import com.common.entity.eparkingCloud.ZtreeRsp;
import com.eparking.insideService.BaseDicInsideService;
import com.eparking.insideService.TParkPortInsideService;
import com.eparking.service.BaseDicService;
import com.common.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseDicServiceImpl
 * @Author jin
 * @Date 2018/9/27 15:48
 **/
@Service
public class BaseDicServiceImpl implements BaseDicService {

    private  static final Logger logger= LoggerFactory.getLogger(BaseDicServiceImpl.class);

    @Autowired
    private TParkPortInsideService tParkPortInsideService;
    @Autowired
    private BaseDicInsideService baseDicInsideService;

    @Override
    public List<TParkPort> selectParkPort(String portType, Integer parkId) {
        //((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(Common.ParkId).toString();
/*        Integer parkid = SessionUtil.getParkId();
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkid);
        if (portType==null){
            criteria.andPortTypeIsNotNull();
        }else {
            criteria.andPortTypeEqualTo(Integer.valueOf(portType));
        }
        return tParkPortMapper.selectByExample(tParkPortCriteria);*/
//        return  baseDicInsideService.selectParkPort(portType,parkId);
        return baseDicInsideService.selectParkPort(portType,parkId);
    }

    @Override
    public List<TDicOuttype> selectOutType() {
/*        TDicOuttypeCriteria tDicOuttypeCriteria = new TDicOuttypeCriteria();
        TDicOuttypeCriteria.Criteria criteria = tDicOuttypeCriteria.createCriteria();
        return tDicOuttypeMapper.selectByExample(tDicOuttypeCriteria);*/
        return baseDicInsideService.selectOutType();
    }

    @Override
    public List<TDicPrepayType> selectgetPayType() {
/*        TDicPrepayTypeCriteria tDicPrepayTypeCriteria = new TDicPrepayTypeCriteria();
        TDicPrepayTypeCriteria.Criteria criteria = tDicPrepayTypeCriteria.createCriteria();
        return tDicPrepayTypeMapper.selectByExample(tDicPrepayTypeCriteria);*/
        return baseDicInsideService.selectgetPayType();
    }

    /**
     * 查询全部页面信息Ztree
     * @return
     */
    @Override
    public List<ZtreeRsp> getPortNameListForZtree(String portType) {
        List<ZtreeRsp> ztreeRspList=new ArrayList<ZtreeRsp>();
        try{
            String parkId = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(Common.ParkId).toString();
/*            TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
            TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
            criteria.andParkIdEqualTo(Integer.valueOf(parkid));
            if (portType==null){
                criteria.andPortTypeIsNotNull();
            }else {
                criteria.andPortTypeEqualTo(Integer.valueOf(portType));
            }
            List<TParkPort> tParkPortLists =  tParkPortMapper.selectByExample(tParkPortCriteria);*/
            List<TParkPort> tParkPortLists = tParkPortInsideService.getPortNameListForZtree(Integer.valueOf(parkId),portType);
            ZtreeRsp ztreeRspall=new ZtreeRsp();
            ztreeRspall.setId(0);
            ztreeRspall.setpId(0);
            ztreeRspall.setName("所有通道");
            ztreeRspall.setOpen(true);
            ztreeRspList.add(ztreeRspall);
            for (TParkPort tParkPortList:tParkPortLists) {
                ZtreeRsp ztreeRsp=new ZtreeRsp();
                ztreeRsp.setId(tParkPortList.getPortId());
                ztreeRsp.setpId(0);
                ztreeRsp.setName(tParkPortList.getPortName());
                //if(tParkPortList.getPid().equals(0)){
                //ztreeRsp.setOpen(true);
                //}
                ztreeRspList.add(ztreeRsp);
            }
        }
        catch (Exception e){
            logger.info("获取Ztree信息异常,异常信息为："+e.toString());
        }

        return ztreeRspList;
    }

    @Override
    public List<ZtreeRsp> getPortNameListForZtreeByParkId(String parkId) {
        List<ZtreeRsp> ztreeRspList=new ArrayList<ZtreeRsp>();
        try{
            List<TParkPort> tParkPortLists = tParkPortInsideService.getPortNameListForZtree(Integer.valueOf(parkId),"");
            ZtreeRsp ztreeRspall=new ZtreeRsp();
            ztreeRspall.setId(0);
            ztreeRspall.setpId(0);
            ztreeRspall.setName("所有通道");
            ztreeRspall.setOpen(true);
            ztreeRspList.add(ztreeRspall);
            for (TParkPort tParkPortList:tParkPortLists) {
                ZtreeRsp ztreeRsp=new ZtreeRsp();
                ztreeRsp.setId(tParkPortList.getPortId());
                ztreeRsp.setpId(0);
                ztreeRsp.setName(tParkPortList.getPortName());
                //if(tParkPortList.getPid().equals(0)){
                //ztreeRsp.setOpen(true);
                //}
                ztreeRspList.add(ztreeRsp);
            }
        }
        catch (Exception e){
            logger.info("获取Ztree信息异常,异常信息为："+e.toString());
        }

        return ztreeRspList;
    }

}
