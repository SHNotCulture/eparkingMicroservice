package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.eparking.insideService.TBusinesCouponInsideService;
import com.eparking.service.MerchantCouponRecordService;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**@Author xiexuanran
 * @Date  2019/4/12
 */

@Service
public class MerchantCouponRecordServiceImpl implements MerchantCouponRecordService {

/*    @Autowired
    private TBusinesCouponMapper tBusinesCouponMapper;*/
    @Autowired
    private TBusinesCouponInsideService tBusinesCouponInsideService;

    /**
     * 查询商户消费记录
     * @param tBusinesCoupon
     * @param CouponTimeBegin
     * @param CouponTimeEnd
     * @return
     */
    public List<TBusinesCoupon> FindMerchantCoupon(TBusinesCoupon tBusinesCoupon, String CouponTimeBegin, String CouponTimeEnd){
//        return tBusinesCouponMapper.selectByExample(getTBusinesCouponCriteria(tBusinesCoupon,CouponTimeBegin,CouponTimeEnd));
        return tBusinesCouponInsideService.getTBusinesCoupon(tBusinesCoupon, CouponTimeBegin, CouponTimeEnd);
    }



/*    public TBusinesCouponCriteria getTBusinesCouponCriteria (TBusinesCoupon tBusinesCoupon, String CouponTimeBegin, String CouponTimeEnd){
        TBusinesCouponCriteria tBusinesCouponCriteria = new TBusinesCouponCriteria();
        TBusinesCouponCriteria.Criteria criteria = tBusinesCouponCriteria.createCriteria();
        if(tBusinesCoupon!=null){
            if(tBusinesCoupon.getId()!=null){
                criteria.andIdEqualTo(tBusinesCoupon.getId());
            }
            if (tBusinesCoupon.getCarPlate()!=null && tBusinesCoupon.getCarPlate()!=""){        //车牌
                criteria.andCarPlateLike("%" + tBusinesCoupon.getCarPlate() + "%");
            }
            if (tBusinesCoupon.getBusineId()!=null){
                criteria.aneparkingdbusineIdEqualTo(tBusinesCoupon.getBusineId());
            }
        }
        if (CouponTimeBegin!=null && CouponTimeBegin!=""  && CouponTimeEnd!="" && CouponTimeEnd!=null ){
            criteria.andInTimeBetween(CouponTimeBegin,CouponTimeEnd);
        }
        return  tBusinesCouponCriteria;
    }*/


    /**
     * 商户优惠(分页)
     * @param tBusinesCoupon
     * @param
     * @param page
     * @param limit
     * @return
     */

    @Override
    public PageBean<TBusinesCoupon> FindMerchantCouponbyPage(TBusinesCoupon tBusinesCoupon ,String CouponTimeBegin, String CouponTimeEnd,Integer page,Integer limit){
//        PageHelper.startPage(page, limit,"create_date desc");
//        List<TBusinesCoupon> tBusinesCoupons=FindMerchantCoupon(tBusinesCoupon,CouponTimeBegin,CouponTimeEnd);
//        Integer countNums =this.getCount(tBusinesCoupon,CouponTimeBegin,CouponTimeEnd);
//        PageBean<TBusinesCoupon> pageData = new PageBean<>(page, limit, countNums);
//        pageData.setItems(tBusinesCoupons);
//        return pageData;
        return tBusinesCouponInsideService.getTBusinesCouponbyPage(tBusinesCoupon, CouponTimeBegin, CouponTimeEnd, page, limit);
    }
}
