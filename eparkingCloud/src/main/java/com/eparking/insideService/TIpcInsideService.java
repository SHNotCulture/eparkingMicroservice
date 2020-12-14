package com.eparking.insideService;

import com.common.entity.eparkingCloud.TIpc;
import com.eparking.insideService.impl.TIpcInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TIpcInsideService接口
* @author 谢轩然
* @date 2020/07/13 18:31
*/
@FeignClient(value = "eparkingData",fallback = TIpcInsideServiceImpl.class)
public interface TIpcInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tIpc
    * @param tIpc
    * @return
    */
    @PostMapping(value = client+"/tIpc/getTIpc")
    List<TIpc> getTIpc(@RequestBody TIpc tIpc);

    /**
    *查询(分页)tIpc
    * @param tIpc
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tIpc/getTIpcbyPage")
    PageBean<TIpc> getTIpcbyPage(@RequestBody TIpc tIpc, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tIpc
    * @param tIpc
    * @return
    */
    @PostMapping(value = client+"/tIpc/updateTIpc")
    String UpdateTIpc(@RequestBody TIpc tIpc);

    /**
    * 删除tIpc
    * @param tIpc
    * @return
    */
    @PostMapping(value = client+"/tIpc/deleteTIpc")
    String DeleteTIpc(@RequestBody TIpc tIpc);
}