package com.eparking.service.impl;

import com.eparking.service.TCompanyAreaService;
import org.springframework.stereotype.Service;

/**
* @Description: TCompanyAreaService接口实现类
* @author 谢轩然
* @date 2020/04/23 17:28
*/
@Service
public class TCompanyAreaServiceImpl  implements TCompanyAreaService {

/*    private  static final Logger logger= LoggerFactory.getLogger( TCompanyAreaServiceImpl.class);
    @Autowired
    private TCompanyAreaMapper tCompanyAreaMapper;

    *//**
    * 设置查询条件
    * @param tCompanyArea
    * @return
    *//*
    private  TCompanyAreaCriteria setCriteria(TCompanyArea tCompanyArea){
        TCompanyAreaCriteria tCompanyAreaCriteria= new TCompanyAreaCriteria();
        if(tCompanyArea!=null){
        TCompanyAreaCriteria.Criteria criteria=tCompanyAreaCriteria.createCriteria();
        if(tCompanyArea.getId()!=null){
        criteria.andIdEqualTo(tCompanyArea.getId());
        }

        }
        return  tCompanyAreaCriteria;
    }

    *//**
    * 获取数据总量
    * @param tCompanyArea
    * @return
    *//*
    private Integer getCount(TCompanyArea tCompanyArea){
    Integer total =(int)tCompanyAreaMapper.countByExample(setCriteria(tCompanyArea));
    return total;
    }

    *//**
    *查询tCompanyArea(分页)
    * @param tCompanyArea
    * @param page
    * @param limit
    * @return
    *//*
    public PageBean<TCompanyArea> getTCompanyAreabyPage(TCompanyArea tCompanyArea, Integer page, Integer limit){
        PageHelper.startPage(page, limit,"id desc");
        List<TCompanyArea> tCompanyAreas=getTCompanyArea(tCompanyArea);
            Integer countNums =getCount(tCompanyArea);
            PageBean<TCompanyArea> pageData = new PageBean<>(page, limit, countNums);
            pageData.setItems(tCompanyAreas);
            return pageData;
        }

    *//**
    * 查询tCompanyArea
    * @param tCompanyArea
    * @return
    *//*
    public List<TCompanyArea> getTCompanyArea(TCompanyArea tCompanyArea){
    List<TCompanyArea>  tCompanyAreas=tCompanyAreaMapper.selectByExample(setCriteria(tCompanyArea));
    return tCompanyAreas;
    }

    *//**
    * 更新tCompanyArea
    * @param tCompanyArea
    * @return
    *//*
    public String UpdateTCompanyArea(TCompanyArea tCompanyArea)
    {
            String msg="";
            try{
            if(tCompanyArea.getId()!=null){
            tCompanyAreaMapper.updateByPrimaryKeySelective(tCompanyArea);
                msg="更新TCompanyArea成功";
            }
            else
            {
            tCompanyAreaMapper.insertSelective(tCompanyArea);
                msg="新建TCompanyArea成功";
            }
            }
            catch (Exception e)
            {

            }
            return msg;
    }

    *//**
    * 删除tCompanyArea
    * @param tCompanyArea
    * @return
    *//*
    public String DeleteTCompanyArea(TCompanyArea tCompanyArea){
            String msg="";
            if(tCompanyArea.getId()!=null){
            tCompanyAreaMapper.deleteByPrimaryKey(tCompanyArea.getId());
            msg="删除TCompanyArea成功";
            }
            return msg;
    }

    *//**
    * 根据ID查询tCompanyArea
    * @param id
    * @return
    *//*
    public TCompanyArea getTCompanyAreaByID(Integer id) {
        return  tCompanyAreaMapper.selectByPrimaryKey(id);
    }*/
}
