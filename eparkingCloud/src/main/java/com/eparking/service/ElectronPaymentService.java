package com.eparking.service;

import com.common.entity.eparkingCloud.TElectronPayment;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:142018-11-6
 * @Modified By:
 */
public interface ElectronPaymentService {
    PageBean<TElectronPayment> getElectronPaymentbyPage(TElectronPayment tElectronPayment, String beginTime, String endTime, Integer page, Integer limit);

    List<TElectronPayment> getElectronPayment(String beginTime, String endTime, TElectronPayment tElectronPayment);

    String UpdateElectronPayment(TElectronPayment tElectronPayment);

    void exportList(String beginTime, String endTime, TElectronPayment tElectronPayment, String title, HttpServletResponse response);
}
