package com.eparking.service.impl;

import com.eparking.service.CompanyNoticeService;
import org.springframework.stereotype.Service;

@Service
public class CompanyNoticeServiceImpl implements CompanyNoticeService {

/*    @Autowired
    private TCompanyNoticeMapper tCompanyNoticeMapper;

    *//**
     * 设置查询条件
     * @param tCompanyNotice
     * @return
     *//*
    private TCompanyNoticeCriteria setCriteria(TCompanyNotice tCompanyNotice){
        TCompanyNoticeCriteria tCompanyNoticeCriteria = new TCompanyNoticeCriteria();
        if(tCompanyNotice!=null){
            TCompanyNoticeCriteria.Criteria criteria = tCompanyNoticeCriteria.createCriteria();
            if(tCompanyNotice.getParkId()!=null) {
                criteria.andParkIdEqualTo(tCompanyNotice.getParkId());
            }
            if (tCompanyNotice.getMemberId()!=null){
                criteria.andMemberIdEqualTo(tCompanyNotice.getMemberId());
            }
            if (tCompanyNotice.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tCompanyNotice.getCompanyId());
            }
            if(tCompanyNotice.getTitle()!=null && tCompanyNotice.getTitle()!=""){
                criteria.andTitleLike("%"+tCompanyNotice.getTitle()+"%");
            }
            if (tCompanyNotice.getContent()!=null && tCompanyNotice.getContent()!=""){
                criteria.andContentLike("%"+tCompanyNotice.getContent()+"%");
            }
            if (tCompanyNotice.getCreateTime()!=null && tCompanyNotice.getCreateTime()!="") {
                criteria.andCreateTimeEqualTo(tCompanyNotice.getCreateTime());
            }
            if (tCompanyNotice.getUserId()!=null){
                criteria.andUserIdEqualTo(tCompanyNotice.getUserId());
            }
            if (tCompanyNotice.getUpdateTime()!=null && tCompanyNotice.getUpdateTime()!=""){
                criteria.andUpdateTimeEqualTo(tCompanyNotice.getUpdateTime());
            }
        }
        return  tCompanyNoticeCriteria;
    }
    *//**
     * 获取数据总量
     * @param tCompanyNotice
     * @return
     *//*
    private Integer getCount(TCompanyNotice tCompanyNotice){
        Integer total =(int)tCompanyNoticeMapper.countByExample(setCriteria(tCompanyNotice));
        return total;
    }

    @Override
    public PageBean<TCompanyNotice> getCompanyNoticebyPage(TCompanyNotice tCompanyNotice, Integer page, Integer limit) {
        PageHelper.startPage(page, limit,"create_time desc");
        List<TCompanyNotice> tCompanyNoticeLists=getCompanyNotice(tCompanyNotice);
        Integer countNums =getCount(tCompanyNotice);
        PageBean<TCompanyNotice> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCompanyNoticeLists);
        return pageData;
    }

    @Override
    public List<TCompanyNotice> getCompanyNotice(TCompanyNotice tCompanyNotice) {
        List<TCompanyNotice> tCompanyNoticeLists = tCompanyNoticeMapper.selectByExample(setCriteria(tCompanyNotice));
        return tCompanyNoticeLists;
    }

    @Override
    public String updateCompanyNotice(TCompanyNotice tCompanyNotice) {
        String msg="";
        if (tCompanyNotice.getId()!=null){//编辑
            tCompanyNoticeMapper.updateByPrimaryKeySelective(tCompanyNotice);
            msg = "编辑成功";
        }else{//新增
            tCompanyNoticeMapper.insertSelective(tCompanyNotice);
            msg = "成功添加";
        }
        return msg;
    }

    @Override
    public String deleteCompanyNotice(TCompanyNotice tCompanyNotice) {
        tCompanyNotice = tCompanyNoticeMapper.selectByPrimaryKey(tCompanyNotice.getId());
        tCompanyNoticeMapper.deleteByPrimaryKey(tCompanyNotice.getId());
        return "删除成功";
    }*/
}
