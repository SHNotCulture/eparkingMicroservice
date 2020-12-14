package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TDayOff;
import com.common.entity.eparkingCloud.TDayOffCriteria;
import com.eparkingdb.dao.TDayOffMapper;
import com.eparkingdb.service.TDayOffService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TDayOffServiceImpl implements TDayOffService {
    private static final Logger logger = LoggerFactory.getLogger(TDayOffServiceImpl.class);
    @Autowired
    private TDayOffMapper tDayOffMapper;

    private TDayOffCriteria setCriteria(TDayOff tDayOff){
        //查询全部调休日信息
        TDayOffCriteria tDayOffCriteria= new TDayOffCriteria();
        if(tDayOff!=null){
            TDayOffCriteria.Criteria criteria=tDayOffCriteria.createCriteria();
            if(tDayOff.getParkId()!=null){
                criteria.andParkIdEqualTo(tDayOff.getParkId());
            }
        }
        return  tDayOffCriteria;
    }

    private Integer getCount(TDayOff tDayOff){
        Integer total =(int)tDayOffMapper.countByExample(setCriteria(tDayOff));
        return total;
    }

    public PageBean<TDayOff> getTDayOffbyPage(TDayOff tDayOff, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TDayOff> tDayOffList=getTDayOff(tDayOff);
        Integer countNums =getCount(tDayOff);
        PageBean<TDayOff> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tDayOffList);
        return pageData;
    }

    public List<TDayOff> getTDayOff(TDayOff tDayOff){
//        logger.info("tDayOff:"+tDayOff);
        List<TDayOff> tDayOffList=tDayOffMapper.selectByExample(setCriteria(tDayOff));
        return tDayOffList;
    }

    public String UpdateTDayOff(TDayOff tDayOff)
    {
//        logger.info(tDayOff.toString());
        if(tDayOff.getId()!=null){
            tDayOffMapper.updateByPrimaryKeySelective(tDayOff);
        }
        else
        {
            tDayOffMapper.insertSelective(tDayOff);
        }
        return "更新成功";
    }

    public String DeleteTDayoff(TDayOff tDayOff){
        tDayOffMapper.deleteByPrimaryKey(tDayOff.getId());
        return "删除成功";
    }
}
