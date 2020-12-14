package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.*;
import com.eparking.service.*;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName InsideController
 * @Author xiexuanran
 * @Date 2019/10/9 10:39:21
 **/
@RestController
@RequestMapping(value = "inside")
public class InsideController {
    private  static final Logger logger= LoggerFactory.getLogger(InsideController.class);
    @Autowired
    private CarParkService carParkService;

    @Autowired
    private MonthlyCarService monthlyCarService;

    @Autowired
    private UserService userService;

    @Autowired
    private MonthBindingCarService monthBindingCarService;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private ParkPortService parkPortService;

    @Autowired
    private ParkingRecordService parkingRecordService;

    @Autowired
    private  PropertyAutobillService propertyAutobillService;
    @Autowired
    private  PropertyHouseholdBillService propertyHouseholdBillService;
    @Autowired
    private PropertyHouseholdInfoService propertyHouseholdInfoService;

    @Autowired
    private PropertyBillItemsService propertyBillItemsService;

    /**
     * 云值守查询车场列表
     * @return
     */
    @PostMapping(value = "/getParkList")
    @HttpLog(operationType = "0",modularTypeName = "云值守查询车场列表")
    public ActionRsp getParkList(@RequestBody TCompanyUser user){
        return ActionRspUtil.Success(carParkService.getCarParkbyIDIn(user.getParkIds()));
    }
    /**
     * 云值守查询车场列表（LayuiMenu）
     * @param user
     * @return
     */
    @PostMapping(value = "/getParkListbyLayuiMenu")
    @HttpLog(operationType = "0",modularTypeName = "云值守查询车场列表（LayuiMenu）")
    public  List<LayuiMenu> getParkListbyLayuiMenu(@RequestBody  TCompanyUser user) {
        LayuiMenu layuiMenu=new LayuiMenu();
        //用户的车场权限
/*        List<Integer> ids = StringUtil.stringList2List(user.getParkIds().split(","));
        List<TCompanyPark> tCompanyParkList= carParkService.getCarParkbyIDIn(ids);*/
        List<TCompanyPark> tCompanyParkList= carParkService.getCarParkbyIDIn(user.getParkIds());
        //System.out.println("parkList: "+ JsonUtil.listToJson(tCompanyParkList));
        List<LayuiMenu> parkChildMap = new ArrayList<>();
        List<LayuiMenu> layuiMenuList = new ArrayList<>();
        for(int i=0;i<tCompanyParkList.size();i++){
            LayuiMenu layuiMenu1=new LayuiMenu();
            layuiMenu1.setID(tCompanyParkList.get(i).getId());
            layuiMenu1.setName(tCompanyParkList.get(i).getParkName());
            layuiMenu1.setIconCls("System");
            layuiMenu1.setXrc("../view/getStatus?parkId="+tCompanyParkList.get(i).getId());
            parkChildMap.add(layuiMenu1);
        }
        layuiMenu.setID(1);
        layuiMenu.setName("车场列表");
        layuiMenu.setIconCls("User");
        layuiMenu.setChildren(parkChildMap);
        layuiMenuList.add(layuiMenu);

//        System.out.println("result: "+JsonUtil.listToJson(layuiMenuList));

        return layuiMenuList;
    }

    /**
     * 云值守查询车辆信息
     * @param carPlate
     * @param parkId
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/getCarPlateNature")
    @HttpLog(operationType = "0",modularTypeName = "云值守查询车辆信息")
    public ControllerRsp getCarPlateNature(String portName, String carPlate, Integer parkId, Integer page, Integer limit) {
        TParkCar tParkCar=new TParkCar();
        tParkCar.setCarPlate(carPlate);
        tParkCar.setParkId(parkId);
        List<TParkCar> tParkCars=monthlyCarService.getTParkCarlist(tParkCar,"","");

        TMonthBindingCar tMonthBindingCar = new TMonthBindingCar();
        tMonthBindingCar.setCarPlate(carPlate);
        tMonthBindingCar.setParkId(parkId);

        TBlacklist tBlacklist = new TBlacklist();
        tBlacklist.setParkId(parkId);
        tBlacklist.setCarPlate(carPlate);

        TParkPort tParkPort = new TParkPort();
        tParkPort.setParkId(parkId);
        tParkPort.setPortName(portName);

        String carNature;
        String isBlack;
        String portId;
        String isPort = "否";
        List<TMonthBindingCar> tMonthBindingCarList = monthBindingCarService.getMonthBindingCar(tMonthBindingCar);
        List<TBlacklist> tBlacklists = blackListService.getBlackList(tBlacklist);
        List<TParkPort> tParkPortList = parkPortService.getParkPort(tParkPort);

        if(tMonthBindingCarList.size()>0){
            carNature = "2";          //月租车
        }else{
            carNature = "3";          //临时车
        }

        if(tBlacklists.size()>0){
            isBlack = "是";          //黑名单车辆
        }else{
            isBlack = "否";
        }

        portId = tParkPortList.get(0).getPortId().toString();

        //定义车辆信息返回list
        List<Map<String,String>> mapList=new ArrayList<>();
        if(tParkCars.size()>0){
            for (TParkCar parkCar: tParkCars){
                Map<String,String> map=new HashMap<>();
                map.put("carPlate",parkCar.getCarPlate());
                map.put("carNature",carNature);
                map.put("isBlack",isBlack);
                map.put("validity",parkCar.getBeginDate()+","+parkCar.getEndDate());
                map.put("isBlack",isBlack);
                map.put("userName",parkCar.getRealname());
                map.put("telphone",parkCar.getPhone());
                String[] portIdArray = parkCar.getPortId().split(",");
                for(int i=0;i<portIdArray.length;i++){
                    if(portId == portIdArray[i]){
                        isPort = "是";
                    }
                }
                map.put("isPort",isPort);
                mapList.add(map);
            }
        }
//        System.out.println("list: "+mapList.toString());
        return ControllerRspUtil.Success(mapList);
    }

    /**
     * 云值守查询未出场车辆信息
     * @param carPlate
     * @param parkId
     * @param page
     * @param limit
     * @return
     */
    @PostMapping(value = "/getInCarPlate")
    @HttpLog(operationType = "0",modularTypeName = "云值守查询未出场车辆信息")
    public ControllerRsp getInCarPlate(String carPlate, Integer parkId, Integer page, Integer limit) {
        TParkInOut tParkInOut=new TParkInOut();
        tParkInOut.setInCarPlate(carPlate);
        tParkInOut.setParkId(parkId);

        return ControllerRspUtil.Success(parkingRecordService.getInCarbyPage(tParkInOut,page,limit));
    }

    /**
     * 云值守用户验证
     * @param map
     * @return
     */
    @PostMapping(value = "/CheckPassword")
    @HttpLog(operationType = "0",modularTypeName = "云值守用户验证")
    public ActionRsp CheckPassword(@RequestBody  Map map){
        TCompanyUser userSel=new TCompanyUser();
        userSel.setUserAccout(map.get("userAccout").toString());
        userSel.setPassword(map.get("passWord").toString());
        TCompanyUser user = userService.getCompanyUser(userSel).get(0);
        return ActionRspUtil.Success(user);
    }

    /**
     * 查询物业用户信息（云值守）
     * @return
     */
    @PostMapping(value = "/getCompanyUser")
    @HttpLog(operationType = "0",modularTypeName = "查询物业用户信息（云值守）")
    public ControllerRsp getCompanyUser(@RequestBody Map map){
        TCompanyUser userSel=new TCompanyUser();
        userSel.setEntityType(1);
        userSel.setCompanyId(Integer.valueOf(map.get("companyId").toString()));
        return ControllerRspUtil.Success(userService.getCompanyUserbyPage(userSel,Integer.valueOf(map.get("page").toString()),Integer.valueOf(map.get("limit").toString())));
    }

    /**
     * 查询账单生成任务
     * @return
     */
/*    @PostMapping(value = "/autoBillSelect")
    @HttpLog(operationType = "0",modularTypeName = "查询账单生成任务")
    public  ActionRsp autoBillSelect(){
        TPropertyAutobill tPropertyAutobill=new TPropertyAutobill();
        tPropertyAutobill.setCheckoutDate(String.valueOf(DateUtil.getDayOfMonth()));
        List<TPropertyAutobill> tPropertyAutobills=propertyAutobillService.getPropertyAutobill(tPropertyAutobill);
        return  ActionRspUtil.Success(JsonUtil.listToJson(tPropertyAutobills));
    }*/
    /**
     * 住户查询
     * @param tPropertyHouseholdInfo
     * @return
     */
/*    @PostMapping(value = "/houseHoldSelect")
    @HttpLog(operationType = "0",modularTypeName = "住户查询")
    public  ActionRsp houseHoldSelect(TPropertyHouseholdInfo tPropertyHouseholdInfo){
        return  ActionRspUtil.Success(JsonUtil.listToJson(propertyHouseholdInfoService.getPropertyHouseholdInfo(tPropertyHouseholdInfo)));
    }*/
    /**
     * 缴费项目查询
     * @param tPropertyBillItems
     * @return
     */
/*    @PostMapping(value = "/TPropertyBillItemsSelect")
    @HttpLog(operationType = "0",modularTypeName = "缴费项目查询")
    public  ActionRsp TPropertyBillItemsSelect(TPropertyBillItems tPropertyBillItems) {
        return ActionRspUtil.Success(JsonUtil.listToJson(propertyBillItemsService.getPropertyBillItems(tPropertyBillItems)));
    }*/

    /**
     * 生成住户账单
     * @param TPropertyHouseholdbill
     * @return
     */
/*    @PostMapping(value = "/createBill")
    @HttpLog(operationType = "1",modularTypeName = "生成住户账单")
    public  ActionRsp createBill(TPropertyHouseholdbill TPropertyHouseholdbill){
        return  ActionRspUtil.Success(propertyHouseholeparkingdbillService.updatePropertyHouseholeparkingdbill(TPropertyHouseholdbill));
    }*/

    /**
     * 查询住户账单
     * @param TPropertyHouseholdbill
     * @return
     */
/*    @PostMapping(value = "/householeparkingdbillSelect")
    @HttpLog(operationType = "0",modularTypeName = "查询住户账单")
    public  ActionRsp householeparkingdbillSelect(TPropertyHouseholdbill TPropertyHouseholdbill){
        List<Integer> list =new ArrayList<>();
        return  ActionRspUtil.Success(propertyHouseholeparkingdbillService.geTPropertyHouseholdbill(list ,TPropertyHouseholdbill));
    }*/




}
