package com.eparking.insideService;

import com.common.entity.eparkingCloud.TCompany;
import com.eparking.insideService.impl.TCompanyInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCompanyInsideService接口
* @author 谢轩然
* @date 2020/04/15 16:12
*/
@FeignClient(value = "eparkingCloudData",fallback = TCompanyInsideServiceImpl.class)
public interface TCompanyInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCompany
    * @param tCompany
    * @return
    */
    @PostMapping(value = client+"/tCompany/getTCompany")
    List<TCompany> getTCompany(@RequestBody TCompany tCompany);

    /**
    *查询(分页)tCompany
    * @param tCompany
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCompany/getTCompanybyPage")
    PageBean<TCompany> getTCompanybyPage(@RequestBody TCompany tCompany, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCompany
    * @param tCompany
    * @return
    */
    @PostMapping(value = client+"/tCompany/updateTCompany")
    String UpdateTCompany(@RequestBody TCompany tCompany);

    /**
    * 删除tCompany
    * @param tCompany
    * @return
    */
    @PostMapping(value = client+"/tCompany/deleteTCompany")
    String DeleteTCompany(@RequestBody TCompany tCompany);

    /**
     * 根据id查询tCompany
     * @param id
     * @return
     */
    @PostMapping(value = client+"/tCompany/getTCompanyById")
    TCompany getTCompanyById(@RequestParam("id") Integer id);

    /**
     * 查询是否存在重复物业信息
     * @param tCompany
     * @return
     */
    @PostMapping(value = client+"/tCompany/getCompanyDistnict")
    List<TCompany> getCompanyDistnict(@RequestBody TCompany tCompany);
}