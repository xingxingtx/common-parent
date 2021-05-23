package com.wei.utils.excel;

import com.wei.utils.annotation.Excel;
import com.wei.utils.generate.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe ExportExcel
 * @Author a_pen
 * @Date 2020年09月09日
 */
public class ExcelUtils<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    public ExcelUtils() {
    }

    /**
     * 用于将科学计数法式的数字转换为常规数字显示,BigDecimal表示。<br>
     * 如：6.913084212242E12  转换成：6940282300075
     *
     * @param obj as Object
     * @return
     */
    public static String decimal2string(Object obj) {
        BigDecimal big = null;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Number) {
            big = new BigDecimal(((Number) obj).doubleValue());
        } else {
            return obj.toString();
        }
        return big == null ? "" : big.toPlainString();
    }

    /**
     * 导入
     * @param tClass 对应实体类
     * @param sheetName sheet名称
     * @param input 数据
     * @param rowNum 数据行
     * @return
     * @throws Exception
     */
    public List<T> importExcel(Class<T> tClass, String sheetName, InputStream input, int rowNum) throws Exception {
        List<T> list = new ArrayList<>();
        /**创建工作空间*/
        Workbook workbook = WorkbookFactory.create(input);
        /**获取sheet数量*/
        int sheets = workbook.getNumberOfSheets();
        if(sheets <= 0){
            log.info("sheets number is :{}", sheets);
        }
        for (int index = 0; index < sheets; index++) {
            Sheet sheet = null;
            if (StringUtils.isEmpty(sheetName, false)) {
                sheet = workbook.getSheetAt(index);
            } else {
                /**获取固定sheetName 数据*/
                sheet = workbook.getSheet(sheetName);
                index = sheets;
            }
            /**获取数据总行数*/
            int rows = sheet.getPhysicalNumberOfRows();
            if (rows > 0) {
                // 有数据时才处理 得到类的所有field.
                Field[] allFields = tClass.getDeclaredFields();
                // 定义一个map用于存放列的序号和field.
                List<Field> fieldsMap = new ArrayList<>();
                for (int col = 0; col < allFields.length; col++) {
                    Field field = allFields[col];
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(Excel.class)) {
                        // 设置类的私有字段属性可访问.
                        field.setAccessible(true);
                        fieldsMap.add(field);
                    }
                }
                for (int i = rowNum; i < rows; i++) {
                    // 从第2行开始取数据,默认第一行是表头.
                    Row row = sheet.getRow(i);
                    //获取一行所有的单元格的数量
                    int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
                    // 创建entity实例
                    T entity = tClass.newInstance();
                    for (int j = 0; j < cellNum; j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        } else {
                            cell = row.getCell(j);
                        }
                        // 从map中得到对应列的field.
                        Field field = fieldsMap.get(j);
                        /**根据entity类型设置具体值*/
                        setEntityType(entity, field, cell);
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }
        }
        return list;
    }

    private void setEntityType(T entity, Field field, Cell cell) throws IllegalAccessException {
        // 取得类型,并根据对象类型设置值.
        Class<?> fieldType = field.getType();
        if (String.class == fieldType) {
            field.set(entity, String.valueOf(cell.getStringCellValue()));
        } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
            field.set(entity, Integer.parseInt(cell.getStringCellValue()));
        } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
            field.set(entity, Long.valueOf(cell.getStringCellValue()));
        } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
            field.set(entity, Float.valueOf(cell.getStringCellValue()));
        } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
            field.set(entity, Short.valueOf(cell.getStringCellValue()));
        } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
            field.set(entity, Double.valueOf(cell.getStringCellValue()));
        } else if (Character.TYPE == fieldType) {
            if ((cell.getStringCellValue() != null) && (cell.getStringCellValue().length() > 0)) {
                field.set(entity, Character.valueOf(cell.getStringCellValue().charAt(0)));
            }
        } else if (Date.class == fieldType) {
            String value = null;
            if (cell.getCellType() == CellType.NUMERIC) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cell.setCellValue(sdf.format(cell.getNumericCellValue()));
                value = sdf.format(cell.getNumericCellValue());
            } else {
                value = cell.getStringCellValue();
            }
            field.set(entity, value);
        } else if (BigDecimal.class == fieldType) {
            field.set(entity, cell.getStringCellValue());
        }
    }

    /**
     * 导出excel到网络（直接将http）
     *
     * @param list
     * @param sheetName
     * @param response
     * @return
     */
    public void exportExcelToWeb(Class<T> tClass, List<T> list, String sheetName, HttpServletResponse response) {
        // 告诉浏览器用什么软件可以打开此文件
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sheetName + ".xls", "utf-8"));
            this.exportExcel(tClass, list, sheetName, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(Class<T> tClass, List<T> list, String sheetName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 告诉浏览器用什么软件可以打开此文件
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = df.format(new Date()) + "导出数据";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            this.exportExcel(tClass, list, sheetName, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出excel到指定路径
     *
     * @param list
     * @param sheetName
     * @param path      指定具体Excel文件路径：eg->D:\workFile\xxx.xls
     * @return
     */
    public ReturnResult exportExcelToPath(Class<T> tClass, List<T> list, String sheetName, String path) {
        ReturnResult returnResult = null;
        try {
            OutputStream out = new FileOutputStream(path);
            returnResult = this.exportExcel(tClass, list, sheetName, out);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnResult;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     * 通过制定out，实现导出到磁盘/网络
     *
     * @param sheetName 工作表的名称
     */
    public ReturnResult exportExcel(Class<T> tClass, List<T> list, String sheetName, OutputStream out) {
        // 得到所有定义字段
        Field[] allFields = tClass.getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(Excel.class)) {
                fields.add(field);
            }
        }
        // 产生工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // excel2003中每个sheet中最多有65536行
        int sheetSize = 65536;
        // 取出一共有多少个sheet.
        double sheetNo = Math.ceil(list.size() / sheetSize);
        for (int index = 0; index <= sheetNo; index++) {
            // 产生工作表对象
            HSSFSheet sheet = workbook.createSheet();
            if (sheetNo == 0) {
                workbook.setSheetName(index, sheetName);
            } else {
                // 设置工作表的名称.
                workbook.setSheetName(index, sheetName + index);
            }
            HSSFRow row;
            HSSFCell cell; // 产生单元格
            // 产生一行
            row = sheet.createRow(0);
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                Excel attr = field.getAnnotation(Excel.class);
                // 创建列
                cell = row.createCell(i);
                // 设置列中写入内容为String类型
                cell.setCellType(CellType.STRING);
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                if (attr.name().indexOf("注：") >= 0) {
                    HSSFFont font = workbook.createFont();
                    font.setColor(HSSFFont.COLOR_RED);
                    cellStyle.setFont(font);
                    cellStyle.setFillForegroundColor((short) 700);
                    sheet.setColumnWidth(i, 6000);
                } else {
                    HSSFFont font = workbook.createFont();
                    // 选择需要用到的字体格式
                    cellStyle.setFont(font);
                    cellStyle.setFillForegroundColor((short) 43);
                    // 设置列宽
                    sheet.setColumnWidth(i, 3766);
                }
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setWrapText(true);
                cell.setCellStyle(cellStyle);
                // 写入列名
                cell.setCellValue(attr.name());
                // 如果设置了提示信息则鼠标放上去提示.
                if (!StringUtils.isEmpty(attr.prompt(), false)) {
                    // 这里默认设了2-101列提示.
                    setHSSFPrompt(sheet, "", attr.prompt(), 1, 100, i, i);
                }
                // 如果设置了combo属性则本列只能选择不能输入
                if (attr.combo().length > 0) {
                    // 这里默认设了2-101列只能选择不能输入.
                    setHSSFValidation(sheet, attr.combo(), 1, 100, i, i);
                }
            }
            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());
            // 写入各条记录,每条记录对应excel表中的一行
            HSSFCellStyle cs = workbook.createCellStyle();
            cs.setAlignment(HorizontalAlignment.CENTER);
            cs.setVerticalAlignment(VerticalAlignment.CENTER);
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                // 得到导出对象.
                T vo = (T) list.get(i);
                for (int j = 0; j < fields.size(); j++) {
                    // 获得field.
                    Field field = fields.get(j);
                    // 设置实体类私有属性可访问
                    field.setAccessible(true);
                    Excel attr = field.getAnnotation(Excel.class);
                    try {
                        // 根据Excel中设置情况决定是否导出
                        if (attr.isExport()) {
                            // 创建cell
                            cell = row.createCell(j);
                            cell.setCellStyle(cs);
                            try {
                                if (String.valueOf(field.get(vo)).length() > 10) {
                                    throw new Exception("长度超过10位就不用转数字了");
                                }
                                // 如果可以转成数字则导出为数字类型
                                BigDecimal bc = new BigDecimal(String.valueOf(field.get(vo)));
                                cell.setCellType(CellType.NUMERIC);
                                cell.setCellValue(bc.doubleValue());
                            } catch (Exception e) {
                                cell.setCellType(CellType.STRING);
                                if (vo == null) {
                                    // 如果数据存在就填入,不存在填入空格.
                                    cell.setCellValue("");
                                } else {
                                    // 如果数据存在就填入,不存在填入空格.
                                    cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));
                                }

                            }
                        }
                    } catch (Exception e) {
                        log.error("导出Excel失败{}", e.getMessage());
                    }
                }
            }
        }
        try {
            workbook.write(out);
            out.close();
            ReturnResult returnResult = new ReturnResult();
            returnResult.setMsg("filename");
            returnResult.setCode(1);
            return returnResult;
        } catch (Exception e) {
            log.error("关闭flush失败{}", e.getMessage());
            ReturnResult returnResult = new ReturnResult();
            returnResult.setMsg("导出Excel失败，请联系网站管理员！");
            returnResult.setCode(-1);
            return returnResult;
        }
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow,
                                          int endRow, int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation dataValidationView = new HSSFDataValidation(regions, constraint);
        dataValidationView.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(dataValidationView);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textlist, int firstRow, int endRow,
                                              int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation dataValidationList = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(dataValidationList);
        return sheet;
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xls";
        return filename;
    }

    private static final Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");

    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher matcher = pattern.matcher(str);
            dest = matcher.replaceAll("");
        }
        return dest;
    }
}
