package com.smart119.jqxx.utils;


import com.google.common.collect.Lists;
import com.smart119.common.annotation.Excel;
import com.smart119.jczy.domain.FzjcDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Excel导出工具类
 */
public class ExportExcel {

    private static Logger log = LoggerFactory.getLogger(ExportExcel.class);

    /**
     * 工作薄对象
     */
    private SXSSFWorkbook wb;

    /**
     * 工作表对象
     */
    private SXSSFSheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 注解列表（Object[]{ ExcelField, Field/Method }）
     */
    List<Object[]> annotationList = Lists.newArrayList();




    /**
     * 需要导出的属性
     */
    private List<String> exportFields;


    public List<String> getExportFields() {
        return exportFields;
    }

    public void setExportFields(List<String> exportFields) {
        this.exportFields = exportFields;
    }



    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headers 表头数组
     */
    public ExportExcel(String title, String[] headers) {
        initialize(title, Lists.newArrayList(headers));
    }

    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    public ExportExcel(String title, List<String> headerList) {
        initialize(title, headerList);
    }


    /**
     * 构造函数
     * 获取导出数据，get方法和属性的同时使用excel注释的话，读取属性
     * @param clazz 导出的对象
     */
    public ExportExcel(String title, Class<?> clazz) {
        Map map = getExcel(clazz);
        initialize(title, (List<String>)map.get("headTitle"));
        this.exportFields = (List<String>)map.get("exportField");
    }





    /**
     * 初始化函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    private void initialize(String title, List<String> headerList) {
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet("Export");
        this.styles = createStyles(wb);
        // Create title
        if (StringUtils.isNotBlank(title)){
            Row titleRow = sheet.createRow(rownum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
        }
        // Create header
        if (headerList == null){
            throw new RuntimeException("headerList not null!");
        }
        Row headerRow = sheet.createRow(rownum++);
        headerRow.setHeightInPoints(16);
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length==2){
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            }else{
                cell.setCellValue(headerList.get(i));
            }
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i)*2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
        }
        log.debug("Initialize success.");
    }

    /**
     * 初始化函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     */
    private void initialize(String title,String sheetName,List<String> headerList) {
        if(this.wb == null){
            this.wb = new SXSSFWorkbook(500);
            this.styles = createStyles(wb);
        }else{
            this.rownum = 0;
        }
        this.sheet = wb.createSheet(sheetName);

        // Create title
        if (StringUtils.isNotBlank(title)){
            Row titleRow = sheet.createRow(rownum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
                    titleRow.getRowNum(), titleRow.getRowNum(), headerList.size()-1));
        }
        // Create header
        if (headerList == null){
            throw new RuntimeException("headerList not null!");
        }
        Row headerRow = sheet.createRow(rownum++);
        headerRow.setHeightInPoints(16);
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < headerList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(styles.get("header"));
            String[] ss = StringUtils.split(headerList.get(i), "**", 2);
            if (ss.length==2){
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch().createCellComment(
                        new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            }else{
                cell.setCellValue(headerList.get(i));
            }
            sheet.autoSizeColumn(i);
        }
        for (int i = 0; i < headerList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i)*2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
        }
        log.debug("Initialize success.");
    }

    /**
     * 创建表格样式
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
//		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.LEFT);
        styles.put("data1", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        styles.put("data2", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        styles.put("data3", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
//		style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
//		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }

    /**
     * 添加一行
     * @return 行对象
     */
    public Row addRow(){
        return sheet.createRow(rownum++);
    }


    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val){
        return this.addCell(row, column, val, 0, Class.class);
    }

    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @param align 对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType){
        Cell cell = row.createCell(column);
        String cellFormatString = "@";
        try {
            if(val == null){
                cell.setCellValue("");
            }else if(fieldType != Class.class){
                cell.setCellValue((String)fieldType.getMethod("setValue", Object.class).invoke(null, val));
            }else{
                if(val instanceof String) {
                    cell.setCellValue((String) val);
                }else if(val instanceof Integer) {
                    cell.setCellValue((Integer) val);
                    cellFormatString = "0";
                }else if(val instanceof Long) {
                    cell.setCellValue((Long) val);
                    cellFormatString = "0";
                }else if(val instanceof Double) {
                    cell.setCellValue((Double) val);
                    cellFormatString = "0.00";
                }else if(val instanceof Float) {
                    cell.setCellValue((Float) val);
                    cellFormatString = "0.00";
                }else if(val instanceof Date) {
                    cell.setCellValue((Date) val);
                    cellFormatString = "yyyy-MM-dd HH:mm";
                }else {
                    cell.setCellValue((String)Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
                            "fieldtype."+val.getClass().getSimpleName()+"Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
            }
            if (val != null){
                CellStyle style = styles.get("data_column_"+column);
                if (style == null){
                    style = wb.createCellStyle();
                    style.cloneStyleFrom(styles.get("data"+(align>=1&&align<=3?align:"")));
                    style.setDataFormat(wb.createDataFormat().getFormat(cellFormatString));
                    styles.put("data_column_" + column, style);
                }
                cell.setCellStyle(style);
            }
        } catch (Exception ex) {
            log.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        return cell;
    }

    /**
     * 输出数据流
     * @param os 输出数据流
     */
    public ExportExcel write(OutputStream os) throws IOException {
        wb.write(os);
        return this;
    }

    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExportExcel write(HttpServletResponse response, String fileName) throws IOException{
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        write(response.getOutputStream());
        return this;
    }

    /**
     * 输出到文件
     * @param fileName 输出文件名
     */
    public ExportExcel writeFile(String fileName) throws FileNotFoundException, IOException{
        FileOutputStream os = new FileOutputStream(fileName);
        this.write(os);
        return this;
    }

    /**
     * 清理临时文件
     */
    public ExportExcel dispose(){
        wb.dispose();
        return this;
    }

//	/**
//	 * 导出测试
//	 */
//	public static void main(String[] args) throws Throwable {
//
//		List<String> headerList = Lists.newArrayList();
//		for (int i = 1; i <= 10; i++) {
//			headerList.add("表头"+i);
//		}
//
//		List<String> dataRowList = Lists.newArrayList();
//		for (int i = 1; i <= headerList.size(); i++) {
//			dataRowList.add("数据"+i);
//		}
//
//		List<List<String>> dataList = Lists.newArrayList();
//		for (int i = 1; i <=1000000; i++) {
//			dataList.add(dataRowList);
//		}
//
//		ExportExcel ee = new ExportExcel("表格标题", headerList);
//
//		for (int i = 0; i < dataList.size(); i++) {
//			Row row = ee.addRow();
//			for (int j = 0; j < dataList.get(i).size(); j++) {
//				ee.addCell(row, j, dataList.get(i).get(j));
//			}
//		}
//
//		ee.writeFile("target/export.xlsx");
//
//		ee.dispose();
//
//		log.debug("Export success.");
//
//	}


    /**
     * 根据类型获取导出的报表头以及要导出的字段
     * @param clazz
     * @return
     */
    public static Map getExcel(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        TreeMap<Excel, Object> treeMap = new TreeMap<Excel, Object>(new Comparator<Excel>() {
            @Override
            public int compare(Excel o1, Excel o2) {
                return o1.sort() - o2.sort();
            }
        });
        for (Method method : methods) {
            if (method.isAnnotationPresent(Excel.class)) {
                Excel methodAnnotation = method.getAnnotation(Excel.class);
                if (StringUtils.startsWith(method.getName(), "get")) {
                    treeMap.put(methodAnnotation, method.getName());
                }
            }
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Excel.class)) {
                Excel fieldAnnotation = field.getAnnotation(Excel.class);
                if (!ObjectUtils.isEmpty(fieldAnnotation)) {
                    treeMap.put(fieldAnnotation, field.getName());
                }
            }
        }
        List<String> headerList = new ArrayList<>();
        List<Object> dataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (!ObjectUtils.isEmpty(treeMap)) {
            Set<Excel> excels = treeMap.keySet();
            Iterator<Excel> excelIterator = excels.iterator();
            while (excelIterator.hasNext()) {
                Excel excel = excelIterator.next();
                headerList.add(excel.name());
                dataList.add(treeMap.get(excel));

            }
            map.put("headTitle", headerList);
            map.put("exportField", dataList);
        }
        return map;
    }


    /**
     * 获取导出数据，get方法和属性的同时使用excel注释的话，读取属性
     * @param object
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List<Object> getExportData(Object object) throws InvocationTargetException, IllegalAccessException {
        List<String> fields = this.exportFields;
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        Field[] fieldArr = clazz.getDeclaredFields();
        List<Object> listData = new ArrayList<>();
        for (String exportField: fields) {
            if (StringUtils.startsWith(exportField, "get")) {
                for (Method method : methods) {
                    if (exportField.equals(method.getName())) {
                        Object invoke = method.invoke(object);
                        listData.add(invoke);
                    }
                }
            } else {
                for (Field field:  fieldArr) {
                    field.setAccessible(true);
                    if (StringUtils.equals(exportField, field.getName())) {
                        listData.add(field.get(object));
                    }
                }
            }
        }

        return listData;
    }



}
