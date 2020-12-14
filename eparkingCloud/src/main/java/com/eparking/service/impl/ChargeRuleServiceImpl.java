package com.eparking.service.impl;

import com.common.entity.eparkingCloud.TChargeRule;
import com.common.entity.eparkingCloud.YuncsisResult;
import com.eparking.insideService.TChargeRuleInsideService;
import com.eparking.service.ChargeRuleService;
import com.eparking.service.YuncsisApiService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:082018-9-14
 * @Modified By:
 */
@Service
public class ChargeRuleServiceImpl implements ChargeRuleService {
    private  static final Logger logger= LoggerFactory.getLogger(ChargeRuleServiceImpl.class);
    @Autowired
    private TChargeRuleInsideService tChargeRuleInsideService;
    @Autowired
    private YuncsisApiService yuncsisApiService;
    /**
     *查询临停缴费信息(分页)
     * @param tChargeRule
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TChargeRule> getChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"id desc");
        List<TChargeRule> tChargeRuleList=getChargeRule(tChargeRule);
        Integer countNums =getCount(tChargeRule);
        PageBean<TChargeRule> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tChargeRuleList);
        return pageData;*/
        return tChargeRuleInsideService.getTChargeRulebyPage(tChargeRule, page, limit);
    }
    /**
     * 查询临停缴费信息
     * @return
     */
    public List<TChargeRule> getChargeRule(TChargeRule tChargeRule){
/*        logger.info("tChargeRule:"+tChargeRule);
        //查询全部临停缴费信息
        List<TChargeRule> tChargeRuleList=tChargeRuleMapper.selectByExample(setCriteria(tChargeRule));
        return tChargeRuleList;*/
        return tChargeRuleInsideService.getTChargeRule(tChargeRule);
    }


    /**
     * 更新临停缴费信息
     * @param tChargeRule
     * @return
     */
    public String UpdateChargeRule(TChargeRule tChargeRule)
    {
/*        logger.info(tChargeRule.toString());
        if(tChargeRule.getId()!=null){
            tChargeRuleMapper.updateByPrimaryKeySelective(tChargeRule);
        }
        else
        {
            tChargeRuleMapper.insertSelective(tChargeRule);
        }
        return "更新成功";*/
        String msg = tChargeRuleInsideService.UpdateTChargeRule(tChargeRule);

        if(msg.equals("更新成功")){
            Map map = updateChargerulesMap(tChargeRule,1);
            YuncsisResult yuncsisResult = yuncsisApiService.updateTempchargerules(map);
//            logger.info(JsonUtil.beanToJson(tChargeRule));
//            logger.info(yuncsisResult.getSuccess());
        }else if(msg.equals("新建成功")){
            List<TChargeRule> tChargeRuleList = tChargeRuleInsideService.getTChargeRule(tChargeRule);
            tChargeRule.setId(tChargeRuleList.get(0).getId());
            Map map = updateChargerulesMap(tChargeRule,1);
            YuncsisResult yuncsisResult = yuncsisApiService.updateTempchargerules(map);
//            logger.info(yuncsisResult.getSuccess());
        }
        return msg;
    }

    /**
     * 删除临停缴费信息
     * @param id
     * @return
     */
    public String DeleteChargeRule(Integer id){
/*        tChargeRuleMapper.deleteByPrimaryKey(id);
        return "删除成功";*/

        TChargeRule tChargeRule = new TChargeRule();
        tChargeRule.setId(id);
        String msg = tChargeRuleInsideService.DeleteTChargeRule(tChargeRule);
        if (msg.equals("删除成功")) {
            Map map = updateChargerulesMap(tChargeRule,2);
            YuncsisResult yuncsisResult = yuncsisApiService.updateTempchargerules(map);
//            logger.info(yuncsisResult.getSuccess());
        }
        return msg;
    }

    public Map updateChargerulesMap(TChargeRule tChargeRule,Integer flag){
        Map map = new HashMap();
        map.put("flag",flag);
        map.put("cloudId",tChargeRule.getId());
        map.put("parkId",tChargeRule.getParkId());
        map.put("carTypeId",tChargeRule.getCarTypeId());
        map.put("isHolidayUse",tChargeRule.getIsHolidayUse());
        map.put("timesecStart",tChargeRule.getTimesecStart());
        map.put("timesecEnd",tChargeRule.getTimesecEnd());
        map.put("base0Time",tChargeRule.getFreeTime());
        map.put("base0TimeFee",tChargeRule.getFreeTimeFee());
        map.put("base1Time",tChargeRule.getBaseTime1());
        map.put("base1TimeFee",tChargeRule.getBaseTime1Fee());
        map.put("base2Time",tChargeRule.getBaseTime2());
        map.put("base2TimeFee",tChargeRule.getBaseTime2Fee());
        map.put("base3Time",tChargeRule.getBaseTime3());
        map.put("base3TimeFee",tChargeRule.getBaseTime3Fee());
        map.put("unit1Sectime",tChargeRule.getUnit1Sectime());
        map.put("unit1Time",tChargeRule.getUnit1Time());
        map.put("unit1TimeFee",tChargeRule.getUnit1TimeFee());
        map.put("unit1Maxfee",tChargeRule.getUnit1Maxfee());
        map.put("unit2Sectime",tChargeRule.getUnit2Sectime());
        map.put("unit2Time",tChargeRule.getUnit2Time());
        map.put("unit2TimeFee",tChargeRule.getUnit2TimeFee());
        map.put("unit2Maxfee",tChargeRule.getUnit2Maxfee());
        map.put("unit3Sectime",tChargeRule.getUnit3Sectime());
        map.put("unit3Time",tChargeRule.getUnit3Time());
        map.put("unit3TimeFee",tChargeRule.getUnit3TimeFee());
        map.put("unit3Maxfee",tChargeRule.getUnit3Maxfee());
        map.put("h24UnitTime",tChargeRule.getH24UnitTime());
        map.put("h24UnitTimeFee",tChargeRule.getH24UnitTimeFee());
        map.put("surpassTime",tChargeRule.getSurpassTime());
        map.put("h24Rule",tChargeRule.getH24Rule());
        map.put("maxDay1Fee",tChargeRule.getMaxDay1Fee());
        map.put("maxDaynFee",tChargeRule.getMaxDaynFee());
        map.put("maxSectimeFee",tChargeRule.getMaxSectimeFee());
        map.put("isMaxSectimeFeeByAdd",tChargeRule.getIsMaxSectimeFeeByAdd());
        map.put("isMax24FeeByAdd",tChargeRule.getIsMax24FeeByAdd());
        map.put("h24CalMode",tChargeRule.getH24CalMode());
        map.put("unitTime",tChargeRule.getUnitTime());
        map.put("unitTimeFee",tChargeRule.getUnitTimeFee());
        map.put("surpassTime",tChargeRule.getSurpassTime());
        return map;
    }

}
