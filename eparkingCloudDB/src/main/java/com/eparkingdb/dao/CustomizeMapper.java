package com.eparkingdb.dao;

import com.common.entity.eparkingCloud.*;
import com.eparkingdb.dao.sqlProvider.CustomizeSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

public interface CustomizeMapper {

    @SelectProvider(type=CustomizeSqlProvider.class, method="selectMonthlyNum")
    TCustomize selectMonthlyNum(String parkId);
    @SelectProvider(type=CustomizeSqlProvider.class, method="parkTiggerForTask1")
    void  parkTiggerForTask1(String parkId);
    @SelectProvider(type=CustomizeSqlProvider.class, method="parkTiggerForTask2")
    void  parkTiggerForTask2(String parkId);
    @SelectProvider(type=CustomizeSqlProvider.class, method="parkTiggerForInOut1")
    void  parkTiggerForInOut1(String parkId);
    @SelectProvider(type=CustomizeSqlProvider.class, method="parkTiggerForInOut2")
    void  parkTiggerForInOut2(String parkId);
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectMainNum")
    TCustomize selectMainNum(String parkId);

    @SelectProvider(type=CustomizeSqlProvider.class, method="selectHistogram")
    List<Histogram> selectHistogram(String parkId, String year, String type);

    @SelectProvider(type=CustomizeSqlProvider.class, method="selectCompanyPark")
    Map selectCompanyPark(String parkId);

    @UpdateProvider(type=CustomizeSqlProvider.class, method="updateCompanyPark")
    int updateCompanyPark(String parkId, String column, Integer msg);

    @InsertProvider(type=CustomizeSqlProvider.class, method="paymentForDay")
    int paymentForDay();
    @SelectProvider(type=CustomizeSqlProvider.class, method="findParkPortInId")
    List<TParkPort> findParkPortInId(String ids);

    @InsertProvider(type=CustomizeSqlProvider.class, method="insetParktable")
    int insetParktable(Integer parkId, Integer parkCarId, ParkCarTask parkCarTask, String computerIndex);

    /**
     * 黑名单生成任务
     * @param parkId
     * @return
     */
    @InsertProvider(type=CustomizeSqlProvider.class, method="insertBlackTask")
    int insertBlackTask(Integer parkId, BlackListTask blackListTask, String pk);

    /**
     * 查停车记录今日缴费分类别
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectParkingRecordNum")
    Map selectParkingRecordNum(String parkid);
    /**
     * 查询快到期的月租车
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectCloseParkCar")
    List<TParkCar> selectCloseParkCar(String closeDays, String payRule, String parkid);
    /**
     * 查询快到期的月租车数量
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectCloseParkCarCount")
    long selectCloseParkCarCount(String closeDays, String payRule, String parkid);
    /**
     * 批量修改通道权限
     */
    @UpdateProvider(type=CustomizeSqlProvider.class, method="Resetport")
    int Resetport(String portids, String ids);
    /**
     * 月底前到期数量
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getMaturityNo")
    int getMaturityNo(String parkid);

    /**
     * 今日车流量
     * @param parkid
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectTotalNum")
    int selectTotalNum(String parkid);
    /**
     * 今日分类别类别缴费
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectPayByType")
    Map selectPayByType(String parkid);
    /**
     *近七日车流量
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectSevenDays")
    List<Map> selectSevenDays(String parkid);
    /**
     * 查看分类型缴费的次数（微信、支付宝、银联、现金）
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectePayType")
    Map selectePayType(String parkid);
    /**
     * 总车位，剩余车位
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectCarNum")
    Map selectCarNum(String parkid);

    /**
     * 查询首页大部分数据
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectMainAllNum")
    Map selectMainAllNum(String parkid);

    /**
     * 查询在场车辆打印内容
     * @param parkid
     * @return
     */
//    @SelectProvider(type=CustomizeSqlProvider.class, method="selectExportPresentCar")
//    Map selectExportPresentCar(String parkid);

    /**
     * 当班对账和电子对账查看详情
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectDetail")
    List<TParkInOut> selectDetail(String parkId, String whereType);

    /**
     * 当班对账和电子对账查看详情数目
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectDetailNum")
    Integer selectDetailNum(String parkId, String whereType);

    /**
     * 模糊搜索 月租车
     */
    /*@SelectProvider(type=CustomizeSqlProvider.class, method="fuzzyParkCar")
    List<TParkCar> fuzzyParkCar(String key);*/

    /**
     * 搜索在场车辆
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectPresentCar")
    List<TParkInOut> selectPresentCar(String parkId, String andSql);

    /* *//**
     * 搜索在场车辆总数
     * @return
     *//*
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectPresentCar")
    Integer selectPresentCarNum(String parkId,String andSql);*/

    /**
     * Excel导入文件批量插入数据库
     * @return
     */
    @InsertProvider(type=CustomizeSqlProvider.class, method="insertExcel")
    Integer insertExcel(String tableName, String column, String insertSql);

    /**
     * 搜索在场记录（根据车牌）
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectPresentCarByCarplate")
    TParkInOut selectPresentCarByCarplate(String parkId, String carplate);

    /**
     * 模糊搜索在场车牌（根据车牌）
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectPresentCarLikeCarplate")
    List<String> selectPresentCarLikeCarplate(Integer parkId, String carplate);

    /**
     * 查询商户最接近过期的电子劵
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getTBusineTicketExpireSoon")
    Integer getTBusineTicketExpireSoon(Integer busineId, Integer ticketId);

    /**
     * 查询商户最接近过期的电子劵
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getTBusineTicketExpireSoonAPI")
    Integer getTBusineTicketExpireSoonAPI(BusineCouponGetTicketInfo busineCouponGetTicketInfo);

    /**
     * 按月份查询支出金额
     * @param tBusinesCoupon
     * @param
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getCouponPayMonth")
    List<Map> getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year);

    /**
     * 按天数查询支出金额
     * @param tBusinesCoupon
     * @param year
     * @param month
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getCouponPayDay")
    List<Map> getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month);

    /**
     * 查询商户累计消费金额
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getCouponSumPay")
    Double getCouponSumPay(TBusinesCoupon tBusinesCoupon);

    /**
     * 查询商户累计充值金额
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getCouponNeedPay")
    Double getCouponNeedPay(TBusinesCoupon tBusinesCoupon);

    /**
     * 日报统计查询1：临时车收费
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay1")
    TParkInOut getParkInOutPay1(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询2：月卡车收费
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay2")
    TCarPayment getParkInOutPay2(Integer parkId, String date1, String date2, Integer payType);
    /**
     * 日报统计查询3：电子支付
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay3")
    TCarPayment getParkInOutPay3(Integer parkId, String date1, String date2, Integer payType);
    /**
     * 日报统计查询4：预缴
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay4")
    TParkReport getParkInOutPay4(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询5：月租电子
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay5")
    TParkReport getParkInOutPay5(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询6：闪付
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay6")
    TParkReport getParkInOutPay6(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询7：支付次数
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkInOutPay7")
    TCompanyPark getParkInOutPay7(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询8：新增月租车辆
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getParkNum")
    Integer getParkNum(Integer parkId, String date);
    /**
     * 日报统计查询9：免费车辆数
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getFreeTotal")
    Integer getFreeTotal(Integer parkId, String date1, String date2);
    /**
     * 日报统计查询10：
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getBusinePay")
    Integer getBusinePay(Integer parkId, String date1, String date2);

    @SelectProvider(type=CustomizeSqlProvider.class, method="deleteReport")
    Integer deleteReport(Integer parkId, String date);

    @SelectProvider(type=CustomizeSqlProvider.class, method="selectReport")
    List<TParkReport> selectReport(Integer parkId, String date);

    /**
     * 日报对查询的时间范围进行统计
     * @param parkId
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectReportSum")
    Map selectReportSum(Integer parkId, String startDate, String endDate, String tabelName);

    /**
     * 新增车牌时对该车牌验证是否已存在
     * @param parkId
     * @param carplate
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getTParkCarByCarplate")
    List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate);

    /**
     * 黑名单新增车牌时对该车牌验证是否已存在
     * @param parkId
     * @param carplate
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getTBlacklistByCarplate")
    List<TBlacklist> getTBlacklistByCarplate(Integer parkId, String carplate);

    /**
     * 按车辆性质统计缴费明细报表
     * @param
     * @param
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="chargeReportStatistics")
    List<Map> chargeReportStatistics(Integer parkId, String beginDate, String endDate);

    /**
     * 按当班员统计查询
     * @param
     * @param
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="getDutyStatistics")
    List<Map> getDutyStatistics(Integer parkId, String dutyPerson, String beginTime, String endTime);

    @SelectProvider(type=CustomizeSqlProvider.class, method="getDutyStatisticsNum")
    Integer getDutyStatisticsNum(Integer parkId, String dutyPerson, String beginTime, String endTime);

    /**
     * 微信优惠下发任务
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="insetCouponble")
    Integer insetCouponble(Integer parkId, Shop shop, String computerIndex);

    /**
     * 查询优惠下发任务
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="selectCouponble")
    List<Map<String,String>> selectCouponble(Integer parkId);

    /**
     * 删除优惠下发任务
     * @return
     */
    @SelectProvider(type=CustomizeSqlProvider.class, method="DeleteCouponble")
    Integer DeleteCouponble(Integer parkId, String taskId);
}
