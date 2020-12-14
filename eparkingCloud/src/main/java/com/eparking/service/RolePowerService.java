package com.eparking.service;

import com.common.entity.eparkingCloud.TRolePowerNew;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 19:002018-11-6
 * @Modified By:
 */
public interface RolePowerService {
    PageBean<TRolePowerNew> getRolePowerbyPage(TRolePowerNew tRolePower, Integer page, Integer limit);
    List<TRolePowerNew> getRolePower(TRolePowerNew tRolePower);
    List<TRolePowerNew> getRolePowerById(TRolePowerNew tRolePower);
    String UpdateRolePower(TRolePowerNew tRolePower);
    String DeleteRolePower(TRolePowerNew tRolePower);
    TRolePowerNew selectByPrimaryKey(Integer id);
}
