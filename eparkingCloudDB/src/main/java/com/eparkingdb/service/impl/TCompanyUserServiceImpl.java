package com.eparkingdb.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.eparkingCloud.TCompanyUserCriteria;
import com.eparkingdb.dao.TCompanyUserMapper;
import com.common.enums.ActionRspEnum;
import com.common.exception.ActionRspException;
import com.eparkingdb.service.TCompanyUserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李书瀚
 * @Description: TCompanyUserService接口实现类
 * @date 2020/04/08 17:24
 */
@Service
public class TCompanyUserServiceImpl implements TCompanyUserService {

    private static final Logger logger = LoggerFactory.getLogger(TCompanyUserServiceImpl.class);
    @Autowired
    private TCompanyUserMapper tCompanyUserMapper;

    /**
     * 设置查询条件
     *
     * @param tCompanyUser
     * @return
     */
    private TCompanyUserCriteria setCriteria(TCompanyUser tCompanyUser) {
        TCompanyUserCriteria tCompanyUserCriteria = new TCompanyUserCriteria();
        if (tCompanyUser != null) {
            TCompanyUserCriteria.Criteria criteria = tCompanyUserCriteria.createCriteria();
            if (tCompanyUser.getId() != null) {
                criteria.andIdEqualTo(tCompanyUser.getId());
            }
            if (tCompanyUser.getCompanyId() != null) {
                criteria.andCompanyIdEqualTo(tCompanyUser.getCompanyId());
            }
            if (tCompanyUser.getUserAccout() != null) {
                criteria.andUserAccoutEqualTo(tCompanyUser.getUserAccout());
            }
            if (tCompanyUser.getEntityType() != null) {
                criteria.andEntityTypeEqualTo(tCompanyUser.getEntityType());
            }
            if (tCompanyUser.getIsAdmin() != null) {
                criteria.andIsAdminEqualTo(tCompanyUser.getIsAdmin());
            }
            if (tCompanyUser.getUserName() != null) {
                criteria.andUserNameEqualTo(tCompanyUser.getUserName());
            }
            if (tCompanyUser.getPassword() != null) {
                criteria.andPasswordEqualTo(tCompanyUser.getPassword());
            }
        }
        return tCompanyUserCriteria;
    }

    /**
     * 获取数据总量
     *
     * @param tCompanyUser
     * @return
     */
    private Integer getCount(TCompanyUser tCompanyUser) {
        Integer total = (int) tCompanyUserMapper.countByExample(setCriteria(tCompanyUser));
        return total;
    }

    /**
     * 查询(分页)tCompanyUser
     *
     * @param tCompanyUser
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TCompanyUser> getTCompanyUserbyPage(TCompanyUser tCompanyUser, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "id desc");
        List<TCompanyUser> TCompanyUsers = getTCompanyUser(tCompanyUser);
        Integer countNums = getCount(tCompanyUser);
        PageBean<TCompanyUser> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(TCompanyUsers);
        return pageData;
    }

    /**
     * 查询tCompanyUser
     *
     * @param tCompanyUser
     * @return
     */
    public List<TCompanyUser> getTCompanyUser(TCompanyUser tCompanyUser) {
        List<TCompanyUser> tCompanyUsers = tCompanyUserMapper.selectByExample(setCriteria(tCompanyUser));
        return tCompanyUsers;
    }

    /**
     * 更新tCompanyUser
     *
     * @param tCompanyUser
     * @return
     */
    public String UpdateTCompanyUser(TCompanyUser tCompanyUser) {
        String msg = "";
        try {
            if (tCompanyUser.getId() != null) {
                tCompanyUserMapper.updateByPrimaryKeySelective(tCompanyUser);
                msg = "更新成功";
            } else {
                tCompanyUserMapper.insertSelective(tCompanyUser);
                msg = "新建成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 删除tCompanyUser
     *
     * @param tCompanyUser
     * @return
     */
    public String DeleteTCompanyUser(TCompanyUser tCompanyUser) {
        String msg = "";
        if (tCompanyUser.getId() != null) {
            tCompanyUserMapper.deleteByPrimaryKey(tCompanyUser.getId());
            msg = "删除成功";
        }
        return msg;
    }

    /**
     * 根据ID查询tCompanyUser
     *
     * @param id
     * @return
     */
    public TCompanyUser getTCompanyUserByID(Integer id) {
        return tCompanyUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public TCompanyUser CheckPassword(TCompanyUser tCompanyUser) {
        TCompanyUserCriteria tCompanyUserCriteria = new TCompanyUserCriteria();
        try {
            if (tCompanyUser != null) {
                TCompanyUserCriteria.Criteria criteria = tCompanyUserCriteria.createCriteria();
                criteria.andUserAccoutEqualTo(tCompanyUser.getUserAccout());
                criteria.andPasswordEqualTo(tCompanyUser.getPassword());
            } else {
                throw new ActionRspException(ActionRspEnum.Login_ERROR);
            }
            List<TCompanyUser> tCompanyUserList = tCompanyUserMapper.selectByExample(tCompanyUserCriteria);
            if (tCompanyUserList.size() == 1) {
                tCompanyUser = tCompanyUserList.get(0);

            } else {
                throw new ActionRspException(ActionRspEnum.Login_ERROR);
            }
        } catch (Exception e) {

        }
        return tCompanyUser;
    }
}
