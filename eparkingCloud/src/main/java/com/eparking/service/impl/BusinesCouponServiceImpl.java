package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TBusine;
import com.common.entity.eparkingCloud.TBusinesCoupon;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TBusinesCouponInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.BusinesCouponService;
import com.common.util.DateUtil;
import com.common.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusinesCouponServiceImpl implements BusinesCouponService {

/*    @Autowired
    private CustomizeMapper customizeMapper;*/

    @Autowired
    private BusineService busineService;

/*    @Autowired
    private TBusinesCouponMapper tBusinesCouponMapper;

    @Autowired
    private TBusineMapper tBusineMapper;*/
    @Autowired
    private CustomizeInsideService customizeInsideService;
    @Autowired
    private TBusinesCouponInsideService tBusinesCouponInsideService;


    @Override
    public List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year) {
//        List<Map> tCouponPayMonth=customizeMapper.getCouponPayMonth(tBusinesCoupon,year);
        List<Map> tCouponPayMonth=customizeInsideService.getCouponPayMonth(tBusinesCoupon, year);
        return tCouponPayMonth;
    }
    @Override
    public List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year,String month) {
//        List<Map> tCouponPayDay=customizeMapper.getCouponPayDay(tBusinesCoupon,year,month);
        List<Map> tCouponPayDay=customizeInsideService.getCouponPayDay(tBusinesCoupon,year,month);
        return tCouponPayDay;
    }

    @Override
    public TBusinesCoupon setTBusinesCoupon() {
        TBusinesCoupon tBusinesCoupon = new TBusinesCoupon();
        String account =  SessionUtil.getUser().getUserAccout();
        TBusine tBusine = busineService.selectByAccount(account);
        tBusinesCoupon.setBusineId(tBusine.getId());
        tBusinesCoupon.setCompanyId(SessionUtil.getUser().getCompanyId());
        tBusinesCoupon.setParkId(SessionUtil.getParkId());
        return tBusinesCoupon;
    }

    @Override
    public List<Map> returnCouponPayDay() {
        TBusinesCoupon tBusinesCoupon = setTBusinesCoupon();
        String date = DateUtil.nowTime();
        String month = date.substring(5,7);
        String year = date.substring(0,4);
        Integer dayNum=DateUtil.getDayByMonth(DateUtil.today());

        List<Map> couponPay = getCouponPayDay(tBusinesCoupon,year,month);

            List<String> DayList = new ArrayList<>();
            for (Integer i=1;i<=dayNum;i++) {
                if (i < 10) {
                    DayList.add("0" + i);
                }else {
                    DayList.add(i.toString());
                }
            }
        List<Map> resultCouponPay = new ArrayList<>();
        int i=0;
        for(String m : DayList){
            Map map = new HashMap();
            map.put("date",m);
            map.put("value",0.00);
            resultCouponPay.add(map);
            for(Map map1 : couponPay){
                if (m.equals(map1.get("date"))){
                    Map map2 = new HashMap();
                    map2.put("date",m);
                    map2.put("value",map1.get("value"));
                    resultCouponPay.remove(i);
                    resultCouponPay.add(map2);
                }
            }
            i++;
        }
        return resultCouponPay;
    }

    @Override
    public List<Map> returnCouponPayMonth() {
        TBusinesCoupon tBusinesCoupon = setTBusinesCoupon();
        String date = DateUtil.nowTime();
        String year = date.substring(1,4);
        List<Map> couponPay = getCouponPayMonth(tBusinesCoupon,year);
        List<String> monthList = new ArrayList<>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12"));
        List<Map> resultCouponPay = new ArrayList<>();
        int i=0;
        for(String m : monthList) {
            Map map = new HashMap();
            map.put("date", m);
            map.put("value", 0.00);
            resultCouponPay.add(map);
            for (Map map1 : couponPay) {
                if (m.equals(map1.get("date"))) {
                    Map map2 = new HashMap();
                    map2.put("date", m);
                    map2.put("value", map1.get("value"));
                    resultCouponPay.remove(i);
                    resultCouponPay.add(map2);
                }
            }
            i++;
        }
        return resultCouponPay;
    }

    @Override
    public Double couponSumPay() {
        TBusinesCoupon tBusinesCoupon = setTBusinesCoupon();
//        Double couponSumPay = customizeMapper.getCouponSumPay(tBusinesCoupon);
        Double couponSumPay = customizeInsideService.getCouponSumPay(tBusinesCoupon);
        return couponSumPay;
    }

    @Override
    public Double couponNeedPay() {
        TBusinesCoupon tBusinesCoupon = setTBusinesCoupon();
//        Double couponNeedPay = customizeMapper.getCouponNeedPay(tBusinesCoupon);
        Double couponNeedPay = customizeInsideService.getCouponNeedPay(tBusinesCoupon);
        return couponNeedPay;
    }

    @Override
    public Double couponBalance() {
        Double coupon = couponSumPay();
        Double total = couponNeedPay();
        Double balance = total - coupon;
//        TBusine tBusine = tBusineMapper.selectByPrimaryKey(businesCouponService.setTBusinesCoupon().getBusineId());
        TBusine tBusine = busineService.selectByPrimaryKey(setTBusinesCoupon().getBusineId());
        tBusine.setBalance(balance);
//        tBusineMapper.updateByPrimaryKeySelective(tBusine);
        busineService.UpdateBusine(tBusine);
        return balance;
    }

    @Override
    public List getBusinesCouponByCarplate(TParkInOut tParkInOut) {
/*        TBusinesCoupon tBusinesCoupon = new TBusinesCoupon();
        tBusinesCoupon.setParkId(tParkInOut.getParkId());
        tBusinesCoupon.setCarPlate(tParkInOut.getInCarPlate());
        tBusinesCoupon.setInTime(tParkInOut.getInTime());
        List<TBusinesCoupon> tBusinesCoupons = tBusinesCouponMapper.selectByExample(getTBusinesCouponCriteria(tBusinesCoupon));
        List list = new ArrayList();
        for (TBusinesCoupon tBusinesCouponNew : tBusinesCoupons){
            Map map = StringUtil.object2Map(tBusinesCouponNew);
            TBusine tBusine = busineService.selectByPrimaryKey(tBusinesCouponNew.getBusineId());
            map.put("busineName",tBusine.getBusineName());
            map.put("busineType",tBusine.getDiscountType());
            list.add(map);
        }
        return list;*/
        List list= tBusinesCouponInsideService.getBusinesCouponByCarplate(tParkInOut);
        return list;
    }

/*    public TBusinesCouponCriteria getTBusinesCouponCriteria(TBusinesCoupon tBusinesCoupon){
        TBusinesCouponCriteria tBusinesCouponCriteria = new TBusinesCouponCriteria();
        TBusinesCouponCriteria.Criteria criteria = tBusinesCouponCriteria.createCriteria();
        if (tBusinesCoupon!=null){
            if (tBusinesCoupon.getParkId()!=null){
                criteria.andParkIdEqualTo(tBusinesCoupon.getParkId());
            }
            if (tBusinesCoupon.getCarPlate()!=null){
                criteria.andCarPlateEqualTo(tBusinesCoupon.getCarPlate());
            }if (tBusinesCoupon.getInTime()!=null){
                criteria.andInTimeEqualTo(tBusinesCoupon.getInTime());
            }
        }
        return tBusinesCouponCriteria;
    }*/

}
