package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TBusinesCouponCriteria;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparkingdb.dao.TBusinesCouponMapper;
import com.eparkingdb.service.TBusineService;
import com.eparkingdb.service.TBusinesCouponService;
import com.common.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 谢轩然
 * @Description: TBusinesCouponService接口实现类
 * @date 2020/04/09 11:57
 */
@Service
public class TBusinesCouponServiceImpl implements TBusinesCouponService {

    private static final Logger logger = LoggerFactory.getLogger(TBusinesCouponServiceImpl.class);
    @Autowired
    private TBusinesCouponMapper tBusinesCouponMapper;
    @Autowired
    private TBusineService tBusineService;

    /**
     * 设置查询条件
     *
     * @param tBusinesCoupon
     * @return
     */
    private TBusinesCouponCriteria setCriteria(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd) {
        TBusinesCouponCriteria tBusinesCouponCriteria = new TBusinesCouponCriteria();
        TBusinesCouponCriteria.Criteria criteria = tBusinesCouponCriteria.createCriteria();
        if (tBusinesCoupon != null) {
            if (tBusinesCoupon.getId() != null) {
                criteria.andIdEqualTo(tBusinesCoupon.getId());
            }
            if (tBusinesCoupon.getCarPlate() != null && tBusinesCoupon.getCarPlate() != "") {        //车牌
                criteria.andCarPlateLike("%" + tBusinesCoupon.getCarPlate() + "%");
            }
            if (tBusinesCoupon.getBusineId() != null) {
                criteria.aneparkingdbusineIdEqualTo(tBusinesCoupon.getBusineId());
            }
            if (tBusinesCoupon.getCompanyId() != null) {
                criteria.andCompanyIdEqualTo(tBusinesCoupon.getCompanyId());
            }
            if (tBusinesCoupon.getParkId() != null) {
                criteria.andParkIdEqualTo(tBusinesCoupon.getParkId());
            }
            if (tBusinesCoupon.getCouponCode() != null && tBusinesCoupon.getCouponCode() != "") {
                criteria.andCouponCodeEqualTo(tBusinesCoupon.getCouponCode());
            }
            if (tBusinesCoupon.getCouponPay() != null) {
                criteria.andCouponPayEqualTo(tBusinesCoupon.getCouponPay());
            }
            if (tBusinesCoupon.getParkName() != null && tBusinesCoupon.getParkName() != "") {
                criteria.andParkNameEqualTo(tBusinesCoupon.getParkName());
            }
            if (tBusinesCoupon.getInTime() != null && tBusinesCoupon.getInTime() != "") {
                criteria.andInTimeEqualTo(tBusinesCoupon.getInTime());
            }
            if (tBusinesCoupon.getOutTime() != null && tBusinesCoupon.getOutTime() != "") {
                criteria.andOutTimeEqualTo(tBusinesCoupon.getOutTime());
            }
            if (tBusinesCoupon.getDerateType() != null) {
                criteria.andDerateTypeEqualTo(tBusinesCoupon.getDerateType());
            }
            if (tBusinesCoupon.getDisable() != null) {
                criteria.andDisableEqualTo(tBusinesCoupon.getDisable());
            }
            if (tBusinesCoupon.getStatus() != null && tBusinesCoupon.getStatus() != "") {
                criteria.andStatusEqualTo(tBusinesCoupon.getStatus());
            }
        }
        if (couponTimeBegin != null && couponTimeBegin != "" && couponTimeEnd != "" && couponTimeEnd != null) {
            criteria.andInTimeBetween(couponTimeBegin, couponTimeEnd);
        }

        return tBusinesCouponCriteria;
    }

    private TBusinesCouponCriteria setCarplateCriteria(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd) {
        TBusinesCouponCriteria tBusinesCouponCriteria = new TBusinesCouponCriteria();
        TBusinesCouponCriteria.Criteria criteria = tBusinesCouponCriteria.createCriteria();
        if (tBusinesCoupon != null) {
            if (tBusinesCoupon.getId() != null) {
                criteria.andIdEqualTo(tBusinesCoupon.getId());
            }
            if (tBusinesCoupon.getCarPlate() != null && tBusinesCoupon.getCarPlate() != "") {        //车牌
                criteria.andCarPlateLike("%" + tBusinesCoupon.getCarPlate() + "%");
            }
            if (tBusinesCoupon.getParkId() != null) {
                criteria.andParkIdEqualTo(tBusinesCoupon.getParkId());
            }
            if (tBusinesCoupon.getInTime() != null && tBusinesCoupon.getInTime() != "") {
                criteria.andInTimeEqualTo(tBusinesCoupon.getInTime());
            }
        }
        if (couponTimeBegin != null && couponTimeBegin != "" && couponTimeEnd != "" && couponTimeEnd != null) {
            criteria.andInTimeBetween(couponTimeBegin, couponTimeEnd);
        }

        return tBusinesCouponCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tBusinesCoupon
     * @return
     */
    private Integer getCount(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd) {
        Integer total = (int) tBusinesCouponMapper.countByExample(setCriteria(tBusinesCoupon, couponTimeBegin, couponTimeEnd));
        return total;
    }

    /**
     * 查询tBusinesCoupon(分页)
     *
     * @param tBusinesCoupon
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TBusinesCoupon> getTBusinesCouponbyPage(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "create_date desc");
        List<TBusinesCoupon> tBusinesCoupons = getTBusinesCoupon(tBusinesCoupon, couponTimeBegin, couponTimeEnd);
        Integer countNums = getCount(tBusinesCoupon, couponTimeBegin, couponTimeEnd);
        PageBean<TBusinesCoupon> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tBusinesCoupons);
        return pageData;
    }

    /**
     * 查询tBusinesCoupon
     *
     * @param tBusinesCoupon
     * @return
     */
    public List<TBusinesCoupon> getTBusinesCoupon(TBusinesCoupon tBusinesCoupon, String couponTimeBegin, String couponTimeEnd) {
        List<TBusinesCoupon> tBusinesCoupons = tBusinesCouponMapper.selectByExample(setCriteria(tBusinesCoupon, couponTimeBegin, couponTimeEnd));
        return tBusinesCoupons;
    }

    /**
     * 更新tBusinesCoupon
     *
     * @param tBusinesCoupon
     * @return
     */
    public String UpdateTBusinesCoupon(TBusinesCoupon tBusinesCoupon) {
        String msg = "";
        try {
            if (tBusinesCoupon.getId() != null) {
                tBusinesCouponMapper.updateByPrimaryKeySelective(tBusinesCoupon);
                msg = "更新TBusinesCoupon成功";
            } else {
                tBusinesCouponMapper.insertSelective(tBusinesCoupon);
                msg = "新建TBusinesCoupon成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
     * 删除tBusinesCoupon
     *
     * @param tBusinesCoupon
     * @return
     */
    public String DeleteTBusinesCoupon(TBusinesCoupon tBusinesCoupon) {
        String msg = "";
        if (tBusinesCoupon.getId() != null) {
            tBusinesCouponMapper.deleteByPrimaryKey(tBusinesCoupon.getId());
            msg = "删除TBusinesCoupon成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tBusinesCoupon
     *
     * @param id
     * @return
     */
    public TBusinesCoupon getTBusinesCouponByID(Integer id) {
        return tBusinesCouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getBusinesCouponByCarplate(TParkInOut tParkInOut) {
        TBusinesCoupon tBusinesCoupon = new TBusinesCoupon();
        tBusinesCoupon.setParkId(tParkInOut.getParkId());
        tBusinesCoupon.setCarPlate(tParkInOut.getInCarPlate());
        tBusinesCoupon.setInTime(tParkInOut.getInTime());
//        logger.info("tBC: " + JsonUtil.beanToJson(tBusinesCoupon));
        List<TBusinesCoupon> tBusinesCoupons = tBusinesCouponMapper.selectByExample(setCarplateCriteria(tBusinesCoupon, null, null));
        List list = new ArrayList();
        if (tBusinesCoupons.size() > 0) {
            for (TBusinesCoupon tBusinesCouponNew : tBusinesCoupons) {
                Map map = StringUtil.object2Map(tBusinesCouponNew);
                TBusine tBusine = tBusineService.getTBusineByID(tBusinesCouponNew.getBusineId());
                if (tBusine != null) {
                    map.put("busineName", tBusine.getBusineName());
                    map.put("busineType", tBusine.getDiscountType());
                    list.add(map);
                }
            }
        } else {
        }

        logger.info(String.valueOf(list));

        return list;

    }
}
