package com.smart119.common.utils.poi;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 *
 * @author v_wangguangyu
 */
@SuppressWarnings({"rawtypes"})
public class ExcelUtil {
    private static ExcelUtil eu = new ExcelUtil();



    private ExcelUtil() {
    }
    public static ExcelUtil getInstance() {
        return eu;
    }
    /**
     * 处理对象转换为Excel
     *
     * @param template
     * @param objs
     * @param clz
     * @param isClasspath
     *
     * @return
     */
    private ExcelTemplate handlerObj2Excel(String template, List objs, Class clz, boolean isClasspath) {
        ExcelTemplate et = ExcelTemplate.getInstance();
        try {
            if (isClasspath) {
                et.readTemplateByClasspath(template);
            } else {
                et.readTemplateByPath(template);
            }
            List<ExcelHeader> headers = getHeaderList(clz);
            Collections.sort(headers);
            // 输出标题
            et.createNewRow();
            for (ExcelHeader eh : headers) {
                et.createCell(eh.getTitle());
            }
            // 输出值
            for (Object obj : objs) {
                et.createNewRow();
                for (ExcelHeader eh : headers) {
                    // Method m = clz.getDeclaredMethod(mn);
                    // Object rel = m.invoke(obj);
                    et.createCell(BeanUtils.getProperty(obj, getMethodName(eh)));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return et;
    }
    /**
     * 根据标题获取相应的方法名称
     *
     * @param eh
     *
     * @return
     */
    private String getMethodName(ExcelHeader eh) {
        String mn = eh.getMethodName();
        mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
        return mn;
    }
    /**
     * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到流
     *
     * @param datas       模板中的替换的常量数据
     * @param template    模板路径
     * @param os          输出流
     * @param objs        对象列表
     * @param clz         对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportObj2ExcelByTemplate(Map<String, String> datas, String template, OutputStream os, List objs,
                                          Class clz, boolean isClasspath) {
        ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
        et.replaceFinalData(datas);
        et.wirteToStream(os);
    }
    /**
     * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到一个具体的路径中
     *
     * @param datas       模板中的替换的常量数据
     * @param template    模板路径
     * @param outPath     输出路径
     * @param objs        对象列表
     * @param clz         对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportObj2ExcelByTemplate(Map<String, String> datas, String template, String outPath, List objs,
                                          Class clz, boolean isClasspath) {
        ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
        et.replaceFinalData(datas);
        et.writeToFile(outPath);
    }
    /**
     * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到流,基于Properties作为常量数据
     *
     * @param prop        基于Properties的常量数据模型
     * @param template    模板路径
     * @param os          输出流
     * @param objs        对象列表
     * @param clz         对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportObj2ExcelByTemplate(Properties prop, String template, OutputStream os, List objs, Class clz,
                                          boolean
                                                  isClasspath) {
        ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
        et.replaceFinalData(prop);
        et.wirteToStream(os);
    }
    /**
     * 将对象转换为Excel并且导出，该方法是基于模板的导出，导出到一个具体的路径中,基于Properties作为常量数据
     *
     * @param prop        基于Properties的常量数据模型
     * @param template    模板路径
     * @param outPath     输出路径
     * @param objs        对象列表
     * @param clz         对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportObj2ExcelByTemplate(Properties prop, String template, String outPath, List objs, Class clz,
                                          boolean
                                                  isClasspath) {
        ExcelTemplate et = handlerObj2Excel(template, objs, clz, isClasspath);
        et.replaceFinalData(prop);
        et.writeToFile(outPath);
    }
    private Workbook handleObj2Excel(List objs, Class clz, boolean isXssf) {
        Workbook wb = null;
        try {
            if (isXssf) {
                wb = new XSSFWorkbook();
            } else {
                wb = new HSSFWorkbook();
            }
            Font font = wb.createFont();
            // 粗体显示
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
            style.setFont(font);
            // 水平居中
            style.setAlignment(CellStyle.ALIGN_CENTER);
            // 垂直居中
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            HSSFCellStyle styleData = (HSSFCellStyle) wb.createCellStyle();
            styleData.setWrapText(true);
            // 垂直居中
            styleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Sheet sheet = wb.createSheet();
            // 将第一行的表头标题锁定
            sheet.createFreezePane(0, 1, 0, 1 );
            Row r = sheet.createRow(0);
            List<ExcelHeader> headers = getHeaderList(clz);
            Collections.sort(headers);
            // 写标题
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = r.createCell(i);
                cell.setCellStyle(style);
                cell.setCellValue(headers.get(i).getTitle());
            }
            // 写数据
            Object obj = null;
            for (int i = 0; i < objs.size(); i++) {
                r = sheet.createRow(i + 1);
                obj = objs.get(i);
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = r.createCell(j);
                    cell.setCellStyle(styleData);
                    // 设置某一列宽度
                    sheet.setColumnWidth(j, 256 * 30);
                    cell.setCellValue(BeanUtils.getProperty(obj, getMethodName(headers.get(j))));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return wb;
    }
    /**
     * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于路径的导出
     *
     * @param outPath 导出路径
     * @param objs    对象列表
     * @param clz     对象类型
     * @param isXssf  是否是2007版本
     */
    public void exportObj2Excel(String outPath, List objs, Class clz, boolean isXssf) {
        Workbook wb = handleObj2Excel(objs, clz, isXssf);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 导出对象到Excel，不是基于模板的，直接新建一个Excel完成导出，基于流
     *
     * @param os     输出流
     * @param objs   对象列表
     * @param clz    对象类型
     * @param isXssf 是否是2007版本
     */
    public void exportObj2Excel(OutputStream os, List objs, Class clz, boolean isXssf) {
        try {
            Workbook wb = handleObj2Excel(objs, clz, isXssf);
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 从类路径读取相应的Excel文件到对象列表
     *
     * @param path     类路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     *
     * @return
     */
    public List<Object> readExcel2ObjsByClasspath(String path, Class clz, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(path));
            return handlerExcel2Objs(wb, clz, readLine, tailLine);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从类路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     *
     * @return 对象列表
     */
    public List<Object> readExcel2ObjsByClasspath(String path, Class clz) {
        return this.readExcel2ObjsByClasspath(path, clz, 0, 0);
    }


    /**
     * 从文件路径读取相应的Excel文件到对象列表
     *
     * @param path     文件路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     *
     * @return
     */
    public List<Object> readExcel2ObjsByPath(String path, Class clz, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new FileInputStream(new File(path)));
            return handlerExcel2Objs(wb, clz, readLine, tailLine);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 从文件路径读取相应的Excel文件到对象列表
     *
     * @param path     文件路径下的path
     * @param clz      对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     *
     * @return
     */
    public List<Object> readExcel2ObjsByPath(String path, Class clz, Class superclass, int readLine, int tailLine) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new FileInputStream(new File(path)));
            return handlerExcel2Objs(wb, clz, superclass, readLine, tailLine);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     *
     * @return 对象列表
     */
    public List<Object> readExcel2ObjsByPath(String path, Class clz) {
        return this.readExcel2ObjsByPath(path, clz, 0, 0);
    }


    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     *
     * @param path 路径
     * @param clz  类型
     * @param superclass clz的父类
     * @return 对象列表
     */
    public List<Object> readExcel2ObjsByPath(String path, Class clz, Class superclass) {
        return this.readExcel2ObjsByPath(path, clz,  superclass, 0, 0);
    }

    private String getCellValue(Cell c) {
        String o = null;
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                DecimalFormat df = new DecimalFormat("0");
                o = df.format(c.getNumericCellValue());
                o = String.valueOf(o);
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }


    private List<Object> handlerExcel2Objs(Workbook wb, Class clz, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);
            objs = new ArrayList<Object>();
            Map<Integer, String> maps = getHeaderMap(row, clz);
            if (maps == null || maps.size() <= 0) {
                throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
            }
            for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
                row = sheet.getRow(i);
                Object obj = clz.newInstance();
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci);
                    String content = this.getCellValue(c);
                    BeanUtils.copyProperty(obj, mn, content);
                }
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return objs;
    }


    private List<Object> handlerExcel2Objs(Workbook wb, Class clz, Class superclass, int readLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(0);
        List<Object> objs = null;
        try {
            Row row = sheet.getRow(readLine);
            objs = new ArrayList<Object>();
            Map<Integer, String> maps = getHeaderMap(row, clz, superclass);
            if (maps == null || maps.size() <= 0) {
                throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
            }
            for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
                row = sheet.getRow(i);
                Object obj = clz.newInstance();
                for (Cell c : row) {
                    int ci = c.getColumnIndex();
                    String mn = maps.get(ci);
                    String content = this.getCellValue(c);
                    BeanUtils.copyProperty(obj, mn, content);
                }
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return objs;
    }



    private List<ExcelHeader> getHeaderList(Class clz) {
        Method[] ms = clz.getDeclaredMethods();
        Field[] fields = clz.getDeclaredFields();
        Set<Field> fieldSet = new HashSet<>();
        fieldSet.addAll(Arrays.asList(fields));
        List<ExcelHeader> headers = getExcelHeaders(fieldSet);
        return headers;
    }



    private List<ExcelHeader> getHeaderList(Class clz, Class superclass) {
        Method[] ms = clz.getDeclaredMethods();
        Field[] fields = clz.getDeclaredFields();
        Field[] superclassDeclaredFields = null;
        if (!ObjectUtils.isEmpty(superclass)) {
            superclassDeclaredFields = superclass.getDeclaredFields();
        }
        Set<Field> fieldSet = new HashSet<>();
        fieldSet.addAll(Arrays.asList(fields));
        if (ObjectUtils.isArray(superclassDeclaredFields)) {
            fieldSet.addAll(Arrays.asList(superclassDeclaredFields));
        }
        List<ExcelHeader> headers = getExcelHeaders(fieldSet);
        return headers;
    }

    private List<ExcelHeader> getExcelHeaders(Set<Field> fieldSet) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        for (Field field : fieldSet) {
            String fieldName = field.getName();
            if (field.isAnnotationPresent(ExcelResources.class)) {
                ExcelResources er = field.getAnnotation(ExcelResources.class);
                headers.add(new ExcelHeader(er.title(), er.order(), fieldName));
            }
        }
        return headers;
    }


    private Map<Integer, String> getHeaderMap(Row titleRow, Class clz) {
        List<ExcelHeader> headers = getHeaderList(clz);
        Map<Integer, String> maps = new HashMap<Integer, String>();
        for (Cell c : titleRow) {
            String title = c.getStringCellValue();
            for (ExcelHeader eh : headers) {
                if (eh.getTitle().equals(title.trim())) {
                    maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
                    break;
                }
            }
        }
        return maps;
    }


    private Map<Integer, String> getHeaderMap(Row titleRow, Class clz,  Class superclass) {
        List<ExcelHeader> headers = getHeaderList(clz, superclass);
        Map<Integer, String> maps = new HashMap<Integer, String>();
        for (Cell c : titleRow) {
            String title = c.getStringCellValue();
            for (ExcelHeader eh : headers) {
                if (eh.getTitle().equals(title.trim())) {
                    maps.put(c.getColumnIndex(), eh.getMethodName().replace("get", "set"));
                    break;
                }
            }
        }
        return maps;
    }
}
