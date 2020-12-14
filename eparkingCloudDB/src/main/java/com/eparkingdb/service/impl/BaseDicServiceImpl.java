package com.eparkingdb.service.impl;


import com.common.entity.eparkingCloud.*;
import com.eparkingdb.dao.TDicOuttypeMapper;
import com.eparkingdb.dao.TDicPrepayTypeMapper;
import com.eparkingdb.dao.TParkPortMapper;
import com.eparkingdb.service.BaseDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TParkPortMapper tParkPortMapper;
    @Autowired
    private TDicOuttypeMapper tDicOuttypeMapper;
    @Autowired
    private TDicPrepayTypeMapper tDicPrepayTypeMapper;

    @Override
    public List<TParkPort> selectParkPort(String portType,Integer parkId) {
        //((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(Common.ParkId).toString();
//        Integer parkid = SessionUtil.getParkId();
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkId);
        if (portType==null || portType==""){
            criteria.andPortTypeIsNotNull();
        }else {
            criteria.andPortTypeEqualTo(Integer.valueOf(portType));
        }
//        return tParkPortMapper.selectByExample(tParkPortCriteria);
        List<TParkPort> tParkPortList = tParkPortMapper.selectByExample(tParkPortCriteria);
//        String aa = JsonUtil.listToJson(tParkPortList);
        return tParkPortList;
    }

    @Override
    public List<TDicOuttype> selectOutType() {
        TDicOuttypeCriteria tDicOuttypeCriteria = new TDicOuttypeCriteria();
        TDicOuttypeCriteria.Criteria criteria = tDicOuttypeCriteria.createCriteria();
        return tDicOuttypeMapper.selectByExample(tDicOuttypeCriteria);
    }
    @Override
    public List<TDicPrepayType> selectgetPayType() {
        TDicPrepayTypeCriteria tDicPrepayTypeCriteria = new TDicPrepayTypeCriteria();
        TDicPrepayTypeCriteria.Criteria criteria = tDicPrepayTypeCriteria.createCriteria();
        return tDicPrepayTypeMapper.selectByExample(tDicPrepayTypeCriteria);
    }
    /*@Override
    public List<TDicOuttype> selectOutType() {
        TDicOuttypeCriteria tDicOuttypeCriteria = new TDicOuttypeCriteria();
        TDicOuttypeCriteria.Criteria criteria = tDicOuttypeCriteria.createCriteria();
        return tDicOuttypeMapper.selectByExample(tDicOuttypeCriteria);
    }

    @Override
    public List<TDicPrepayType> selectgetPayType() {
        TDicPrepayTypeCriteria tDicPrepayTypeCriteria = new TDicPrepayTypeCriteria();
        TDicPrepayTypeCriteria.Criteria criteria = tDicPrepayTypeCriteria.createCriteria();
        return tDicPrepayTypeMapper.selectByExample(tDicPrepayTypeCriteria);
    }*/

    /**
     * 查询全部页面信息Ztree
     * @return
     */
   /* @Override
    public List<ZtreeRsp> getPortNameListForZtree(String portType) {
        List<ZtreeRsp> ztreeRspList=new ArrayList<ZtreeRsp>();
        try{
            String parkid = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(Common.ParkId).toString();
            TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
            TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
            criteria.andParkIdEqualTo(Integer.valueOf(parkid));
            if (portType==null){
                criteria.andPortTypeIsNotNull();
            }else {
                criteria.andPortTypeEqualTo(Integer.valueOf(portType));
            }
            List<TParkPort> tParkPortLists =  tParkPortMapper.selectByExample(tParkPortCriteria);

            ZtreeRsp ztreeRspall=new ZtreeRsp();
            ztreeRspall.setId(0);
            ztreeRspall.setpId(0);
            ztreeRspall.setName("所有通道");
            ztreeRspall.setOpen(true);
            ztreeRspList.add(ztreeRspall);
            for (TParkPort tParkPortList:tParkPortLists) {
                ZtreeRsp ztreeRsp=new ZtreeRsp();
                ztreeRsp.setId(tParkPortList.getId());
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
    }*/

}
