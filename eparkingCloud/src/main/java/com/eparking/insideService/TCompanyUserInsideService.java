package com.eparking.insideService;

import com.common.entity.ActionRsp;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.impl.TCompanyUserInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TCompanyUserInsideService接口
* @author 谢轩然
* @date 2020/04/20 11:41
*/
@FeignClient(value = "eparkingCloudData",fallback = TCompanyUserInsideServiceImpl.class)
public interface TCompanyUserInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @PostMapping(value = client+"/tCompanyUser/getTCompanyUser")
    List<TCompanyUser> getTCompanyUser(@RequestBody TCompanyUser tCompanyUser);

    /**
    *查询(分页)tCompanyUser
    * @param tCompanyUser
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tCompanyUser/getTCompanyUserbyPage")
    PageBean<TCompanyUser> getTCompanyUserbyPage(@RequestBody TCompanyUser tCompanyUser, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @PostMapping(value = client+"/tCompanyUser/updateTCompanyUser")
    String UpdateTCompanyUser(@RequestBody TCompanyUser tCompanyUser);

    /**
    * 删除tCompanyUser
    * @param tCompanyUser
    * @return
    */
    @PostMapping(value = client+"/tCompanyUser/deleteTCompanyUser")
    String DeleteTCompanyUser(@RequestBody TCompanyUser tCompanyUser);

    /**
     * 根据ID查询物业用户
     * @param
     * @return
     */
    @PostMapping(value = client+"/tCompanyUser/getTCompanyUserByID")
    TCompanyUser getTCompanyUserByID(@RequestParam("id") Integer id);


    /**
     * 核对登录用户信息
     * @param
     * @return
     */
    @PostMapping(value = client+"/tCompanyUser/CheckPassword")
    TCompanyUser CheckPassword(@RequestBody TCompanyUser tCompanyUser);

    /**
     * 修改用户密码
     * @param
     * @return
     */
    @PostMapping(value = client+"/tCompanyUser/changePassword")
    ActionRsp changePassword(@RequestParam("id") Integer id, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("resetPassword") String resetPassword);

    /**
     * 重置物业用户密码
     * @param
     * @return
     */
    @PostMapping(value = client+"/tCompanyUser/resetPassword")
    ActionRsp resetPassword(@RequestParam("id") Integer id);

}