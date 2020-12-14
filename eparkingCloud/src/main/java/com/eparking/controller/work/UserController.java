package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.eparkingCloud.TRolePowerNew;
import com.eparking.enums.ActionRspEnum;
import com.eparking.exception.ActionRspException;
import com.eparking.insideService.TCompanyUserInsideService;
import com.eparking.insideService.TRolePowerNewInsideService;
import com.eparking.service.RolePowerService;
import com.eparking.service.UserService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 21:322018-8-14
 * @Modified By:
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    private  static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RolePowerService rolePowerService;
    @Autowired
    private TRolePowerNewInsideService tRolePowerNewInsideService;
    @Autowired
    private TCompanyUserInsideService tCompanyUserInsideService;
    /**
     * 查询角色信息(分页)
     * @return
     */
    @PostMapping(value = "/getRolePowerbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询角色信息（分页）")
    public ControllerRsp getRolePowerbyPage(TRolePowerNew tRolePower, Integer page, Integer limit){
        TCompanyUser user= SessionUtil.getUser();
        tRolePower.setCompanyId(user.getCompanyId());
        return ControllerRspUtil.Success(rolePowerService.getRolePowerbyPage(tRolePower,page,limit));
//        return tRolePowerNewInsideService.getTRolePowerNewbyPage(tRolePower,page,limit);
    }

    /**
     * 查询角色信息
     * @return
     */
    @PostMapping(value = "/getRolePower")
    @HttpLog(operationType = "0",modularTypeName = "查询角色信息")
    public ActionRsp getRolePower(TRolePowerNew tRolePower){
        TCompanyUser user=SessionUtil.getUser();
        tRolePower.setCompanyId(user.getCompanyId());
//        return ControllerRspUtil.Success(rolePowerService.getRolePower(tRolePower));
//        return tRolePowerNewInsideService.getTRolePowerNew(tRolePower);
        return ActionRspUtil.Success(rolePowerService.getRolePower(tRolePower));
    }
    /**
     * 更新角色信息
     * @param tRolePower
     * @return
     */
    @PostMapping(value = "/updateRolePower")
    @HttpLog(operationType = "1",modularTypeName = "更新角色信息")
    public ActionRsp UpdateRolePower(TRolePowerNew tRolePower)
    {
        TCompanyUser user= SessionUtil.getUser();
        tRolePower.setCompanyId(user.getCompanyId());
        tRolePower.setUserId(user.getId());
        return ActionRspUtil.Success(rolePowerService.UpdateRolePower(tRolePower));
//        return tRolePowerNewInsideService.UpdateTRolePowerNew(tRolePower);
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteRolePower")
    @HttpLog(operationType = "1",modularTypeName = "删除角色信息")
    public ActionRsp DeleteRolePower(Integer id){
//        return ActionRspUtil.Success(rolePowerService.DeleteRolePower(id));
        TRolePowerNew tRolePowerNew = new TRolePowerNew();
        tRolePowerNew.setId(id);
//        return tRolePowerNewInsideService.DeleteTRolePowerNew(tRolePowerNew);
        return ActionRspUtil.Success(rolePowerService.DeleteRolePower(tRolePowerNew));
    }

    /**
     * 查询物业用户信息(分页)
     * @return
     */
    @PostMapping(value = "/getCompanyUserbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询物业用户信息（分页）")
    public ControllerRsp getCompanyUserbyPage(TCompanyUser tCompanyUser,Integer page, Integer limit){
        TCompanyUser user=SessionUtil.getUser();
        tCompanyUser.setEntityType(1);
        tCompanyUser.setCompanyId(user.getCompanyId());
        return ControllerRspUtil.Success(userService.getCompanyUserbyPage(tCompanyUser,page,limit));
//        return tCompanyUserInsideService.getTCompanyUserbyPage(tCompanyUser,page,limit);
    }
    /**
     * 查询物业用户信息
     * @return
     */
    @PostMapping(value = "/getCompanyUser")
    @HttpLog(operationType = "0",modularTypeName = "查询物业用户信息")
    public ActionRsp getCompanyUser(TCompanyUser tCompanyUser){
        TCompanyUser user= SessionUtil.getUser();
        tCompanyUser.setEntityType(1);
        tCompanyUser.setCompanyId(user.getCompanyId());
//        return ControllerRspUtil.Success(userService.getCompanyUser(tCompanyUser));
//        return tCompanyUserInsideService.getTCompanyUser(tCompanyUser);
        return ActionRspUtil.Success(userService.getCompanyUser(tCompanyUser));
    }
    /**
     * 更新物业用户信息
     * @param tCompanyUser
     * @return
     */
    @PostMapping(value = "/updateCompanyUser")
    @HttpLog(operationType = "1",modularTypeName = "更新物业用户信息")
    public ActionRsp UpdateCompanyUser(TCompanyUser tCompanyUser)
    {
        TCompanyUser user=SessionUtil.getUser();
        tCompanyUser.setCompanyId(user.getCompanyId());
        tCompanyUser.setParentUser(user.getId());
        tCompanyUser.setIsAdmin("2");
        tCompanyUser.setEntityType(1);
        //新增用户的话设置初始密码
        if(tCompanyUser.getId()==null){
            tCompanyUser.setPassword(MD5Util.MD5Encode("123456"));
        }
        return ActionRspUtil.Success(userService.UpdateCompanyUser(tCompanyUser));
//        return tCompanyUserInsideService.UpdateTCompanyUser(tCompanyUser);
    }
    /**
     * 修改用户密码
     * @param password
     * @param newPassword
     * @param resetPassword
     * @return
     */
    @PostMapping(value = "/changePassword")
    @HttpLog(operationType = "1",modularTypeName = "修改用户密码")
    public ActionRsp changePassword(Integer id, String password, String newPassword, String resetPassword){
        TCompanyUser nowUser= SessionUtil.getUser();
//        logger.info("nowUser:"+nowUser.toString());
        TCompanyUser user=new TCompanyUser();
        user.setId(id);
        TCompanyUser userChange=new TCompanyUser();
        userChange=userService.getCompanyUser(user).get(0);
//        userChange=((List<TCompanyUser>)tCompanyUserInsideService.getTCompanyUser(user).getResult()).get(0);
        userChange.setParentUser(nowUser.getId());
//        logger.info("userChange:"+userChange.toString());
        if(userChange.getPassword().equals(MD5Util.MD5Encode(password)))
        {
            if(newPassword.equals(resetPassword)){
                userChange.setPassword(MD5Util.MD5Encode(newPassword));
                userService.UpdateCompanyUser(userChange);
//                tCompanyUserInsideService.UpdateTCompanyUser(userChange);
            }
            else
            {
                throw new ActionRspException(ActionRspEnum.Reset_ERROR2);
            }

        }
        else
        {
            throw new ActionRspException(ActionRspEnum.Reset_ERROR1);
        }
        return ActionRspUtil.Success("修改成功");
    }
    /**
     * 重置物业用户密码
     * @param id
     * @return
     */
    @PostMapping(value = "/resetPassword")
    @HttpLog(operationType = "1",modularTypeName = "重置物业用户密码")
    public ActionRsp resetPassword(Integer id)
    {
        TCompanyUser user= SessionUtil.getUser();
        TCompanyUser tCompanyUser=new TCompanyUser();
        tCompanyUser.setId(id);
        tCompanyUser.setPassword(MD5Util.MD5Encode("123456"));
        tCompanyUser.setCompanyId(user.getCompanyId());
        tCompanyUser.setParentUser(user.getId());
       String result=userService.UpdateCompanyUser(tCompanyUser);
//        String result=tCompanyUserInsideService.UpdateTCompanyUser(tCompanyUser).getResult().toString();
       if(result.equals("更新成功")){
           result="密码重置成功,初始密码为123456,,请自行更改密码后使用！";
       }
        return ActionRspUtil.Success(result);
    }

    /**
     * 删除物业用户信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteCompanyUser")
    @HttpLog(operationType = "1",modularTypeName = "删除物业用户信息")
    public ActionRsp DeleteCompanyUser(Integer id){
//        return ActionRspUtil.Success(userService.DeleteCompanyUser(id));
        TCompanyUser tCompanyUser = new TCompanyUser();
        tCompanyUser.setId(id);
//        return tCompanyUserInsideService.DeleteTCompanyUser(tCompanyUser);
        return ActionRspUtil.Success(userService.DeleteCompanyUser(tCompanyUser));
    }
}
