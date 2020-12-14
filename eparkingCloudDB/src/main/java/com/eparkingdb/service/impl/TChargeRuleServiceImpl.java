package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeRule;
import com.common.entity.eparkingCloud.TChargeRuleCriteria;
import com.eparkingdb.dao.TChargeRuleMapper;
import com.eparkingdb.service.TChargeRuleService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description: TChargeRuleService接口实现类
* @author 谢轩然
* @date 2020/05/15 11:00
*/
@Service
public class TChargeRuleServiceImpl  implements TChargeRuleService {

    private  static final Logger logger= LoggerFactory.getLogger( TChargeRuleServiceImpl.class);
    @Autowired
    private TChargeRuleMapper tChargeRuleMapper;

    /**
    * 设置查询条件
    * @param tChargeRule
    * @return
    */
    private  TChargeRuleCriteria setCriteria(TChargeRule tChargeRule){
        TChargeRuleCriteria tChargeRuleCriteria= new TChargeRuleCriteria();
        if(tChargeRule!=null){
        TChargeRuleCriteria.Criteria criteria=tChargeRuleCriteria.createCriteria();
        if(tChargeRule.getId()!=null){
        criteria.andIdEqualTo(tChargeRule.getId());
        }
            if(tChargeRule.getParkId()!=null){
                criteria.andParkIdEqualTo(tChargeRule.getParkId());
            }
            if(tChargeRule.getBaseTime1()!=null){
                criteria.aneparkingdbaseTime1EqualTo(tChargeRule.getBaseTime1());
            }
            if(tChargeRule.getBaseTime1Fee()!=null){
                criteria.aneparkingdbaseTime1FeeEqualTo(tChargeRule.getBaseTime1Fee());
            }
            if(tChargeRule.getBaseTime2()!=null){
                criteria.aneparkingdbaseTime2EqualTo(tChargeRule.getBaseTime2());
            }
            if(tChargeRule.getBaseTime2Fee()!=null){
                criteria.aneparkingdbaseTime2FeeEqualTo(tChargeRule.getBaseTime2Fee());
            }
            if(tChargeRule.getBaseTime3()!=null){
                criteria.aneparkingdbaseTime3EqualTo(tChargeRule.getBaseTime3());
            }
            if(tChargeRule.getBaseTime3Fee()!=null){
                criteria.aneparkingdbaseTime3FeeEqualTo(tChargeRule.getBaseTime3Fee());
            }
            if(tChargeRule.getBaseTime4()!=null){
                criteria.aneparkingdbaseTime4EqualTo(tChargeRule.getBaseTime4());
            }
            if(tChargeRule.getBaseTime4Fee()!=null){
                criteria.aneparkingdbaseTime4FeeEqualTo(tChargeRule.getBaseTime4Fee());
            }
            if(tChargeRule.getCarTypeId()!=null){
                criteria.andCarTypeIdEqualTo(tChargeRule.getCarTypeId());
            }
            if(tChargeRule.getIsHolidayUse()!=null){
                criteria.andIsHolidayUseEqualTo(tChargeRule.getIsHolidayUse());
            }
            if(tChargeRule.getTimesecStart()!=null && tChargeRule.getTimesecStart()!=""){
                criteria.andTimesecStartEqualTo(tChargeRule.getTimesecStart());
            }
            if(tChargeRule.getTimesecEnd()!=null && tChargeRule.getTimesecEnd()!=""){
                criteria.andTimesecEndEqualTo(tChargeRule.getTimesecEnd());
            }
            if(tChargeRule.getUnit1Sectime()!=null){
                criteria.andUnit1SectimeEqualTo(tChargeRule.getUnit1Sectime());
            }
            if(tChargeRule.getUnit1Time()!=null){
                criteria.andUnit1TimeEqualTo(tChargeRule.getUnit1Time());
            }
            if(tChargeRule.getUnit1TimeFee()!=null){
                criteria.andUnit1TimeFeeEqualTo(tChargeRule.getUnit1TimeFee());
            }
            if(tChargeRule.getUnit1Maxfee()!=null){
                criteria.andUnit1MaxfeeEqualTo(tChargeRule.getUnit1Maxfee());
            }
            if(tChargeRule.getUnit2Sectime()!=null){
                criteria.andUnit2SectimeEqualTo(tChargeRule.getUnit2Sectime());
            }
            if(tChargeRule.getUnit2Time()!=null){
                criteria.andUnit2TimeEqualTo(tChargeRule.getUnit2Time());
            }
            if(tChargeRule.getUnit2TimeFee()!=null){
                criteria.andUnit2TimeFeeEqualTo(tChargeRule.getUnit2TimeFee());
            }
            if(tChargeRule.getUnit2Maxfee()!=null){
                criteria.andUnit2MaxfeeEqualTo(tChargeRule.getUnit2Maxfee());
            }
            if(tChargeRule.getUnit3Sectime()!=null){
                criteria.andUnit3SectimeEqualTo(tChargeRule.getUnit3Sectime());
            }
            if(tChargeRule.getUnit3Time()!=null){
                criteria.andUnit3TimeEqualTo(tChargeRule.getUnit3Time());
            }
            if(tChargeRule.getUnit3TimeFee()!=null){
                criteria.andUnit3TimeFeeEqualTo(tChargeRule.getUnit3TimeFee());
            }
            if(tChargeRule.getUnit3Maxfee()!=null){
                criteria.andUnit3MaxfeeEqualTo(tChargeRule.getUnit3Maxfee());
            }
            if(tChargeRule.getH24UnitTime()!=null){
                criteria.andH24UnitTimeEqualTo(tChargeRule.getH24UnitTime());
            }
            if(tChargeRule.getH24UnitTimeFee()!=null){
                criteria.andH24UnitTimeFeeEqualTo(tChargeRule.getH24UnitTimeFee());
            }
            if(tChargeRule.getSurpassTime()!=null){
                criteria.andSurpassTimeEqualTo(tChargeRule.getSurpassTime());
            }
            if(tChargeRule.getH24Rule()!=null){
                criteria.andH24RuleEqualTo(tChargeRule.getH24Rule());
            }
            if(tChargeRule.getMaxDay1Fee()!=null){
                criteria.andMaxDay1FeeEqualTo(tChargeRule.getMaxDay1Fee());
            }
            if(tChargeRule.getMaxDaynFee()!=null){
                criteria.andMaxDaynFeeEqualTo(tChargeRule.getMaxDaynFee());
            }
            if(tChargeRule.getMaxSectimeFee()!=null){
                criteria.andMaxSectimeFeeEqualTo(tChargeRule.getMaxSectimeFee());
            }
            if(tChargeRule.getIsMaxSectimeFeeByAdd()!=null){
                criteria.andIsMaxSectimeFeeByAddEqualTo(tChargeRule.getIsMaxSectimeFeeByAdd());
            }
            if(tChargeRule.getIsMax24FeeByAdd()!=null){
                criteria.andIsMax24FeeByAddEqualTo(tChargeRule.getIsMax24FeeByAdd());
            }
            if(tChargeRule.getH24CalMode()!=null){
                criteria.andH24CalModeEqualTo(tChargeRule.getH24CalMode());
            }
        }
        return  tChargeRuleCriteria;
    }

    /**
    * 获取数据总量
    * @param tChargeRule
    * @return
    */
    private Integer getCount(TChargeRule tChargeRule){
    Integer total =(int)tChargeRuleMapper.countByExample(setCriteria(tChargeRule));
    return total;
    }

    /**
    *查询tChargeRule(分页)
    * @param tChargeRule
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TChargeRule> getTChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TChargeRule> tChargeRules=getTChargeRule(tChargeRule);
            Integer countNums =getCount(tChargeRule);
            PageBean<TChargeRule> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tChargeRules);
            return pageData;
        }

    /**
    * 查询tChargeRule
    * @param tChargeRule
    * @return
    */
    public List<TChargeRule> getTChargeRule(TChargeRule tChargeRule){
    List<TChargeRule>  tChargeRules=tChargeRuleMapper.selectByExample(setCriteria(tChargeRule));
    return tChargeRules;
    }

    /**
    * 更新tChargeRule
    * @param tChargeRule
    * @return
    */
    public String UpdateTChargeRule(TChargeRule tChargeRule)
    {
            String msg="";
            try{
            if(tChargeRule.getId()!=null){
            tChargeRuleMapper.updateByPrimaryKeySelective(tChargeRule);
                msg="更新成功";
            }
            else
            {
            tChargeRuleMapper.insertSelective(tChargeRule);
                msg="新建成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    /**
    * 删除tChargeRule
    * @param tChargeRule
    * @return
    */
    public String DeleteTChargeRule(TChargeRule tChargeRule){
            String msg="";
            if(tChargeRule.getId()!=null){
            tChargeRuleMapper.deleteByPrimaryKey(tChargeRule.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tChargeRule
    * @param id
    * @return
    */
    public TChargeRule getTChargeRuleByID(Integer id) {
        return  tChargeRuleMapper.selectByPrimaryKey(id);
    }
}
