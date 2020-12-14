package com.eparking.service.impl;


import com.common.entity.eparkingCloud.Histogram;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TCustomize;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.service.HomepageService;
import com.eparking.service.MonthlyCarService;
import com.common.util.Common;
import com.common.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HomepageServiceImpl
 * @Author jin
 * @Date 2018/9/25 14:32
 **/
@Service
public class HomepageServiceImpl implements HomepageService {
    @Autowired
    private MonthlyCarService monthlyCarService;
    @Autowired
    private CustomizeInsideService customizeInsideService;


    @Override
    public String findPresentCarNum() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        JSONObject UserJson = JSONObject.fromObject(session.getAttribute("User").toString());
        String indexParkId = UserJson.getString("parkIds");
        List<Integer> parkIDlist = new ArrayList<Integer>();
        int i = indexParkId.indexOf(',');
        while (i >= 0) {
            parkIDlist.add(Integer.valueOf(indexParkId.substring(0, i)));
            indexParkId = indexParkId.substring(i + 1, indexParkId.length());
            i = indexParkId.indexOf(",");
        }
        parkIDlist.add(Integer.valueOf(indexParkId));
/*        TParkCarCriteria tParkCarCriteria = new TParkCarCriteria();
        TParkCarCriteria.Criteria criteria = tParkCarCriteria.createCriteria();
        criteria.andParkIdIn(parkIDlist);
        tParkCarMapper.countByExample(tParkCarCriteria);*/
        monthlyCarService.getPresentCarNum(parkIDlist);
       /* StringBuffer strSQL = new StringBuffer();
        for (int k=0;k<parkIDlist.size();k++){
            strSQL.append("");
        }*/
        return null;
    }

    @Override
    public TCustomize selectNumMsg() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String indexParkId = session.getAttribute(Common.ParkId).toString();
//        TCustomize tCustomize = customizeMapper.selectMainNum(indexParkId);
        TCustomize tCustomize = customizeInsideService.selectMainNum(indexParkId);
        return tCustomize;
    }

    @Override
    public Map selectHistogram(String year, String type, String parkid) {
//        List<Histogram> histograms = customizeMapper.selectHistogram(parkid,year,type);
        List<Histogram> histograms = JsonUtil.json2List(customizeInsideService.selectHistogram(parkid, year, type), Histogram.class);
        StringBuffer str = new StringBuffer();
        for (int k = 0; k < histograms.size(); k++) {
            str.append("m" + histograms.get(k).getMonth() + "m");
        }
        for (int i = 1; i <= 12; i++) {
            Histogram histogram = new Histogram();
            if (!str.toString().contains("m" + i + "m")) {
                histogram.setMonth(i + "");
                histogram.setNums(0.00);
                histograms.add(histogram);
            }
        }
        Map map = new HashMap();
        for (int m = 0; m < histograms.size(); m++) {
            map.put(histograms.get(m).getMonth(), histograms.get(m).getNums());
        }
        return map;
    }

    @Override
    public Map selectCompanyPark() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String indexParkId = session.getAttribute(Common.ParkId).toString();
//        Map map = customizeMapper.selectCompanyPark(indexParkId);
        Map map = JsonUtil.json2Map(customizeInsideService.selectCompanyPark(indexParkId));
        return map;
    }

    @Override
    public Map updateCompanyPark(TCompanyPark tCompanyPark) {
        tCompanyPark.getTotalParkingSpace();
        return null;
    }

    @Override
    public Integer selectTotalNum(String parkid) {
//        return customizeMapper.selectTotalNum(parkid);
        return customizeInsideService.selectTotalNum(parkid);
    }

    @Override
    public Map selectPayByType(String parkid) {
//        return customizeMapper.selectPayByType(parkid);
        return JsonUtil.json2Map(customizeInsideService.selectPayByType(parkid));
    }

    /**
     * 查询近7日车流量
     */
    @Override
    public List<Map> selectSevenDays(String parkid) {
//        List<Map> maps = customizeMapper.selectSevenDays(parkid);
        List<Map> maps = JsonUtil.json2List(customizeInsideService.selectSevenDays(parkid),Map.class);
        Map<String, String> mapdate = new HashMap();
        Map<String, String> mapnum = new HashMap();
        List<Map> maplist = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            mapdate.put(i + 1 + "", maps.get(i).get("date").toString());
            mapnum.put(i + 1 + "", maps.get(i).get("num").toString());
        }
        maplist.add(mapdate);
        maplist.add(mapnum);
//        maplist.put("date",mapdate);
//        maplist.put("num",mapnum);
        return maplist;
    }

    @Override
    public Map selectePayType(String parkid) {
//        return customizeMapper.selectePayType(parkid);
        return JsonUtil.json2Map(customizeInsideService.selectePayType(parkid));
    }

    @Override
    public Map selectCarNum(String parkid) {
//        return customizeMapper.selectCarNum(parkid);
        return JsonUtil.json2Map(customizeInsideService.selectCarNum(parkid));
    }

    /**
     * 查询首页大部分数据
     *
     * @param parkid
     * @return
     */
    @Override
    public Map selectMainAllNum(String parkid) {
//        return customizeMapper.selectMainAllNum(parkid);
        return JsonUtil.json2Map(customizeInsideService.selectMainAllNum(parkid));
    }


}
