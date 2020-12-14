package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TCarPayRuleCriteria;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparkingdb.dao.TCarPayRuleMapper;
import com.eparkingdb.service.TCarPayRuleService;
import com.common.util.CommonUtil;
import com.common.util.StringUtil3;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @Description: TCarPayRuleService接口实现类
* @author 谢轩然
* @date 2020/05/14 18:07
*/
@Service
public class TCarPayRuleServiceImpl  implements TCarPayRuleService {

    private  static final Logger logger= LoggerFactory.getLogger( TCarPayRuleServiceImpl.class);
    @Autowired
    private TCarPayRuleMapper tCarPayRuleMapper;

    /**
    * 设置查询条件
    * @param tCarPayRule
    * @return
    */
    private  TCarPayRuleCriteria setCriteria(TCarPayRule tCarPayRule){
        TCarPayRuleCriteria tCarPayRuleCriteria= new TCarPayRuleCriteria();
        if(tCarPayRule!=null){
        TCarPayRuleCriteria.Criteria criteria=tCarPayRuleCriteria.createCriteria();
        if(tCarPayRule.getId()!=null){
        criteria.andIdEqualTo(tCarPayRule.getId());
        }
            if(tCarPayRule.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tCarPayRule.getCompanyId());
            }
            if(tCarPayRule.getParkId()!=null){
                criteria.andParkIdEqualTo(tCarPayRule.getParkId());
            }
            if(tCarPayRule.getRuleName()!=null && !tCarPayRule.getRuleName().equals("")){
                criteria.andRuleNameEqualTo(tCarPayRule.getRuleName());
            }
        }
        return  tCarPayRuleCriteria;
    }

    /**
    * 获取数据总量
    * @param tCarPayRule
    * @return
    */
    private Integer getCount(TCarPayRule tCarPayRule){
    Integer total =(int)tCarPayRuleMapper.countByExample(setCriteria(tCarPayRule));
    return total;
    }

    /**
    *查询tCarPayRule(分页)
    * @param tCarPayRule
    * @param page
    * @param limit
    * @return
    */
    public PageBean<TCarPayRule> getTCarPayRulebyPage(TCarPayRule tCarPayRule, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCarPayRule> tCarPayRules=getTCarPayRule(tCarPayRule);
            Integer countNums =getCount(tCarPayRule);
            PageBean<TCarPayRule> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCarPayRules);
            return pageData;
        }

    /**
    * 查询tCarPayRule
    * @param tCarPayRule
    * @return
    */
    public List<TCarPayRule> getTCarPayRule(TCarPayRule tCarPayRule){
    List<TCarPayRule>  tCarPayRules=tCarPayRuleMapper.selectByExample(setCriteria(tCarPayRule));
    return tCarPayRules;
    }

    /**
    * 更新tCarPayRule
    * @param tCarPayRule
    * @return
    */
    public String UpdateTCarPayRule(TCarPayRule tCarPayRule)
    {
        String msg = "";
        try {
            if (tCarPayRule.getId() != null) {
                tCarPayRuleMapper.updateByPrimaryKeySelective(tCarPayRule);
                msg = "更新成功";
            } else {
                tCarPayRuleMapper.insertSelective(tCarPayRule);
                msg = "新建成功";
            }
        } catch (Exception e) {

        }
        return msg;
    }

    /**
    * 删除tCarPayRule
    * @param tCarPayRule
    * @return
    */
    public String DeleteTCarPayRule(TCarPayRule tCarPayRule){
            String msg="";
            if(tCarPayRule.getId()!=null){
            tCarPayRuleMapper.deleteByPrimaryKey(tCarPayRule.getId());
            msg="删除成功";
            }
            return msg;
    }

    /**
    * 根据ID查询tCarPayRule
    * @param id
    * @return
    */
    public TCarPayRule getTCarPayRuleByID(Integer id) {
        return  tCarPayRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer getCarPayRuleId(Integer parkid, String ruleName) {
        TCarPayRuleCriteria tCarPayRuleCriteria= new TCarPayRuleCriteria();
        TCarPayRuleCriteria.Criteria criteria=tCarPayRuleCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkid);
        criteria.andRuleNameLike("%"+ruleName+"%");
        List<TCarPayRule> tCarPayRuleList=tCarPayRuleMapper.selectByExample(tCarPayRuleCriteria);
        return tCarPayRuleList.get(0).getId();
    }

    @Override
    public JSONObject getNeedPay(Integer payStandard, Integer payCount, TParkCar tParkCar) {
        Double needPay = 0.0;//应收费
        String endDate = null;//截止日期
        JSONObject lan=new JSONObject();
        try {
            TCarPayRule carPayRule = getTCarPayRuleByID(tParkCar.getPayRule());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bigengDate = sdf.parse(tParkCar.getEndDate());


            Integer parkingNo = tParkCar.getParkingNo();
            if (carPayRule != null) {
                //年缴
                if (CommonUtil.CAR_PAY_RULE_TYPE_Y.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getYearFee();
                    endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, 365 * payCount));
                    //季度
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_Q.equals(payStandard)) {
                    Long needPay1 = (parkingNo * payCount * carPayRule.getSeasonFee());
                    needPay = needPay1.doubleValue();
                    endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, 3 * payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_M.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getMonthFee();
                    endDate = sdf.format(StringUtil3.getBeforeMonth(bigengDate, payCount));
                } else if (CommonUtil.CAR_PAY_RULE_TYPE_D.equals(payStandard)) {
                    needPay = parkingNo * payCount * carPayRule.getDayFee();
                    endDate = sdf.format(StringUtil3.getBeforeDay(bigengDate, payCount));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //result = "{\"result\":\"success\",\"needPay\":\"" + needPay + "\",\"endDate\":\"" + endDate + "\"}";
        /*lan.addProperty("id",tParkCar.getId());
        lan.addProperty("needPay",needPay);
        lan.addProperty("endDate",endDate);*/
        lan.put("id",tParkCar.getId());
        lan.put("needPay",needPay);
        lan.put("endDate",endDate);
        return lan;
    }
}
