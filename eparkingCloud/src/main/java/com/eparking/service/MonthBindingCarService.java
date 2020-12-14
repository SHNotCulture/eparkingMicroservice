package com.eparking.service;


import com.common.entity.eparkingCloud.TMonthBindingCar;
import com.common.entity.PageBean;

import java.util.List;

public interface MonthBindingCarService {
    PageBean<TMonthBindingCar> getMonthBindingCarbyPage(TMonthBindingCar tMonthBindingCar, Integer page, Integer limit);
    List<TMonthBindingCar> getMonthBindingCar(TMonthBindingCar tMonthBindingCar);
    String updateMonthBindingCar(TMonthBindingCar tMonthBindingCar);
    String deleteMonthBindingCar(TMonthBindingCar tMonthBindingCar);
    Boolean checkCarplateOnlyOne(TMonthBindingCar tMonthBindingCar);
}
