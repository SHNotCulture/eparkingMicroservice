package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TTruckSpace;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TTruckSpaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TTruckSpaceController类
* @author 谢轩然
* @date 2020/05/15 11:22
*/
@RestController
@RequestMapping("/tTruckSpace")
public class TTruckSpaceController {

    private  static final Logger logger= LoggerFactory.getLogger(TTruckSpaceController.class);

    @Autowired
    private TTruckSpaceService tTruckSpaceService;

    /**
    * 查询TTruckSpace信息
    * @paramtTruckSpace
    * @return
    */
    @PostMapping(value = "/getTTruckSpace")
    @HttpLog(operationType = "0",modularTypeName = "查询TTruckSpace")
    public List<TTruckSpace> getTTruckSpace(@RequestBody TTruckSpace tTruckSpace){
    return tTruckSpaceService.getTTruckSpace(tTruckSpace);
    }

    /**
    * 查询TTruckSpace信息(分页)
    * @paramtTruckSpace
    * @return
    */
    @PostMapping(value = "/getTTruckSpacebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TTruckSpace(分页)")
    public PageBean<TTruckSpace> getTTruckSpacebyPage(@RequestBody TTruckSpace tTruckSpace, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tTruckSpaceService.getTTruckSpacebyPage(tTruckSpace,page,limit);
    }

    /**
    * 更新TTruckSpace信息
    * @paramtTruckSpace
    * @return
    */
    @PostMapping(value = "/updateTTruckSpace")
    @HttpLog(operationType = "1",modularTypeName = "更新TTruckSpace信息")
    public String UpdateTTruckSpace(@RequestBody TTruckSpace tTruckSpace)
    {
        return tTruckSpaceService.UpdateTTruckSpace(tTruckSpace);
    }

    /**
    * 删除TTruckSpace信息
    * @param tTruckSpace
    * @return
    */
    @PostMapping(value = "/deleteTTruckSpace")
    @HttpLog(operationType = "1",modularTypeName = "删除TTruckSpace信息")
    public String DeleteTTruckSpace(@RequestBody TTruckSpace tTruckSpace){
    return tTruckSpaceService.DeleteTTruckSpace(tTruckSpace);
    }

    /**
     * 根据ID查找tTruckSpace
     * @param id
     * @return
     */
    @PostMapping(value = "/getTTruckSpaceById")
    @HttpLog(operationType = "0",modularTypeName = "根据ID查找tTruckSpace")
    public TTruckSpace getTTruckSpaceById(@RequestParam("id") Integer id){
        return tTruckSpaceService.getTTruckSpaceByID(id);
    }
}