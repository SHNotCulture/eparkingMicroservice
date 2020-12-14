package com.eparking.insideService;


import com.eparking.insideService.impl.MqttClientInsideServiceImpl;
import com.common.util.Common;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "eparkingMqttClient",fallback = MqttClientInsideServiceImpl.class)
public interface MqttClientInsideService {
    String client = Common.Feign_eparkingMqttClient;
    @PostMapping(value = client + "/inside/SendCarFeeRequst")
    void SendCarFeeRequst(@RequestBody Map map);
    @PostMapping(value = client + "/inside/SendCalFeeCurrentCar")
    void SendCalFeeCurrentCar(@RequestBody Map map);
    @PostMapping(value = client + "/inside/SendCouponUpdate")
    void SendCouponUpdate(@RequestBody Map map);
    @PostMapping(value = client + "/inside/reservedCar")
    String reservedCar(@RequestBody Map map);
    @PostMapping(value = client + "/inside/carOwnerNosensePay")
    String carOwnerNosensePay(@RequestBody Map map);
    @PostMapping(value = client + "/inside/bindingCar")
    String bindingCar(@RequestBody Map map);
}
