package com.eparking.service;


import com.common.entity.eparkingCloud.TCarPayment;
import com.common.entity.eparkingCloud.TCustomize;
import com.common.entity.eparkingCloud.TParkCar;
import com.common.entity.PageBean;
import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface MonthlyCarService {
    PageBean<TParkCar> getTParkCarlistbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);
    List<TParkCar> getTParkCarlist(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd);
    TCustomize getCarNum(String parkid);
    TParkCar UpdateTParkCar(TParkCar tParkCar);
    List<TParkCar> getCloseParkCar(String closeType, String payRule, String parkid);
    PageBean<TParkCar> getCloseParkCarbyPage(String closeType, String payRule, String parkid, Integer page, Integer limit);
    JSONObject getNeedPay(Integer payStandard, Integer payCount, TParkCar tParkCar);
    String Resetport(String portids, String ids);
    Integer getMaturityNo(String parkid);
    void exportList(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, String title, HttpServletResponse response);//导出
    String importMonthlyCar(String fileName, MultipartFile file, Integer parkID, String payRule, String portId) throws IOException;//导入
    TParkCar getTParkCarById(Integer id);
    String correct(TParkCar tParkCar, Double needPay, Short payType, String remark);//冲正
    String refund(TParkCar parkCar, TCarPayment carPayment, String isRefund);//删除
    String recharge(TCarPayment carPayment, TParkCar parkCar);//充值
    List<TParkCar> whetherParkCar(Integer parkId, String carPlate, String endDateSelectStart, String endDateSelectEnd);
    String monthlyPrepayment(Integer parkId, String carPlate, Double needPay, Double actualPay, Integer ePayType, String beginDate, String endDate, Integer monthNum, Integer payRule, Integer companyId);
    Integer getPresentCarNum(List<Integer> parkIDlist);
    String monthlyWalletRecharge(Integer id, Double needPay, Double actualPay, String remark, String rechargeType);
    List<TParkCar> getTParkCarByCarplate(Integer parkId, String carplate);
    void syncParkCarTask(TParkCar parkCar);
    PageBean<TParkCar> getOwnnerCarbyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);
    List<TParkCar> getOwnerCar(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd);
    List<TParkCar> getTParkCarForStatistics(TParkCar tParkCar, String date);
    PageBean<TParkCar> getTParkCarFuzzybyPage(TParkCar tParkCar, String endDateSelectStart, String endDateSelectEnd, Integer page, Integer limit);
}
