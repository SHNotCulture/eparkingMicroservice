package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCompanyParkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCompanyParkController类
* @author 谢轩然
* @date 2020/04/09 14:50
*/
@RestController
@RequestMapping("/tCompanyPark")
public class TCompanyParkController {

    private  static final Logger logger= LoggerFactory.getLogger(TCompanyParkController.class);

    @Autowired
    private TCompanyParkService tCompanyParkService;

    /**
    * 查询TCompanyPark信息
    * @paramtCompanyPark
    * @return
    */
    @PostMapping(value = "/getTCompanyPark")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyPark")
    public List<TCompanyPark> getTCompanyPark(@RequestBody TCompanyPark tCompanyPark){
    return tCompanyParkService.getTCompanyPark(tCompanyPark);
    }

    /**
    * 查询TCompanyPark信息(分页)
    * @paramtCompanyPark
    * @return
    */
    @PostMapping(value = "/getTCompanyParkbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyPark(分页)")
    public PageBean<TCompanyPark> getTCompanyParkbyPage(@RequestBody TCompanyPark tCompanyPark, Integer page, Integer limit){
    return tCompanyParkService.getTCompanyParkbyPage(tCompanyPark,page,limit);
    }

    /**
    * 更新TCompanyPark信息
    * @paramtCompanyPark
    * @return
    */
    @PostMapping(value = "/updateTCompanyPark")
    @HttpLog(operationType = "1",modularTypeName = "更新TCompanyPark信息")
    public String UpdateTCompanyPark(@RequestBody TCompanyPark tCompanyPark)
    {
        return tCompanyParkService.UpdateTCompanyPark(tCompanyPark);
    }

    /**
    * 删除TCompanyPark信息
    * @param tCompanyPark
    * @return
    */
    @PostMapping(value = "/deleteTCompanyPark")
    @HttpLog(operationType = "1",modularTypeName = "删除TCompanyPark信息")
    public String DeleteTCompanyPark(@RequestBody TCompanyPark tCompanyPark){
    return tCompanyParkService.DeleteTCompanyPark(tCompanyPark);
    }

    /**
     * 根据id查询TCompanyPark信息
     * @paramtCompanyPark
     * @return
     */
    @PostMapping(value = "/getTCompanyParkbyId")
    @HttpLog(operationType = "0",modularTypeName = "根据id查询TCompanyPark信息")
    public TCompanyPark getTCompanyParkbyId(@RequestParam("id") Integer id){
        return tCompanyParkService.getTCompanyParkByID(id);
    }

    @PostMapping(value = "/updateInCarsToday")
    @HttpLog(operationType = "0",modularTypeName = "更新今日入场车辆数")
    public String updateInCarsToday(@RequestParam("parkId") Integer parkId,@RequestParam("number") Integer number, String inTime){
        return tCompanyParkService.updateInCarsToday(parkId,number,inTime);
    }

    @PostMapping(value = "/getCarParkbyIDIn")
    @HttpLog(operationType = "0",modularTypeName = "查询TCompanyPark")
    public List<TCompanyPark> getCarParkbyIDIn(@RequestParam("ids") String ids){
        return tCompanyParkService.getCarParkbyIDIn(ids);
    }
}