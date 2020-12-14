package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.ZtreeRsp;
import com.eparking.insideService.TCompanyParkInsideService;
import com.eparking.service.CarParkService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 14:482018-9-14
 * @Modified By:
 */
@Service
public class CarParkServiceImpl implements CarParkService {
    private static final Logger logger = LoggerFactory.getLogger(CarParkServiceImpl.class);
    @Autowired
    private TCompanyParkInsideService tCompanyParkInsideService;



    /**
     * 查询车场信息(分页)
     *
     * @param tCompanyPark
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TCompanyPark> getCarParkbyPage(TCompanyPark tCompanyPark, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit, "id desc");
        List<TCompanyPark> tCompanyParkList = getCarPark(tCompanyPark);
        Integer countNums = getCount(tCompanyPark);
        PageBean<TCompanyPark> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCompanyParkList);
        return pageData;*/
        return tCompanyParkInsideService.getTCompanyParkbyPage(tCompanyPark, page, limit);
    }

    /**
     * 查询车场信息
     *
     * @return
     */
    public List<TCompanyPark> getCarPark(TCompanyPark tCompanyPark) {
//        List<TCompanyPark> tCompanyParkList = tCompanyParkMapper.selectByExample(setCriteria(tCompanyPark));
        List<TCompanyPark> tCompanyParkList = tCompanyParkInsideService.getTCompanyPark(tCompanyPark);
        return tCompanyParkList;
    }


    /**
     * 根据数组查询对应车场信息
     *
     * @param ids
     * @return
     */
    public List<TCompanyPark> getCarParkbyIDIn(String ids) {
        //logger.info("ids:"+ids);
        //查询对应车场信息
/*
        TCompanyParkCriteria tCompanyParkCriteria = new TCompanyParkCriteria();
        TCompanyParkCriteria.Criteria criteria = tCompanyParkCriteria.createCriteria();
        criteria.andIdIn(ids);
        List<TCompanyPark> tCompanyParkList = tCompanyParkMapper.selectByExample(tCompanyParkCriteria);*/
        List<TCompanyPark> tCompanyParkList = tCompanyParkInsideService.getCarParkbyIDIn(ids);
        return tCompanyParkList;
    }

    /**
     * 查询车场信息Ztree
     *
     * @return
     */
    public List<ZtreeRsp> getCarParkZtree(TCompanyPark tCompanyParkSelect) {
        List<TCompanyPark> tCompanyParkList = getCarPark(tCompanyParkSelect);
        List<ZtreeRsp> ztreeRspList = new ArrayList<ZtreeRsp>();
        for (TCompanyPark tCompanyPark : tCompanyParkList) {
            ZtreeRsp ztreeRsp = new ZtreeRsp();
            ztreeRsp.setId(tCompanyPark.getId());
            ztreeRsp.setpId(0);
            ztreeRsp.setName(tCompanyPark.getParkName());
            ztreeRspList.add(ztreeRsp);
        }
        return ztreeRspList;
    }

    /**
     * 更新车场信息
     *
     * @param tCompanyPark
     * @return
     */
    public String UpdateCarPark(TCompanyPark tCompanyPark) {
/*        String msg ="";
        logger.info(tCompanyPark.toString());
        if (tCompanyPark.getId() != null) {
            tCompanyParkMapper.updateByPrimaryKeySelective(tCompanyPark);
            msg="更新成功";
        } else {
            tCompanyPark.setApiKey(StringUtil.randomString(32));
            tCompanyPark.setAppId("0");
            tCompanyPark.setAppSecret("0");
            tCompanyPark.setMchId("0");
            tCompanyPark.setMchKey("0");
            tCompanyParkMapper.insertSelective(tCompanyPark);
            Integer id = getCarPark(tCompanyPark).get(0).getId();
            customizeMapper.parkTiggerForInOut(id.toString());
            customizeMapper.parkTiggerForTask(id.toString());
            msg="新增成功";
        }
        return msg;*/
        String msg="";
        TCompanyPark tCompanyParkSel = new TCompanyPark();
        tCompanyParkSel.setCompanyId(tCompanyPark.getCompanyId());
        tCompanyParkSel.setParkName(tCompanyPark.getParkName());
        List<TCompanyPark> tCompanyParkListResult = getCarPark(tCompanyParkSel);
        if(tCompanyPark.getId()==null){
            //新增操作
            if(tCompanyParkListResult.size()>0){
                msg = "该物业已存在相同的停车场名称";
            }else{
                msg = tCompanyParkInsideService.UpdateTCompanyPark(tCompanyPark);
            }
        }else{
            //编辑操作
            if(tCompanyParkListResult.size()>0){
                for(TCompanyPark tCompanyParkOne:tCompanyParkListResult){
                    if(tCompanyParkOne.getId()!=tCompanyPark.getId()){
                        msg = "该物业已存在相同的停车场名称";
                        break;
                    }
                }
            }
            if(msg.equals("")){
                msg = tCompanyParkInsideService.UpdateTCompanyPark(tCompanyPark);
            }
        }
        return msg;
    }

    /**
     * 删除车场信息
     *
     * @param id
     * @return
     */
    public String DeleteCarPark(Integer id) {
//        tCompanyParkMapper.deleteByPrimaryKey(id);
        TCompanyPark tCompanyPark = new TCompanyPark();
        tCompanyPark.setId(id);
        return tCompanyParkInsideService.DeleteTCompanyPark(tCompanyPark);
    }

    @Override
    public TCompanyPark getCarParkbyId(TCompanyPark tCompanyPark) {
        return tCompanyParkInsideService.getTCompanyParkByID(tCompanyPark);
    }
}
