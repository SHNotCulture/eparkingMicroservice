package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCarPayRule;
import com.eparking.insideService.impl.TCarPayRuleInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCarPayRuleInsideService接口
* @author 谢轩然
* @date 2020/05/14 18:07
*/
@FeignClient(value = "eparkingCloudData",fallback = TCarPayRuleInsideServiceImpl.class)
public interface TCarPayRuleInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @PostMapping(value = client+"/tCarPayRule/getTCarPayRule")
    List<TCarPayRule> getTCarPayRule(@RequestBody TCarPayRule tCarPayRule);

    /**
    *查询(分页)tCarPayRule
    * @param tCarPayRule
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCarPayRule/getTCarPayRulebyPage")
    PageBean<TCarPayRule> getTCarPayRulebyPage(@RequestBody TCarPayRule tCarPayRule, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @PostMapping(value = client+"/tCarPayRule/updateTCarPayRule")
    String UpdateTCarPayRule(@RequestBody TCarPayRule tCarPayRule);

    /**
    * 删除tCarPayRule
    * @param tCarPayRule
    * @return
    */
    @PostMapping(value = client+"/tCarPayRule/deleteTCarPayRule")
    String DeleteTCarPayRule(@RequestBody TCarPayRule tCarPayRule);

    /**
     * 根据id查询tCarPayRule
     * @param id
     * @return
     */
    @PostMapping(value = client+"/tCarPayRule/getTCarPayRuleByID")
    TCarPayRule getTCarPayRuleByID(@RequestParam("id") Integer id);

    /**
     * 获取月租规则id
     * @param
     * @return
     */
    @PostMapping(value = client+"/tCarPayRule/getCarPayRuleId")
    Integer getCarPayRuleId(@RequestParam("parkId") Integer parkId, @RequestParam("ruleName") String ruleName);
}