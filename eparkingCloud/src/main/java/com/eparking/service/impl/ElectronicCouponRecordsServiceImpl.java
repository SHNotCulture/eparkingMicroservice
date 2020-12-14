package com.eparking.service.impl;


import com.common.entity.eparkingCloud.TTicketCoupon;
import com.eparking.insideService.TTicketCouponInsideService;
import com.eparking.service.ElectronicCouponRecordsService;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**@Author xiexuanran
 * @Date  2019/4/12
 */
@Service
public class ElectronicCouponRecordsServiceImpl implements ElectronicCouponRecordsService {

/*    @Autowired
    private TTicketCouponMapper tTicketCouponMapper;*/

        @Autowired
        private TTicketCouponInsideService tTicketCouponInsideService;

    /* 查询电子优惠券记录
     * 查询条件：车牌，电子券编码，车场名称，电子券名称，进场时间，出场时间
     */
/*    public List<TTicketCoupon> getTElectronicTicket(TTicketCoupon tTicketCoupon,String InTimeBegin,String InTimeEnd,String OutTimeBegin,String OutTimeEnd){
        TTicketCouponCriteria tTicketCouponCriteria = new TTicketCouponCriteria();
        TTicketCouponCriteria.Criteria criteria = tTicketCouponCriteria.createCriteria();
        StringBuffer stringBuffer = new StringBuffer();
        if (tTicketCoupon!=null){
            if (tTicketCoupon.getParkId()!=null ){        //车场ID
                criteria.andParkIdEqualTo(tTicketCoupon.getParkId());
            }
            if (tTicketCoupon.getCarplate()!=null && tTicketCoupon.getCarplate()!=""){        //车牌
                criteria.andCarplateLike(tTicketCoupon.getCarplate());
            }
            if (tTicketCoupon.getCouponCode()!=null && tTicketCoupon.getCouponCode()!=""){        //电子券编码
                criteria.andCouponCodeEqualTo(tTicketCoupon.getCouponCode());
            }
            if (tTicketCoupon.getParkName()!=null && tTicketCoupon.getParkName()!=""){        //车场名称
                criteria.andParkNameEqualTo(tTicketCoupon.getParkName());
            }
            if (tTicketCoupon.getTicketName()!=null && tTicketCoupon.getTicketName()!=""){        //电子券名称
                criteria.andTicketNameEqualTo(tTicketCoupon.getTicketName());
            }
        }
        if (InTimeBegin!=null && InTimeBegin!=""  && InTimeEnd!="" && InTimeEnd!=null ){        //进场时间
            criteria.andInTimeBetween(InTimeBegin,InTimeEnd);
        }
        if (OutTimeBegin!=null  && OutTimeBegin!="" && OutTimeEnd!="" && OutTimeEnd!=null){     //出场时间
            criteria.andOutTimeBetween(OutTimeBegin,OutTimeEnd);
        }
        return tTicketCouponMapper.selectByExample(tTicketCouponCriteria);
    }*/

    @Override
    public PageBean<TTicketCoupon> getTElectronicTicketbyPage(TTicketCoupon tTicketCoupon, String inTimeBegin, String inTimeEnd, String outTimeBegin, String outTimeEnd, Integer page, Integer limit) {
        return tTicketCouponInsideService.getTTicketCouponbyPage(tTicketCoupon, inTimeBegin, inTimeEnd, outTimeBegin, outTimeEnd, page, limit);
    }

}
