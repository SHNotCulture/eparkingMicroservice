package com.eparking.insideService;

import com.common.entity.eparkingCloud.THoliday;
import com.eparking.insideService.impl.THolidayInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "eparkingCloudData",fallback = THolidayInsideServiceImpl.class)
public interface THolidayInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
     * 查询tHoliday
     * @param tHoliday
     * @return
     */
    @PostMapping(value = client+"/tHoliday/getTHoliday")
    List<THoliday> getTHoliday(@RequestBody THoliday tHoliday);

    /**
     *查询(分页)tHoliday
     * @param tHoliday
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tHoliday/getTHolidaybyPage")
    PageBean<THoliday> getTHolidaybyPage(@RequestBody THoliday tHoliday, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
     * 更新tHoliday
     * @param tHoliday
     * @return
     */
    @PostMapping(value = client+"/tHoliday/updateTHoliday")
    String UpdateTHoliday(@RequestBody THoliday tHoliday);

    /**
     * 删除tHoliday
     * @param tHoliday
     * @return
     */
    @PostMapping(value = client+"/tHoliday/deleteTHoliday")
    String DeleteTHoliday(@RequestBody THoliday tHoliday);
}
