package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TCarPayRule;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TCarPayRuleInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/14 18:07
*/
@Component
public class TCarPayRuleInsideServiceImpl  implements TCarPayRuleInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TCarPayRuleInsideServiceImpl.class);

    /**
    * 查询tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @Override
    public List<TCarPayRule> getTCarPayRule(TCarPayRule tCarPayRule){
        String str = "查询TCarPayRule失败！";
        logger.info("查询TCarPayRule失败！");
        return null;
    }

    /**
    *查询(分页)tCarPayRule
    * @param tCarPayRule
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TCarPayRule> getTCarPayRulebyPage(TCarPayRule tCarPayRule, Integer page, Integer limit){
        String str = "查询TCarPayRule分页失败！";
        logger.info("查询TCarPayRule分页失败！");
        return null;
    }

    /**
    * 更新tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @Override
    public String UpdateTCarPayRule(TCarPayRule tCarPayRule){
        String str = "更新TCarPayRule失败！";
        logger.info("更新TCarPayRule失败！");
        return str;
    }

    /**
    * 删除tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @Override
    public String DeleteTCarPayRule(TCarPayRule tCarPayRule){
        String str = "删除TCarPayRule失败！";
        logger.info("删除TCarPayRule失败！");
        return str;
    }

    @Override
    public TCarPayRule getTCarPayRuleByID(Integer id) {
        String str = "通过ID查询TCarPayRule失败！";
        logger.info("通过ID查询TCarPayRule失败！");
        return null;
    }

    @Override
    public Integer getCarPayRuleId(Integer parkId, String ruleName) {
        String str = "获取月租规则id失败！";
        logger.info("获取月租规则id失败！");
        return null;
    }
}