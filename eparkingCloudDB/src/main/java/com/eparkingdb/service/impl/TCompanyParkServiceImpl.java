package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TCompanyParkCriteria;
import com.eparkingdb.dao.CustomizeMapper;
import com.eparkingdb.dao.TCompanyParkMapper;
import com.eparkingdb.service.TCompanyParkService;
import com.common.util.DateUtil;
import com.common.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 谢轩然
 * @Description: TCompanyParkService接口实现类
 * @date 2020/04/09 14:50
 */
@Service
public class TCompanyParkServiceImpl implements TCompanyParkService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyParkServiceImpl.class);
    @Autowired
    private TCompanyParkMapper tCompanyParkMapper;
    @Autowired
    private CustomizeMapper customizeMapper;

    /**
     * 设置查询条件
     *
     * @param tCompanyPark
     * @return
     */
    private TCompanyParkCriteria setCriteria(TCompanyPark tCompanyPark) {
        TCompanyParkCriteria tCompanyParkCriteria = new TCompanyParkCriteria();
        if (tCompanyPark != null) {
            TCompanyParkCriteria.Criteria criteria = tCompanyParkCriteria.createCriteria();
            if (tCompanyPark.getId() != null) {
                criteria.andIdEqualTo(tCompanyPark.getId());
            }
            if (tCompanyPark.getParkName() != null && tCompanyPark.getParkName() != "") {
                criteria.andParkNameLike("%" + tCompanyPark.getParkName() + "%");
            }
            if (tCompanyPark.getCompanyId() != null && tCompanyPark.getCompanyId() != 0) {
                criteria.andCompanyIdEqualTo(tCompanyPark.getCompanyId());
            }
        }
        return tCompanyParkCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tCompanyPark
     * @return
     */
    private Integer getCount(TCompanyPark tCompanyPark) {
        Integer total = (int) tCompanyParkMapper.countByExample(setCriteria(tCompanyPark));
        return total;
    }

    /**
     * 查询tCompanyPark(分页)
     *
     * @param tCompanyPark
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TCompanyPark> getTCompanyParkbyPage(TCompanyPark tCompanyPark, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TCompanyPark> tCompanyParks = getTCompanyPark(tCompanyPark);
        Integer countNums = getCount(tCompanyPark);
        PageBean<TCompanyPark> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCompanyParks);
        return pageData;
    }

    /**
     * 查询tCompanyPark
     *
     * @param tCompanyPark
     * @return
     */
    public List<TCompanyPark> getTCompanyPark(TCompanyPark tCompanyPark) {
        List<TCompanyPark> tCompanyParks = tCompanyParkMapper.selectByExample(setCriteria(tCompanyPark));
        return tCompanyParks;
    }

    /**
     * 更新tCompanyPark
     *
     * @param tCompanyPark
     * @return
     */
    @Transactional
    public String UpdateTCompanyPark(TCompanyPark tCompanyPark) {
        String msg = "";
//        logger.info(tCompanyPark.toString());
        if (tCompanyPark.getId() != null) {
            tCompanyParkMapper.updateByPrimaryKeySelective(tCompanyPark);
            msg = "更新成功";
        } else {
            tCompanyPark.setApiKey(StringUtil.randomString(32));
            tCompanyPark.setAppId("0");
            tCompanyPark.setAppSecret("0");
            tCompanyPark.setMchId("0");
            tCompanyPark.setMchKey("0");
            tCompanyParkMapper.insertSelective(tCompanyPark);
            Integer id = getTCompanyPark(tCompanyPark).get(0).getId();
            customizeMapper.parkTiggerForInOut1(id.toString());
            customizeMapper.parkTiggerForInOut2(id.toString());
            customizeMapper.parkTiggerForTask1(id.toString());
            customizeMapper.parkTiggerForTask2(id.toString());
            msg = "新增成功";
        }
        return msg;
/*            String msg="";
            try{
            if(tCompanyPark.getId()!=null){
                tCompanyPark.setApiKey(StringUtil.randomString(32));
                tCompanyPark.setAppId("0");
                tCompanyPark.setAppSecret("0");
                tCompanyPark.setMchId("0");
                tCompanyPark.setMchKey("0");
                tCompanyParkMapper.insertSelective(tCompanyPark);
                Integer id=getTCompanyPark(tCompanyPark).get(0).getId();
                customizeMapper.parkTiggerForInOut(id.toString());
                customizeMapper.parkTiggerForTask(id.toString());
                msg="更新成功";
            }
            else
            {
            tCompanyParkMapper.insertSelective(tCompanyPark);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;*/
    }


    /**
     * 入场数据更新
     * 0点时将数据清零
     * 每来一条入场记录，判断入场记录的入场时间，如果小于当前时间（只判断时分秒），则累加，否则放弃(跨天才送上来的记录)
     */
    @Override
    public String updateInCarsToday(Integer parkId, int number, String inTime) {
        TCompanyPark parkinfo = tCompanyParkMapper.selectByPrimaryKey(parkId);
        if(inTime.compareTo(DateUtil.nowTimeHHmmss()) < 0) {
            if (parkinfo.getIncarCountToday() == null) parkinfo.setIncarCountToday(0);
            parkinfo.setIncarCountToday(parkinfo.getIncarCountToday() + number);
        }
        if (tCompanyParkMapper.updateByPrimaryKey(parkinfo) > 0) {
            return "更新成功";
        }
        return "更新失败";

    }

    /**
     * 删除tCompanyPark
     *
     * @param tCompanyPark
     * @return
     */
    public String DeleteTCompanyPark(TCompanyPark tCompanyPark) {
        String msg = "";
        if (tCompanyPark.getId() != null) {
            tCompanyParkMapper.deleteByPrimaryKey(tCompanyPark.getId());
            msg = "删除成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tCompanyPark
     *
     * @param id
     * @return
     */
    public TCompanyPark getTCompanyParkByID(Integer id) {
        return tCompanyParkMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据数组查询对应车场信息
     *
     * @param ids
     * @return
     */
    public List<TCompanyPark> getCarParkbyIDIn(String ids) {
        List<Integer> idList = StringUtil.stringList2List(ids.split(","));
        TCompanyParkCriteria tCompanyParkCriteria = new TCompanyParkCriteria();
        TCompanyParkCriteria.Criteria criteria = tCompanyParkCriteria.createCriteria();
        criteria.andIdIn(idList);
        List<TCompanyPark> tCompanyParkList = tCompanyParkMapper.selectByExample(tCompanyParkCriteria);
        return tCompanyParkList;
    }
}
