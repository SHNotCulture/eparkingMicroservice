package com.eparking.service.impl;

import com.common.entity.eparkingCloud.YuncsisResult;
import com.common.util.HttpUtil;
import com.eparking.service.YuncsisApiService;
import com.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class YuncsisApiServiceImpl implements YuncsisApiService {
    private static final Logger logger = LoggerFactory.getLogger(YuncsisApiServiceImpl.class);
    @Override
    public YuncsisResult updateblackcar(Map map) {
        Map blackMap = new HashMap();
        blackMap.put("Id",map.get("Id"));
        blackMap.put("parkId",map.get("parkId"));
        blackMap.put("carPlate",map.get("carPlate"));
        blackMap.put("delete",map.get("delete"));

        String params =String.format("params=%s", JsonUtil.mapToJson(blackMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/updateblackcar?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult reservedcar(Map map) {
        Map reservedCarMap = new HashMap();
        reservedCarMap.put("parkId",map.get("parkId"));
        reservedCarMap.put("carPlate",map.get("carPlate"));
        reservedCarMap.put("carType",map.get("carType"));
        reservedCarMap.put("beginTime",map.get("beginTime"));
        reservedCarMap.put("endTime",map.get("endTime"));
        reservedCarMap.put("flag",map.get("flag"));
        String params =String.format("params=%s", JsonUtil.mapToJson(reservedCarMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/reservedcar?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult vircarplate(Map map) {
        Map vircarMap = new HashMap();
        vircarMap.put("parkId",map.get("parkId"));
        vircarMap.put("laneId",map.get("laneId"));
        vircarMap.put("virCarPlate",map.get("virCarPlate"));
        vircarMap.put("flag",map.get("flag"));
        String params =String.format("params=%s", JsonUtil.mapToJson(vircarMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/vircarplate?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult prepay(Map map) {
        Map prepayMap = new HashMap();
        prepayMap.put("parkId",map.get("parkId"));
        prepayMap.put("orderId",map.get("orderId"));
        prepayMap.put("payAmount",map.get("payAmount"));
        prepayMap.put("payMode",map.get("payMode"));
        prepayMap.put("payTime",map.get("payTime"));
        prepayMap.put("remark",map.get("remark"));
        String params =String.format("params=%s", JsonUtil.mapToJson(prepayMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/prepay?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult coupon(Map map) {
        Map couponMap = new HashMap();
        couponMap.put("parkId",map.get("parkId"));
        couponMap.put("merchantId",map.get("merchantId"));
        couponMap.put("merchantName",map.get("merchantName"));
        couponMap.put("couponAmount",map.get("couponAmount"));
        couponMap.put("couponType",map.get("couponType"));
        couponMap.put("couponTime",map.get("couponTime"));
        String params =String.format("params=%s", JsonUtil.mapToJson(couponMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/coupon?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult updatemember(Map map) {
        Map updatememberMap = new HashMap();
        updatememberMap.put("parkId",map.get("parkId"));
        updatememberMap.put("cloudId",map.get("cloudId"));
        updatememberMap.put("carPlate",map.get("carPlate"));
        updatememberMap.put("carType",map.get("carType"));
        updatememberMap.put("totalCars",map.get("totalCars"));
        updatememberMap.put("realName",map.get("realName"));
        updatememberMap.put("address",map.get("address"));
        updatememberMap.put("phone",map.get("phone"));
        updatememberMap.put("status",map.get("status"));
        updatememberMap.put("beginDate",map.get("beginDate"));
        updatememberMap.put("endDate",map.get("endDate"));
        updatememberMap.put("laneRights",map.get("laneRights"));
        updatememberMap.put("ownerLots",map.get("ownerLots"));
        updatememberMap.put("isLock",map.get("isLock"));
        updatememberMap.put("lockTime",map.get("lockTime"));
        updatememberMap.put("totalLots",map.get("totalLots"));
        updatememberMap.put("usedLots",map.get("usedLots"));
        updatememberMap.put("ruleName",map.get("ruleName"));
        updatememberMap.put("parkLotCode",map.get("parkLotCode"));
        updatememberMap.put("parkLotType",map.get("parkLotType"));
        updatememberMap.put("dailyStartTime",map.get("dailyStartTime"));
        updatememberMap.put("dailyEndTime",map.get("dailyEndTime"));
        updatememberMap.put("renewalTime",map.get("renewalTime"));
        updatememberMap.put("flag",map.get("flag"));
        String params =String.format("params=%s", JsonUtil.mapToJson(updatememberMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/updatemember?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult updatemembercar(Map map) {
        Map updateMemberCarMap = new HashMap();
        updateMemberCarMap.put("parkId",map.get("parkId"));
        updateMemberCarMap.put("carPlate",map.get("carPlate"));
        updateMemberCarMap.put("carType",map.get("carType"));
        updateMemberCarMap.put("parkCarId",map.get("parkCarId"));
        updateMemberCarMap.put("updateTime",map.get("updateTime"));
        updateMemberCarMap.put("flag",map.get("flag"));
        String params =String.format("params=%s", JsonUtil.mapToJson(updateMemberCarMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/updatemembercar?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult calfee(Map map) {
        Map calfeeMap = new HashMap();
        calfeeMap.put("parkId",map.get("parkId"));
        calfeeMap.put("carPlate",map.get("carPlate"));
        calfeeMap.put("feeduration",map.get("feeduration"));
        String params =String.format("params=%s", JsonUtil.mapToJson(calfeeMap));

        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7070/yuncsisapi/calfee?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult updateLaneinfo(Map map) {
        Map updateLaneinfoMap = new HashMap();
        updateLaneinfoMap.put("flag",map.get("flag"));
        updateLaneinfoMap.put("cloud_id",Integer.valueOf(map.get("cloudId").toString()));
        updateLaneinfoMap.put("parkId",Integer.valueOf(map.get("parkId").toString()));
        updateLaneinfoMap.put("parkName",map.get("parkName"));
        updateLaneinfoMap.put("laneId",Integer.valueOf(map.get("cloudId").toString()));
        updateLaneinfoMap.put("lane_name",map.get("laneName"));
        updateLaneinfoMap.put("laneControlSn",map.get("laneControlSn"));
        updateLaneinfoMap.put("ipcIp",map.get("ipcIp"));
        updateLaneinfoMap.put("Ipc_sn",map.get("ipcSn"));
        updateLaneinfoMap.put("Ipc_Aux1_sn",map.get("auxIpc1Sn"));
        updateLaneinfoMap.put("Ipc_Aux2_sn",map.get("auxIpc2Sn"));
        updateLaneinfoMap.put("lane_type",Integer.valueOf(map.get("laneType").toString()));
        updateLaneinfoMap.put("area_number",Integer.valueOf(map.get("areaNumber").toString()));
        updateLaneinfoMap.put("intervene",Integer.valueOf(map.get("intervene").toString()));
        updateLaneinfoMap.put("actived",Integer.valueOf(map.get("actived").toString()));
        updateLaneinfoMap.put("lotsLedIp",map.get("lotsLedIp"));
        updateLaneinfoMap.put("lotsLedStr",map.get("lotsLedStr"));
        updateLaneinfoMap.put("isInCarUpdate",Integer.valueOf(map.get("isInCarUpdate").toString()));
        updateLaneinfoMap.put("lane_rx_mode",Integer.valueOf(map.get("laneRxMode").toString()));
        updateLaneinfoMap.put("voiceMode",Integer.valueOf(map.get("voiceMode").toString()));
        updateLaneinfoMap.put("voiceVolume",Integer.valueOf(map.get("voiceVolume").toString()));
        updateLaneinfoMap.put("ipcType",Integer.valueOf(map.get("ipcType").toString()));
        //文档没有
        updateLaneinfoMap.put("pub_topic","lane/toyuncsis");
        updateLaneinfoMap.put("sub_topic","lane/"+map.get("cloudId"));

        String params =String.format("params=%s", JsonUtil.mapToJson(updateLaneinfoMap));
        logger.info(params);
//        String result = HttpRequest.getUrlResultPost("http://yun1.eparking.top:7090/yuncsisapi/update_laneinfo?", params);
        String result = HttpUtil.getUrlResultPost("http://localhost:7090/yuncsisapi/update_laneinfo?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult updateParkParams(Map map,Integer flag) {
        Map updateParkParamsMap = new HashMap();
        updateParkParamsMap.put("flag",flag);
        updateParkParamsMap.put("parkId",((Double)map.get("parkId")).intValue());
        updateParkParamsMap.put("parkKey",map.get("parkKey"));
        updateParkParamsMap.put("memberOuttimeInEnable",((Double)map.get("memberOuttimeInEnable")).intValue());
        updateParkParamsMap.put("preVoicecastFixcar",((Double)map.get("preVoicecastFixcar")).intValue());
        updateParkParamsMap.put("tempcarFreeAutoOut",((Double)map.get("tempcarFreeAutoOut")).intValue());
        updateParkParamsMap.put("recordSaveDays",((Double)map.get("recordSaveDays")).intValue());
        updateParkParamsMap.put("picSaveDays",((Double)map.get("picSaveDays")).intValue());
        updateParkParamsMap.put("freeSecondsPrepay",((Double)map.get("freeSecondsPrepay")).intValue());
        updateParkParamsMap.put("wasteInfoSaveHours",((Double)map.get("wasteInfoSaveHours")).intValue());
        updateParkParamsMap.put("lockCarEnale",((Double)map.get("lockCarEnale")).intValue());
        updateParkParamsMap.put("bzPayEnale",((Double)map.get("bzPayEnale")).intValue());
        updateParkParamsMap.put("bzPayAutoOpenEnable",((Double)map.get("bzPayAutoOpenEnable")).intValue());
        updateParkParamsMap.put("terminalIp",map.get("enableTerminalUrl"));
        updateParkParamsMap.put("uploadURL",map.get("uploadURL"));
        updateParkParamsMap.put("otherPayEnable",((Double)map.get("otherPayEnable")).intValue());
        updateParkParamsMap.put("voiceCastCarplate",((Double)map.get("voiceCastCarplate")).intValue());
        updateParkParamsMap.put("newCarEnterEnable",((Double)map.get("newCarEnterEnable")).intValue());
        updateParkParamsMap.put("sencondOpenEnable",((Double)map.get("sencondOpenEnable")).intValue());
        updateParkParamsMap.put("limitFullCoupon",((Double)map.get("limitFullCoupon")).intValue());
        updateParkParamsMap.put("noInRecordQueryCloud",((Double)map.get("noInRecordQueryCloud")).intValue());
        updateParkParamsMap.put("globalTempRule",((Double)map.get("tempcarChargeRules")).intValue());
        updateParkParamsMap.put("tempcarInEnableAsLotfull",((Double)map.get("tempcarInEnableAsLotfull")).intValue());
        updateParkParamsMap.put("fixcarInEnalbeAsLotfull",((Double)map.get("fixcarInEnalbeAsLotfull")).intValue());
        updateParkParamsMap.put("virOverwriteInRecord",((Double)map.get("virOverwriteInRecord")).intValue());
        updateParkParamsMap.put("uploadRecEnable",((Double)map.get("uploadRecEnable")).intValue());
        updateParkParamsMap.put("bindingcarExchangecarEnable",((Double)map.get("bindingcarExchangecarEnable")).intValue());
        updateParkParamsMap.put("isSpecialCarChargeRule",((Double)map.get("isSpecialCarChargeRule")).intValue());
        updateParkParamsMap.put("calfeeExpiredFixcar",((Double)map.get("calfeeExpiredFixcar")).intValue());
        updateParkParamsMap.put("ePayAutoRequest",((Double)map.get("ePayAutoRequest")).intValue());
        updateParkParamsMap.put("requestRealTimeCouponInfo",((Double)map.get("requestRealTimeCouponInfo")).intValue());
        updateParkParamsMap.put("prepayOuttimeReCalMode",((Double)map.get("prepayOuttimeReCalMode")).intValue());
        updateParkParamsMap.put("fixCarfeeEpayAutoRequest",0);
        updateParkParamsMap.put("totalParking",0);
        updateParkParamsMap.put("parklotFree",0);
        updateParkParamsMap.put("inFixedPrivateLotCount",0);
        updateParkParamsMap.put("inFixedParkLotCount",0);
        updateParkParamsMap.put("inTempCarCount",0);
        updateParkParamsMap.put("reservedCarCount",0);
/*        updateParkParamsMap.put("fixCarfeeEpayAutoRequest",map.get("fixCarfeeEpayAutoRequest"));
        updateParkParamsMap.put("totalParking",map.get("totalParking"));
        updateParkParamsMap.put("parklotFree",map.get("parklotFree"));
        updateParkParamsMap.put("inFixedPrivateLotCount",map.get("inFixedPrivateLotCount"));
        updateParkParamsMap.put("inFixedParkLotCount",map.get("inFixedParkLotCount"));
        updateParkParamsMap.put("inTempCarCount",map.get("inTempCarCount"));
        updateParkParamsMap.put("reservedCarCount",map.get("reservedCarCount"));*/
        //没接收
        updateParkParamsMap.put("bindingCarInAsNormalFixCar",((Double)map.get("bindingCarInAsNormalFixCar")).intValue());
        //没接收
        updateParkParamsMap.put("fixCarPreciseJudge",((Double)map.get("fixCarPreciseJudge")).intValue());
        //没接收
        updateParkParamsMap.put("requestCloudFixInfo",0);
        //没接收
        updateParkParamsMap.put("inSpecialCarIntervene",map.get("inSpecialCarIntervene"));
        //没接收
        updateParkParamsMap.put("outSpecialCarIntervene",map.get("outSpecialCarIntervene"));
        //没接收
        updateParkParamsMap.put("noEntryCharge",((Double)map.get("noEntryCharge")).intValue());
        updateParkParamsMap.put("voiceVolume",((Double)map.get("volume")).intValue());
        updateParkParamsMap.put("voicePlayer",((Double)map.get("speaker")).intValue());
        updateParkParamsMap.put("inLedShowType",((Double)map.get("LEDShowContext")).intValue());
        updateParkParamsMap.put("ledShowStopTime",((Double)map.get("LEDShowChargeTime")).intValue());
        updateParkParamsMap.put("leeparkingdboardLen",((Double)map.get("LEDLength")).intValue());
        String params =String.format("params=%s", JsonUtil.mapToJson(updateParkParamsMap));
//        logger.info("update_park_params="+params);
        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7090/yuncsisapi/update_park_params?", params);
//        String result = HttpRequest.getUrlResultPost("http://localhost:7090/yuncsisapi/update_park_params?", params);
        return showResult(result);
    }

    @Override
    public YuncsisResult updateTempchargerules(Map map) {
        Map updateTempchargerulesMap = new HashMap();
        updateTempchargerulesMap.put("flag",map.get("flag"));
        updateTempchargerulesMap.put("cloud_id",map.get("cloudId"));
        updateTempchargerulesMap.put("parkId",map.get("parkId"));
        updateTempchargerulesMap.put("car_type_id",map.get("carTypeId"));
        updateTempchargerulesMap.put("is_Holiday_Use",map.get("isHolidayUse"));
        updateTempchargerulesMap.put("timesec_start",map.get("timesecStart"));
        updateTempchargerulesMap.put("timesec_end",map.get("timesecEnd"));
        updateTempchargerulesMap.put("base0_time",map.get("base0Time"));
        updateTempchargerulesMap.put("base0_time_fee",map.get("base0TimeFee"));
        updateTempchargerulesMap.put("base1_time",map.get("base1Time"));
        updateTempchargerulesMap.put("base1_time_fee",map.get("base1TimeFee"));
        updateTempchargerulesMap.put("base2_time",map.get("base2Time"));
        updateTempchargerulesMap.put("base2_time_fee",map.get("base2TimeFee"));
        updateTempchargerulesMap.put("base3_time",map.get("base3Time"));
        updateTempchargerulesMap.put("base3_time_fee",map.get("base3TimeFee"));
        updateTempchargerulesMap.put("unit1Sectime",map.get("unit1Sectime"));
        updateTempchargerulesMap.put("unit1Time",map.get("unit1Time"));
        updateTempchargerulesMap.put("unit1TimeFee",map.get("unit1TimeFee"));
        updateTempchargerulesMap.put("unit1Maxfee",map.get("unit1Maxfee"));
        updateTempchargerulesMap.put("unit2Sectime",map.get("unit2Sectime"));
        updateTempchargerulesMap.put("unit2Time",map.get("unit2Time"));
        updateTempchargerulesMap.put("unit2TimeFee",map.get("unit2TimeFee"));
        updateTempchargerulesMap.put("unit2Maxfee",map.get("unit2Maxfee"));
        updateTempchargerulesMap.put("unit3Sectime",map.get("unit3Sectime"));
        updateTempchargerulesMap.put("unit3Time",map.get("unit3Time"));
        updateTempchargerulesMap.put("unit3TimeFee",map.get("unit3TimeFee"));
        updateTempchargerulesMap.put("unit3Maxfee",map.get("unit3Maxfee"));
        updateTempchargerulesMap.put("h24_unit_time",map.get("h24UnitTime"));
        updateTempchargerulesMap.put("h24_unit_time_fee",map.get("h24UnitTimeFee"));
        updateTempchargerulesMap.put("surpass_time",map.get("surpassTime"));
        updateTempchargerulesMap.put("unit_time",map.get("unitTime"));
        updateTempchargerulesMap.put("unit_time_fee",map.get("unitTimeFee"));
        updateTempchargerulesMap.put("h24_rule",map.get("h24Rule"));
        updateTempchargerulesMap.put("max_day1_fee",map.get("maxDay1Fee"));
        updateTempchargerulesMap.put("max_dayn_fee",map.get("maxDaynFee"));
        updateTempchargerulesMap.put("max_sectime_fee",map.get("maxSectimeFee"));
        updateTempchargerulesMap.put("is_max_sectime_fee_by_add",map.get("isMaxSectimeFeeByAdd"));
        updateTempchargerulesMap.put("is_max_24_fee_by_add",map.get("isMax24FeeByAdd"));
        updateTempchargerulesMap.put("h24CalMode",map.get("h24CalMode"));
        String params =String.format("params=%s", JsonUtil.mapToJson(updateTempchargerulesMap));
//        logger.info(params);
        String result = HttpUtil.getUrlResultPost("http://yun1.eparking.top:7090/yuncsisapi/update_tempchargerules?", params);
//        String result = HttpRequest.getUrlResultPost("http://localhost:7090/yuncsisapi/update_tempchargerules?", params);


        return showResult(result);
    }

    public YuncsisResult showResult(String result){
        YuncsisResult yuncsisResult = new YuncsisResult();
        if(result.equals("success")){
            yuncsisResult.setRedcode("1");
            yuncsisResult.setSuccess("成功");
        }else{
            yuncsisResult.setRedcode("0");
            yuncsisResult.setSuccess("失败");
        }
        return yuncsisResult;
    }
}
