package com.eparking.insideService;

import com.common.entity.eparkingCloud.TRolePowerNew;
import com.eparking.insideService.impl.TRolePowerNewInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TRolePowerNewInsideService接口
* @author 谢轩然
* @date 2020/05/15 15:20
*/
@FeignClient(value = "eparkingCloudData",fallback = TRolePowerNewInsideServiceImpl.class)
public interface TRolePowerNewInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @PostMapping(value = client+"/tRolePowerNew/getTRolePowerNew")
    List<TRolePowerNew> getTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew);

    /**
    *查询(分页)tRolePowerNew
    * @param tRolePowerNew
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tRolePowerNew/getTRolePowerNewbyPage")
    PageBean<TRolePowerNew> getTRolePowerNewbyPage(@RequestBody TRolePowerNew tRolePowerNew, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @PostMapping(value = client+"/tRolePowerNew/updateTRolePowerNew")
    String UpdateTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew);

    /**
    * 删除tRolePowerNew
    * @param tRolePowerNew
    * @return
    */
    @PostMapping(value = client+"/tRolePowerNew/deleteTRolePowerNew")
    String DeleteTRolePowerNew(@RequestBody TRolePowerNew tRolePowerNew);

    @PostMapping(value = client+"/tRolePowerNew/getRolePowerById")
    List<TRolePowerNew> getRolePowerById(@RequestBody TRolePowerNew tRolePowerNew);

    @PostMapping(value = client+"/tRolePowerNew/selectByPrimaryKey")
    TRolePowerNew selectByPrimaryKey(@RequestParam("id") Integer id);
}