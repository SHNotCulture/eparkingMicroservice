package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.THoliday;
import com.common.entity.eparkingCloud.THolidayCriteria;
import com.eparkingdb.dao.THolidayMapper;
import com.eparkingdb.service.THolidayService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class THolidayServiceImpl implements THolidayService {
    private static final Logger logger = LoggerFactory.getLogger(THolidayServiceImpl.class);
    @Autowired
    private THolidayMapper tHolidayMapper;

    private THolidayCriteria setCriteria(THoliday tHoliday) {
        THolidayCriteria tHolidayCriteria = new THolidayCriteria();
        if (tHoliday != null) {
            THolidayCriteria.Criteria criteria = tHolidayCriteria.createCriteria();
            if (tHoliday.getParkId() != null) {
                criteria.andParkIdEqualTo(tHoliday.getParkId());
            }
        }
        return tHolidayCriteria;
    }

    private Integer getCount(THoliday tHoliday) {
        Integer total = (int) tHolidayMapper.countByExample(setCriteria(tHoliday));
        return total;
    }

    @Override
    public PageBean<THoliday> getTHolidaybyPage(THoliday tHoliday, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<THoliday> tHolidayList = getTHoliday(tHoliday);
        Integer countNums = getCount(tHoliday);
        PageBean<THoliday> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tHolidayList);
        return pageData;
    }

    @Override
    public List<THoliday> getTHoliday(THoliday tHoliday) {
        List<THoliday> tHolidayList = tHolidayMapper.selectByExample(setCriteria(tHoliday));
        return tHolidayList;
    }

    @Override
    public String UpdateTHoliday(THoliday tHoliday) {
//        logger.info(tHoliday.toString());
        if (tHoliday.getId() != null) {
            tHolidayMapper.updateByPrimaryKeySelective(tHoliday);
        } else {
            tHolidayMapper.insertSelective(tHoliday);
        }
        return "更新成功";
    }

    @Override
    public String DeleteTHoliday(THoliday tHoliday) {
        tHolidayMapper.deleteByPrimaryKey(tHoliday.getId());
        return "删除成功";
    }
}
