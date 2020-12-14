package com.eparking.insideService;

import com.common.entity.eparkingCloud.TTruckSpace;
import com.eparking.insideService.impl.TTruckSpaceInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TTruckSpaceInsideService接口
* @author 谢轩然
* @date 2020/05/15 11:22
*/
@FeignClient(value = "eparkingCloudData",fallback = TTruckSpaceInsideServiceImpl.class)
public interface TTruckSpaceInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @PostMapping(value = client+"/tTruckSpace/getTTruckSpace")
    List<TTruckSpace> getTTruckSpace(@RequestBody TTruckSpace tTruckSpace);

    /**
    *查询(分页)tTruckSpace
    * @param tTruckSpace
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tTruckSpace/getTTruckSpacebyPage")
    PageBean<TTruckSpace> getTTruckSpacebyPage(@RequestBody TTruckSpace tTruckSpace, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @PostMapping(value = client+"/tTruckSpace/updateTTruckSpace")
    String UpdateTTruckSpace(@RequestBody TTruckSpace tTruckSpace);

    /**
    * 删除tTruckSpace
    * @param tTruckSpace
    * @return
    */
    @PostMapping(value = client+"/tTruckSpace/deleteTTruckSpace")
    String DeleteTTruckSpace(@RequestBody TTruckSpace tTruckSpace);

    /**
     * 根据ID查找tTruckSpace
     * @param id
     * @return
     */
    @PostMapping(value = client+"/tTruckSpace/getTTruckSpaceById")
    TTruckSpace getTTruckSpaceById(@RequestParam("id") Integer id);
}