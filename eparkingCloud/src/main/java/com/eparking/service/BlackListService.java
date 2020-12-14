package com.eparking.service;

import com.common.entity.eparkingCloud.TBlacklist;
import com.common.entity.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BlackListService {
    PageBean<TBlacklist> getBlackListbyPage(TBlacklist tBlacklist, Integer page, Integer limit);
    List<TBlacklist> getBlackList(TBlacklist tBlacklist);
    String updateBlackList(TBlacklist tBlacklist);
    String deleteBlackList(TBlacklist tBlacklist);
    void exportList(TBlacklist tBlacklist, String title, HttpServletResponse response);
    TBlacklist selectByPrimaryKey(Integer id);
}
