package com.eparking.insideService;

import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.eparking.insideService.impl.TMonthBindingCarInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = TMonthBindingCarInsideServiceImpl.class)
public interface TMonthBindingCarInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询tMonthBindingCar
     * @param tMonthBindingCar
     * @return
     */
    @PostMapping(value = client+"/tMonthBindingCar/getTMonthBindingCar")
    List<TMonthBindingCar> getTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar);

    /**
     *查询(分页)tMonthBindingCar
     * @param tMonthBindingCar
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tMonthBindingCar/getTMonthBindingCarbyPage")
    PageBean<TMonthBindingCar> getTMonthBindingCarbyPage(@RequestBody TMonthBindingCar tMonthBindingCar, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tMonthBindingCar
     * @param tMonthBindingCar
     * @return
     */
    @PostMapping(value = client+"/tMonthBindingCar/updateTMonthBindingCar")
    String UpdateTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar);

    /**
     * 删除tMonthBindingCar
     * @param tMonthBindingCar
     * @return
     */
    @PostMapping(value = client+"/tMonthBindingCar/deleteTMonthBindingCar")
    String DeleteTMonthBindingCar(@RequestBody TMonthBindingCar tMonthBindingCar);

    /**
     * 根据ID查tMonthBindingCar信息
     * @param id
     * @return
     */
    @PostMapping(value = client+"/tMonthBindingCar/getTMonthBindingCarById")
    TMonthBindingCar getTMonthBindingCarById(@RequestParam("id") Integer id);
}
