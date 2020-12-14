package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelTruckSpace;
import com.common.entity.eparkingCloud.TTruckSpace;
import com.eparking.enums.ActionRspEnum;
import com.eparking.exception.ActionRspException;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TTruckSpaceInsideService;
import com.eparking.service.TruckSpaceService;
import com.common.util.BeanCopyUtil;
import com.common.util.ExportExcelUtil;
import com.common.entity.PageBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:562018-9-14
 * @Modified By:
 */
@Service
public class TruckSpaceServiceImpl implements TruckSpaceService {
    private static final Logger logger = LoggerFactory.getLogger(TruckSpaceServiceImpl.class);
    @Autowired
    private TTruckSpaceInsideService tTruckSpaceInsideService;
    @Autowired
    private CustomizeInsideService customizeInsideService;


    /**
     *设置查询条件
     * @param tTruckSpace
     * @return
     */
/*    private TTruckSpaceCriteria setCriteria(TTruckSpace tTruckSpace){
        //查询全部车位信息
        TTruckSpaceCriteria tTruckSpaceCriteria= new TTruckSpaceCriteria();
        if(tTruckSpace!=null){
            TTruckSpaceCriteria.Criteria criteria=tTruckSpaceCriteria.createCriteria();
            if(tTruckSpace.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tTruckSpace.getParkId());
            }
            if(tTruckSpace.getParkingState()!=null)
            {
                criteria.andParkingStateEqualTo(tTruckSpace.getParkingState());
            }
            if(tTruckSpace.getParkingSpace()!=null)
            {
                criteria.andParkingSpaceEqualTo(tTruckSpace.getParkingSpace());
            }
            if(tTruckSpace.getCompanyId()!=null)
            {
                criteria.andCompanyIdEqualTo(tTruckSpace.getCompanyId());
            }

        }
        return  tTruckSpaceCriteria;
    }*/
    /**
     *获取数据总量
     * @param tTruckSpace
     * @return
     */
/*    private Integer getCount(TTruckSpace tTruckSpace){
        Integer total =(int)tTruckSpaceMapper.countByExample(setCriteria( tTruckSpace));
        return total;
    }*/

    /**
     * 查询车位信息（分页）
     *
     * @param tTruckSpace
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageBean<TTruckSpace> getTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TTruckSpace> tTruckSpaceList=getTruckSpace(tTruckSpace);
        Integer countNums =getCount(tTruckSpace);
        PageBean<TTruckSpace> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tTruckSpaceList);
        return pageData;*/
        return tTruckSpaceInsideService.getTTruckSpacebyPage(tTruckSpace, page, limit);
    }

    /**
     * 查询车位信息
     *
     * @return
     */
    @Override
    public List<TTruckSpace> getTruckSpace(TTruckSpace tTruckSpace) {
//        logger.info("tTruckSpace:" + tTruckSpace);
//        List<TTruckSpace> tTruckSpaceList=tTruckSpaceMapper.selectByExample(setCriteria(tTruckSpace));
        List<TTruckSpace> tTruckSpaceList = tTruckSpaceInsideService.getTTruckSpace(tTruckSpace);
        return tTruckSpaceList;
    }


    /**
     * 更新车位信息
     *
     * @param tTruckSpace
     * @return
     */
    @Override
    public String UpdateTruckSpace(TTruckSpace tTruckSpace) {
/*        logger.info(tTruckSpace.toString());
        if(tTruckSpace.getId()!=null){
            tTruckSpaceMapper.updateByPrimaryKeySelective(tTruckSpace);
        }
        else
        {
            tTruckSpace.setTheirCarPlate(" ");
            tTruckSpace.setUseCarPlate(" ");
            tTruckSpaceMapper.insertSelective(tTruckSpace);
        }
        return "更新成功";*/
        return tTruckSpaceInsideService.UpdateTTruckSpace(tTruckSpace);
    }

    /**
     * 删除车位信息
     *
     * @param tTruckSpace
     * @return
     */
    @Override
    public String DeleteTruckSpace(TTruckSpace tTruckSpace) {
/*        tTruckSpaceMapper.deleteByPrimaryKey(id);
        return "删除成功";*/
        return tTruckSpaceInsideService.DeleteTTruckSpace(tTruckSpace);
    }

    /**
     * 导入车位信息
     *
     * @return
     */
    @Override
    public String ImportTruckSpace(String fileName, MultipartFile file, Integer parkId, Integer companyId) throws IOException {
        boolean notNull = false;
        int k = 0;
        int all = 0;

        List<TTruckSpace> tTruckSpaceList = new ArrayList<TTruckSpace>();
        StringBuffer strSql = new StringBuffer();
        String column = "parking_state,parking_space,park_code,company_id,park_id,their_car_plate,use_car_plate";

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new ActionRspException(ActionRspEnum.Execl_ERROR1);
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
           /* logger.info(file.toString());
            //获取绝对路径
            String basePath = System.getProperty("catalina.home")+"/webapps/";
            //创建存储文件路径
            String filePath=basePath+"TempExecl/";
            File tempFile = new File(filePath);
            //判断文件夹是否存在
            if(!tempFile.exists())
            {
                //不存在则创建
                tempFile.mkdirs();
            }
            File targetFile = null;
            int pos =fileName.lastIndexOf(".");
            //获取文件后缀
            String suffix=fileName.substring(pos);
            //生成存储文件名
            String name ="temp" + suffix;
            //保存文件
            targetFile = new File(filePath, name);
            file.transferTo(targetFile);*/
        //开始读取Execl
        logger.info("开始读取Execl");
        //InputStream is = file.getInputStream();
        logger.info("开始判断版本");
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(file.getInputStream());
        } else {
            wb = new XSSFWorkbook(file.getInputStream());
        }
        logger.info("版本判断结束");
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            TTruckSpace tTruckSpace;
            logger.info("开始循环");
            all = sheet.getLastRowNum();
            Map mapParkCode = new HashMap();
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                try {
                    Row row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    tTruckSpace = new TTruckSpace();
                    Integer parkingState = (int) row.getCell(0).getNumericCellValue();//车位状态
                    logger.info(parkingState.toString());
              /*  switch(parkingState){
                    case "已售":
                        tTruckSpace.setParkingState(0);
                        break;
                    case "在租":
                        tTruckSpace.setParkingState(1);
                        break;
                    case "免费":
                        tTruckSpace.setParkingState(2);
                        break;
                    case "空闲":
                        tTruckSpace.setParkingState(3);
                        break;
                    default:*/
                    tTruckSpace.setParkingState(parkingState);
                    strSql.append("(" + parkingState + ",");
                    /*   break;
                }*/
                    Integer parkingSpace = (int) row.getCell(1).getNumericCellValue();//车位性质
                    logger.info(parkingSpace.toString());
               /* switch(parkingSpace){
                    case "子母车位":
                        tTruckSpace.setParkingSpace(0);
                        break;
                    case "机械车位":
                        tTruckSpace.setParkingSpace(1);
                        break;
                    case "普通车位":
                        tTruckSpace.setParkingSpace(2);
                        break;
                    default:*/
                    tTruckSpace.setParkingSpace(Integer.valueOf(parkingSpace));
                    strSql.append(parkingSpace + ",");
                 /*       break;
                }*/
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    Cell cell = row.getCell(2);
                    String parkCode = cell.getStringCellValue();//车位编码
                    String theirCarPlate = row.getCell(3).getStringCellValue();//绑定车牌
                    String useCarPlate = row.getCell(4).getStringCellValue();//使用车牌
                    logger.info(parkCode.toString());
                    tTruckSpace.setParkCode(parkCode);
                    tTruckSpace.setCompanyId(companyId);
                    tTruckSpace.setParkId(parkId);
                    tTruckSpace.setTheirCarPlate(theirCarPlate);
                    tTruckSpace.setUseCarPlate(useCarPlate);
                    strSql.append("'" + parkCode + "',");
                    strSql.append(companyId + ",");
                    strSql.append(parkId + ",");
                    strSql.append("'" + theirCarPlate + "',");
                    strSql.append("'" + useCarPlate + "')");
                    if (r != sheet.getLastRowNum()) {
                        strSql.append(",");
                    }
                } catch (Exception e) {
                    k++;
                    logger.info(e.toString());
                    //throw new ActionRspException(ActionRspEnum.Execl_ERROR);
                }
            }
        }
//        customizeMapper.insertExcel("t_truck_space",column,strSql.toString());
        customizeInsideService.insertExcel("t_truck_space", column, strSql.toString());
            /*for (TTruckSpace truckSpace:tTruckSpaceList) {
                tTruckSpaceMapper.insertSelective(truckSpace);
            }*/
        return "一共" + all + "条数据，成功导入" + (all - k) + "条，失败" + k + "条";
    }

    /**
     * 导出车位信息
     *
     * @param tTruckSpace
     * @param title
     * @param response
     */
    @Override
    public void exportList(TTruckSpace tTruckSpace, String title, HttpServletResponse response) {
        List<TTruckSpace> tTruckSpaceList = getTruckSpace(tTruckSpace);
        List<ExcelTruckSpace> excelTruckSpaceList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelTruckSpace防止数组越界
        if (tTruckSpaceList.size() <= 0) {
            ExcelTruckSpace excelTruckSpace = new ExcelTruckSpace();
            excelTruckSpaceList.add(excelTruckSpace);
        } else {
            for (TTruckSpace tTruckSpaceNew : tTruckSpaceList) {
                ExcelTruckSpace excelTruckSpace = new ExcelTruckSpace();
                try {
                    excelTruckSpace = (ExcelTruckSpace) BeanCopyUtil.CopyBeanToBean(tTruckSpaceNew, excelTruckSpace);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelTruckSpaceList.add(excelTruckSpace);
            }
        }
//        logger.info("tTruckSpaceList:"+tTruckSpaceList);
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelTruckSpaceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TTruckSpace selectByPrimaryKey(Integer id) {
//        return tTruckSpaceMapper.selectByPrimaryKey(id);
        return tTruckSpaceInsideService.getTTruckSpaceById(id);
    }
}
