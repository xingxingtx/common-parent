package com.wei.cert.helper;

import com.wei.cert.dto.Entity;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class ReadFileHelper {

    public List<Entity> results = null;

    public ReadFileHelper(String configPath) throws Exception {
        this.results = this.readConfig(configPath);
    }

    private List<Entity> readConfig(String configPath) throws Exception {
        File configFile = new File(configPath);
        InputStream is = new FileInputStream(configFile);
        List<Entity> result = null;
        if (configFile.getName().endsWith("xlsx")) {
            result = this.dealXLSX(new XSSFWorkbook(is));
        } else {
            result = this.dealXLS(new HSSFWorkbook(is));
        }

        return result;
    }

    private List<Entity> dealXLSX(Workbook wb) throws Exception {
        Sheet sheet = wb.getSheetAt(0);
        List<Entity> result = new ArrayList();

        for(int i = 1; i <= sheet.getLastRowNum(); ++i) {
            XSSFRow xssfrow = (XSSFRow)sheet.getRow(i);
            Entity en = new Entity();
            int j = 0;
            en.setCompanyName(xssfrow.getCell(j).getStringCellValue());
            en.setOrgName(xssfrow.getCell(j + 1).getStringCellValue());
            en.setCountryCode(xssfrow.getCell(j + 2).getStringCellValue());
            en.setCommonName(xssfrow.getCell(j + 3).getStringCellValue());
            en.setEffectiveYears((int)xssfrow.getCell(j + 4).getNumericCellValue());
            en.setBusinessCategory(xssfrow.getCell(j + 5).getStringCellValue());
            result.add(en);
        }

        return result;
    }

    private List<Entity> dealXLS(Workbook wb) throws Exception {
        Sheet sheet = wb.getSheetAt(0);
        List<Entity> result = new ArrayList();

        for(int i = 0; i <= sheet.getLastRowNum(); ++i) {
            HSSFRow hssfrow = (HSSFRow)sheet.getRow(i);
            Entity en = new Entity();
            int j = 0;
            en.setCompanyName(hssfrow.getCell(j).getStringCellValue());
            en.setOrgName(hssfrow.getCell(j + 1).getStringCellValue());
            en.setCountryCode(hssfrow.getCell(j + 2).getStringCellValue());
            en.setCommonName(hssfrow.getCell(j + 3).getStringCellValue());
            en.setEffectiveYears((int)hssfrow.getCell(j + 4).getNumericCellValue());
            en.setBusinessCategory(hssfrow.getCell(j + 5).getStringCellValue());
            result.add(en);
        }

        return result;
    }

}


