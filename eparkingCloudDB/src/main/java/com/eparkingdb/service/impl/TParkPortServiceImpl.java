package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TParkPort;
import com.common.entity.eparkingCloud.TParkPortCriteria;
import com.eparkingdb.dao.TParkPortMapper;
import com.eparkingdb.service.TParkPortService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谢轩然
 * @Description: TParkPortService接口实现类
 * @date 2020/05/29 15:05
 */
@Service
public class TParkPortServiceImpl implements TParkPortService {

    private static final Logger logger = LoggerFactory.getLogger(TParkPortServiceImpl.class);
    @Autowired
    private TParkPortMapper tParkPortMapper;

    /**
     * 设置查询条件
     *
     * @param tParkPort
     * @return
     */
    private TParkPortCriteria setCriteria(TParkPort tParkPort) {
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        if (tParkPort != null) {
            TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
            if (tParkPort.getId() != null) {
                criteria.andIdEqualTo(tParkPort.getId());
            }
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
            if (tParkPort.getParkName() != null && tParkPort.getParkName() != "") {
                criteria.andParkNameEqualTo(tParkPort.getParkName());
            }
            if (tParkPort.getLaneControlSn() != null && tParkPort.getLaneControlSn() != "") {
                criteria.andLaneControlSnEqualTo(tParkPort.getLaneControlSn());
            }
            if (tParkPort.getIpcIp() != null && tParkPort.getIpcIp() != "") {
                criteria.andIpcIpEqualTo(tParkPort.getIpcIp());
            }
            if (tParkPort.getIpcSn() != null && tParkPort.getIpcSn() != "") {
                criteria.andIpcSnEqualTo(tParkPort.getIpcSn());
            }
            if (tParkPort.getAuxIpc1Sn() != null && tParkPort.getAuxIpc1Sn() != "") {
                criteria.andAuxIpc1SnEqualTo(tParkPort.getAuxIpc1Sn());
            }
            if (tParkPort.getAuxIpc2Sn() != null && tParkPort.getAuxIpc2Sn() != "") {
                criteria.andAuxIpc2SnEqualTo(tParkPort.getAuxIpc2Sn());
            }
            if (tParkPort.getAreaNumber() != null) {
                criteria.andAreaNumberEqualTo(tParkPort.getAreaNumber());
            }
            if (tParkPort.getIntervene() != null) {
                criteria.andInterveneEqualTo(tParkPort.getIntervene());
            }
            if (tParkPort.getActived() != null) {
                criteria.andActivedEqualTo(tParkPort.getActived());
            }
            if (tParkPort.getLotsLedIp() != null && tParkPort.getLotsLedIp() != "") {
                criteria.andLotsLedIpEqualTo(tParkPort.getLotsLedIp());
            }
            if (tParkPort.getLotsLedStr() != null && tParkPort.getLotsLedStr() != "") {
                criteria.andLotsLedStrEqualTo(tParkPort.getLotsLedStr());
            }
            if (tParkPort.getIsInCarUpdate() != null) {
                criteria.andIsInCarUpdateEqualTo(tParkPort.getIsInCarUpdate());
            }
            if (tParkPort.getLaneRxMode() != null) {
                criteria.andLaneRxModeEqualTo(tParkPort.getLaneRxMode());
            }
            if (tParkPort.getVoiceMode() != null) {
                criteria.andVoiceModeEqualTo(tParkPort.getVoiceMode());
            }
            if (tParkPort.getVoiceVolume() != null) {
                criteria.andVoiceVolumeEqualTo(tParkPort.getVoiceVolume());
            }
        }
        return tParkPortCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tParkPort
     * @return
     */
    private Integer getCount(TParkPort tParkPort) {
        Integer total = (int) tParkPortMapper.countByExample(setCriteria(tParkPort));
        return total;
    }

    /**
     * 查询tParkPort(分页)
     *
     * @param tParkPort
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkPort> getTParkPortbyPage(TParkPort tParkPort, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TParkPort> tParkPorts = getTParkPort(tParkPort);
        Integer countNums = getCount(tParkPort);
        PageBean<TParkPort> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkPorts);
        return pageData;
    }

    /**
     * 查询tParkPort
     *
     * @param tParkPort
     * @return
     */
    public List<TParkPort> getTParkPort(TParkPort tParkPort) {
        List<TParkPort> tParkPorts = tParkPortMapper.selectByExample(setCriteria(tParkPort));
        return tParkPorts;
    }

    /**
     * 更新tParkPort
     *
     * @param tParkPort
     * @return
     */
    public String UpdateTParkPort(TParkPort tParkPort) {
        String msg = "";
        try {
            if (tParkPort.getId() != null) {
                tParkPortMapper.updateByPrimaryKeySelective(tParkPort);
                msg = "更新成功";
            } else {
                tParkPortMapper.insertSelective(tParkPort);
                msg = "新建成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
     * 删除tParkPort
     *
     * @param tParkPort
     * @return
     */
    public String DeleteTParkPort(TParkPort tParkPort) {
        String msg = "";
        if (tParkPort.getId() != null) {
            tParkPortMapper.deleteByPrimaryKey(tParkPort.getId());
            msg = "删除成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tParkPort
     *
     * @param id
     * @return
     */
    public TParkPort getTParkPortByID(Integer id) {
        return tParkPortMapper.selectByPrimaryKey(id);
    }

    @Override
    public TParkPort getTParkPortByParkIdAndPortId(Integer parkId, Integer porId) {
        return tParkPortMapper.getTParkPortByParkIdAndPortId(parkId, porId);
    }

    @Override
    public List<TParkPort> getTParkPortOutPort(TParkPort tParkPort) {
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(tParkPort.getParkId());
        //查找该停车场所有的出场电脑
        criteria.andPortTypeEqualTo(1);
        List<TParkPort> tParkPortList = tParkPortMapper.selectByExample(tParkPortCriteria);
        return tParkPortList;
    }

    @Override
    public List<TParkPort> getPortNameListForZtree(Integer parkId, String portType) {
        TParkPortCriteria tParkPortCriteria = new TParkPortCriteria();
        TParkPortCriteria.Criteria criteria = tParkPortCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkId);
        if (portType == null || portType == "") {
            criteria.andPortTypeIsNotNull();
        } else {
            criteria.andPortTypeEqualTo(Integer.valueOf(portType));
        }
        List<TParkPort> tParkPortLists = tParkPortMapper.selectByExample(tParkPortCriteria);
        return tParkPortLists;
    }
}
