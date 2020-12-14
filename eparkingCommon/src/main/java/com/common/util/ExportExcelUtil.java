package com.common.util;

import com.common.annotation.ExcelTitle;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TCompanyUser;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 10:442018-12-4
 * @Modified By:
 */
public class ExportExcelUtil {

    private  static final Logger logger= LoggerFactory.getLogger(ExportExcelUtil.class);
    /**
     * 使用浏览器选择路径下载
     * @param response
     * @param fileName
     * @param sheetName
     * @param headerName
     * @param list
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String fileName,String sheetName,String headerName, List<?> list) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName+System.currentTimeMillis() + ".xlsx", "utf-8"));
        exportExcel(sheetName,headerName,list, response.getOutputStream());
    }

   /* public static int generateExcel(ExcelData excelData, String path) throws Exception {
        File f = new File(path);
        FileOutputStream out = new FileOutputStream(f);
        return exportExcel(excelData, out);
    }*/

    private static int exportExcel(String sheetName,String headerName, List<?> list, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        int rowIndex = 0;
        try {
            Sheet sheet = wb.createSheet(sheetName);
            rowIndex = writeExcel(wb, sheet, headerName,list);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
        return rowIndex;
    }

    /**
     * 表不显示字段
     * @param wb
     * @param sheet
     * @param data
     * @return
     */
//    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
//        int rowIndex = 0;
//        writeTitlesToExcel(wb, sheet, data.getTitles());
//        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
//        autoSizeColumns(sheet, data.getTitles().size() + 1);
//        return rowIndex;
//    }

    /**
     *表显示字段
     * @param wb
     * @param sheet
     * @param headerName
     * @param list
     * @return
     */
    private static int writeExcel(XSSFWorkbook wb, Sheet sheet, String headerName, List<?> list) {
        int rowIndex = 0;
        List<Method> getterMethods =getMethod(list);
        rowIndex = writeHeaderToExcel(wb, sheet, headerName,getterMethods);
        rowIndex= writeSecondHeaderToExcel(wb, sheet, getterMethods,rowIndex);
        rowIndex = writeTitlesToExcel(wb, sheet, getterMethods,rowIndex);
        rowIndex = writeRowsToExcel(wb, sheet, list, rowIndex);
        autoSizeColumns(sheet, list.size() + 1);
        return rowIndex;
    }

    /**
     * 设置题头
     * @param wb
     * @param sheet
     * @param headerName
     * @param getterMethods
     * @return
     */
    private static int writeHeaderToExcel(XSSFWorkbook wb, Sheet sheet,String headerName, List<Method> getterMethods){

        //----------------标题样式---------------------
        XSSFCellStyle titleStyle = wb.createCellStyle();        //标题样式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font ztFont = wb.createFont();
        ztFont.setItalic(false);                     // 设置字体为斜体字
        ztFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short)16);    // 将字体大小设置为18px
        ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上
        ztFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);    //加粗
        // ztFont.setUnderline(Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
        //ztFont.setStrikeout(true);                  // 是否添加删除线
        titleStyle.setFont(ztFont);
        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex);
        Cell cell = headerRow.createCell(0);
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, getterMethods.size()-1));
        cell.setCellValue(headerName);
        cell.setCellStyle(titleStyle);
        rowIndex++;
        return rowIndex;
    }

    /**
     * 设置二级表头
     * @param wb
     * @param sheet
     * @param getterMethods
     * @return
     */
    private static int writeSecondHeaderToExcel(XSSFWorkbook wb, Sheet sheet, List<Method> getterMethods,int rowIndex){
        //----------------标题样式---------------------
        XSSFCellStyle titleStyle = wb.createCellStyle();        //标题样式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font ztFont = wb.createFont();
        ztFont.setItalic(false);                     // 设置字体为斜体字
        ztFont.setColor(Font.COLOR_NORMAL);            // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short)14);    // 将字体大小设置为18px
        ztFont.setFontName("宋体");             // 将“宋体”字体应用到当前单元格上
        ztFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);    //加粗
        // ztFont.setUnderline(Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
        //ztFont.setStrikeout(true);                  // 是否添加删除线
        titleStyle.setFont(ztFont);
        Row headerRow = sheet.createRow(rowIndex);
        int cellIndex=0;
        //----------------firstTitle---------------------
        Cell cell = headerRow.createCell(cellIndex);
        TCompanyUser user=SessionUtil.getUser();
        String FirstTitle="操作人："+user.getUserName();
        sheet.setColumnWidth(cellIndex, FirstTitle.getBytes().length*2* 256);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex+1));
        cell.setCellValue(FirstTitle);
        cell.setCellStyle(titleStyle);
        cellIndex=cellIndex+2;
        //----------------SecondTitle---------------------
        cell = headerRow.createCell(cellIndex);
        TCompanyPark park=SessionUtil.getPark();
        String SecondTitle="所属车场："+park.getParkName();
        sheet.setColumnWidth(cellIndex, SecondTitle.getBytes().length*2* 256);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex+1));
        cell.setCellValue(SecondTitle);
        cell.setCellStyle(titleStyle);
        cellIndex=cellIndex+2;
        //----------------ThirdTitle---------------------
        cell = headerRow.createCell(cellIndex);
        String ThirdTitle="导出时间："+DateUtil.getCurDateTime();
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.setColumnWidth(cellIndex, ThirdTitle.getBytes().length*2* 256);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, cellIndex, cellIndex+1));
        cell.setCellValue(ThirdTitle);
        cell.setCellStyle(titleStyle);
        cellIndex=cellIndex+2;
        //----------------End---------------------
        rowIndex++;
        return rowIndex;
    }
    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param getterMethods
     * @return
     */
    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet,  List<Method> getterMethods, int rowIndex) {
        Font titleFont = wb.createFont();
        //设置字体
        titleFont.setFontName("simsun");
        //设置粗体
        titleFont.setBoldweight(Short.MAX_VALUE);
        //设置字号
        titleFont.setFontHeightInPoints((short) 14);
        //设置颜色
        titleFont.setColor(IndexedColors.BLACK.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置图案颜色
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //设置图案样式
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        titleRow.setHeightInPoints(25);
        for (int i = 0; i < getterMethods.size(); i++) {
            String value=getterMethods.get(i).getAnnotation(ExcelTitle.class).value();
            Cell cell = titleRow.createCell(i);
            sheet.setColumnWidth(i, value.getBytes().length*2* 256);
            cell.setCellValue(value);
            cell.setCellStyle(titleStyle);
        }
        rowIndex++;
        return rowIndex;
    }
    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<?> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        List<Method> getterMethods =getMethod(rows);
        for (Object obj : rows) {
            //增加一行
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(25);
            //循环类属性
            for (int i =0; i < getterMethods.size(); i++) {
                Cell cell = dataRow.createCell(i);
                String value = null;
                try {
                    value = new String(getterMethods.get(i).invoke(obj, null) + "");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(!value.equals("null")) {
                    value=value;
                } else {
                    value="";
                }
                sheet.setColumnWidth(i, value.getBytes().length*2* 256);
                cell.setCellValue(value);
                cell.setCellStyle(dataStyle);

            }
            rowIndex++;
        }
        return rowIndex;
    }
    /**
     * 获取实体类注解属性
     * @param data
     * @return
     */
    private static List<Method> getMethod(List<?> data){
        //获取导出对象的方法
        Method[] methods = data.get(0).getClass().getDeclaredMethods();
        List<Method> getterMethods = new ArrayList<Method>();
        for(Method method : methods) {
            method.getDeclaredAnnotations();
            if(method.getName().contains("get") && method.isAnnotationPresent(ExcelTitle.class)) {   //过滤方法，只获取ExcelTitle注解的get方法
                getterMethods.add(method);
            }
        }
        Collections.sort(getterMethods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int i = o1.getAnnotation(ExcelTitle.class).order() - o2.getAnnotation(ExcelTitle.class).order();
                return i;
            }
        });
        return getterMethods;
    }



    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     *
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}
