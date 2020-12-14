package com.eparking.service.impl;


import com.common.entity.eparkingCloud.TCarPayRule;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.eparking.service.CarPayRuleService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:492018-9-14
 * @Modified By:
 */
@Service
public class CarPayRuleServiceImpl implements CarPayRuleService {
    private  static final Logger logger= LoggerFactory.getLogger(CarPayRuleServiceImpl.class);
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;

    /**
     * 设置查询条件
     * @param tCarPayRule
     * @return
     */
/*    private TCarPayRuleCriteria setCriteria(TCarPayRule tCarPayRule){
        TCarPayRuleCriteria tCarPayRuleCriteria= new TCarPayRuleCriteria();
        //条件查询
        if(tCarPayRule!=null){
            TCarPayRuleCriteria.Criteria criteria=tCarPayRuleCriteria.createCriteria();
            if(tCarPayRule.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tCarPayRule.getCompanyId());
            }
            if(tCarPayRule.getParkId()!=null){
                criteria.andParkIdEqualTo(tCarPayRule.getParkId());
            }
        }
        return  tCarPayRuleCriteria;
    }*/
    /**
     * 获取数据总量
     * @param tCarPayRule
     * @return
     */
/*    private Integer getCount(TCarPayRule tCarPayRule){
        Integer total =(int)tCarPayRuleMapper.countByExample(setCriteria(tCarPayRule));
        return total;
    }*/
    /**
     * 查询月租缴费信息(分页)
     * @return
     */
    @Override
    public PageBean<TCarPayRule> getCarPayRulebyPage(TCarPayRule tCarPayRule, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"id desc");
        List<TCarPayRule> tCarPayRuleList=getCarPayRule(tCarPayRule);
        Integer countNums =getCount(tCarPayRule);
        PageBean<TCarPayRule> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCarPayRuleList);
        return pageData;*/
        return tCarPayRuleInsideService.getTCarPayRulebyPage(tCarPayRule, page, limit);
    }
    /**
     * 查询月租缴费信息
     * @return
     */
    @Override
    public List<TCarPayRule> getCarPayRule(TCarPayRule tCarPayRule){
/*        List<TCarPayRule> tCarPayRuleList=tCarPayRuleMapper.selectByExample(setCriteria(tCarPayRule));
        return tCarPayRuleList;*/
        return tCarPayRuleInsideService.getTCarPayRule(tCarPayRule);
    }
    /**
     * 更新月租规则
     * @param tCarPayRule
     * @return
     */
    @Override
    public String UpdateCarPayRule(TCarPayRule tCarPayRule)
    {
        String msg="";
/*        logger.info(tCarPayRule.toString());
        if(tCarPayRule.getId()!=null){
            tCarPayRuleMapper.updateByPrimaryKeySelective(tCarPayRule);
        }
        else
        {
            tCarPayRuleMapper.insertSelective(tCarPayRule);
        }
        return "更新成功";*/
        TCarPayRule tCarPayRuleSel = new TCarPayRule();
        tCarPayRuleSel.setParkId(tCarPayRule.getParkId());
        tCarPayRuleSel.setRuleName(tCarPayRule.getRuleName());
        List<TCarPayRule> tCarPayRuleListResult = tCarPayRuleInsideService.getTCarPayRule(tCarPayRuleSel);
        if(tCarPayRule.getId()==null){
            //新增操作
            if(tCarPayRuleListResult.size()>0){
                msg = "该车场已存在相同的规则名称";
            }else{
                msg = tCarPayRuleInsideService.UpdateTCarPayRule(tCarPayRule);
            }
        }else{
            //编辑操作
            if(tCarPayRuleListResult.size()>0){
                for(TCarPayRule tCarPayRuleOne:tCarPayRuleListResult){
                    if(tCarPayRuleOne.getId()!=tCarPayRule.getId()){
                        msg = "该车场已存在相同的规则名称";
                       break;
                    }
                }
            }
            if(msg.equals("")){
                msg = tCarPayRuleInsideService.UpdateTCarPayRule(tCarPayRule);
            }
        }
        return msg;
    }

    /**
     * 删除月租规则
     * @param tCarPayRule
     * @return
     */
    @Override
    public String DeleteCarPayRule(TCarPayRule tCarPayRule){
/*        tCarPayRuleMapper.deleteByPrimaryKey(id);
        return "删除成功";*/
        return tCarPayRuleInsideService.DeleteTCarPayRule(tCarPayRule);
    }

    @Override
    public TCarPayRule getCarPayRuleByid(Integer id) {
//        return tCarPayRuleMapper.selectByPrimaryKey(id);
        return tCarPayRuleInsideService.getTCarPayRuleByID(id);
    }

    /**
     * 获取月租规则id
     * @param parkId
     * @param ruleName
     * @return
     */
    @Override
    public Integer getCarPayRuleId(Integer parkId, String ruleName) {
/*        TCarPayRuleCriteria tCarPayRuleCriteria= new TCarPayRuleCriteria();
        TCarPayRuleCriteria.Criteria criteria=tCarPayRuleCriteria.createCriteria();
        criteria.andParkIdEqualTo(parkid);
        criteria.andRuleNameLike("%"+ruleName+"%");
        List<TCarPayRule> tCarPayRuleList=tCarPayRuleMapper.selectByExample(tCarPayRuleCriteria);
        return tCarPayRuleList.get(0).getId();*/
        return tCarPayRuleInsideService.getCarPayRuleId(parkId, ruleName);
    }

    @Override
    public TCarPayRule selectByPrimaryKey(Integer id) {
//        return tCarPayRuleMapper.selectByPrimaryKey(id);
        return tCarPayRuleInsideService.getTCarPayRuleByID(id);
    }
}
