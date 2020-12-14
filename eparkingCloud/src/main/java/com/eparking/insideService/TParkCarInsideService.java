package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.insideService.impl.TParkCarInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import net.sf.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TParkCarInsideService接口
* @author 谢轩然
* @date 2020/05/14 15:43
*/
@FeignClient(value = "eparkingCloudData",fallback = TParkCarInsideServiceImpl.class)
public interface TParkCarInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tParkCar
    * @param tParkCar
    * @return
    */
    @PostMapping(value = client+"/tParkCar/getTParkCar")
    List<TParkCar> getTParkCar(@RequestBody TParkCar tParkCar, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd);

    /**
    *查询(分页)tParkCar
    * @param tParkCar
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tParkCar/getTParkCarbyPage")
    PageBean<TParkCar> getTParkCarbyPage(@RequestBody TParkCar tParkCar, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client+"/tParkCar/getOwnerCar")
    List<TParkCar> getOwnerCar(@RequestBody TParkCar tParkCar, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd);

    /**
     *查询(分页)tParkCar
     * @param tParkCar
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tParkCar/getOwnnerCarbyPage")
    PageBean<TParkCar> getOwnnerCarbyPage(@RequestBody TParkCar tParkCar, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tParkCar
    * @param tParkCar
    * @return
    */
    @PostMapping(value = client+"/tParkCar/updateTParkCar")
    String UpdateTParkCar(@RequestBody TParkCar tParkCar);

    /**
    * 删除tParkCar
    * @param tParkCar
    * @return
    */
    @PostMapping(value = client+"/tParkCar/deleteTParkCar")
    String DeleteTParkCar(@RequestBody TParkCar tParkCar);

    /**
     * 根据ID查TParkCar信息
     * @param id
     * @return
     */
    @PostMapping(value = client+"/tParkCar/getTParkCarById")
    TParkCar getTParkCarById(@RequestParam("id") Integer id);

    @PostMapping(value = client+"/tParkCar/recharge")
    ActionRsp recharge(@RequestParam("carInfo") String carInfo);

    @PostMapping(value = client+"/tParkCar/getCloseParkCar")
    List<TParkCar> getCloseParkCar(@RequestParam("closeType") String closeType, @RequestParam("payRule") String payRule, @RequestParam("parkid") String parkid);

    /**
     * 查询接近到期的月租车分页
     * @param closeType
     * @param payRule
     * @param parkid
     * @return
     */
    @PostMapping(value = client+"/tParkCar/getCloseParkCarbyPage")
    PageBean<TParkCar> getCloseParkCarbyPage(@RequestParam("closeType") String closeType, @RequestParam("payRule") String payRule, @RequestParam("parkid") String parkid, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @PostMapping(value = client+"/tCarPayRule/getNeedPay")
    JSONObject getNeedPay(@RequestParam("payStandard") Integer payStandard, @RequestParam("payCount") Integer payCount, @RequestBody TParkCar tParkCar);

    @PostMapping(value = client+"/tParkCar/Resetport")
    String Resetport(@RequestParam("portids") String portids, @RequestParam("ids") String ids);

    @PostMapping(value = client+"/tParkCar/correct")
    ActionRsp correct(@RequestParam("carInfo") String carInfo);

    @PostMapping(value = client+"/tParkCar/refund")
    ActionRsp refund(@RequestParam("carInfo") String carInfo);

    @PostMapping(value = client+"/tParkCar/insertTParkCarMap")
    Integer insertTParkCarMap(@RequestBody TParkCar tParkCar);

    @PostMapping(value = client+"/tParkCar/whetherTParkCar")
    List<TParkCar> whetherTParkCar(@RequestParam("parkId") Integer parkId, @RequestParam("carPlate") String carPlate, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd);

    @PostMapping(value = client+"/tParkCar/getPresentCarNum")
    Integer getPresentCarNum(@RequestParam("parkIDlist") String parkIDlist);

    @PostMapping(value = client+"/tParkCar/getTParkCarForStatistics")
    List<TParkCar> getTParkCarForStatistics(@RequestBody TParkCar tParkCar, @RequestParam("date") String date);

    /**
     *模糊查询(分页)tParkCar
     * @param
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = client+"/tParkCar/getTParkCarFuzzybyPage")
    PageBean<TParkCar> getTParkCarFuzzybyPage(@RequestBody TParkCar tParkCar, @RequestParam("parkCarIdList") String parkCarIdList, @RequestParam("endDateSelectStart") String endDateSelectStart, @RequestParam("endDateSelectEnd") String endDateSelectEnd, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

}