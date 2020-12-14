package com.eparking.insideService;

import com.common.entity.eparkingCloud.TBlacklist;
import com.eparking.insideService.impl.TBlacklistInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TBlacklistInsideService接口
* @author 谢轩然
* @date 2020/05/14 16:29
*/
@FeignClient(value = "eparkingCloudData",fallback = TBlacklistInsideServiceImpl.class)
public interface TBlacklistInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tBlacklist
    * @param tBlacklist
    * @return
    */
    @PostMapping(value = client+"/tBlacklist/getTBlacklist")
    List<TBlacklist> getTBlacklist(@RequestBody TBlacklist tBlacklist);

    /**
    *查询(分页)tBlacklist
    * @param tBlacklist
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tBlacklist/getTBlacklistbyPage")
    PageBean<TBlacklist> getTBlacklistbyPage(@RequestBody TBlacklist tBlacklist, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tBlacklist
    * @param tBlacklist
    * @return
    */
    @PostMapping(value = client+"/tBlacklist/updateTBlacklist")
    String UpdateTBlacklist(@RequestBody TBlacklist tBlacklist);

    /**
    * 删除tBlacklist
    * @param tBlacklist
    * @return
    */
    @PostMapping(value = client+"/tBlacklist/deleteTBlacklist")
    String DeleteTBlacklist(@RequestBody TBlacklist tBlacklist);

    @PostMapping(value = client+"/tBlacklist/getTBlacklistByID")
    TBlacklist getTBlacklistByID(@RequestParam("id") Integer id);
}