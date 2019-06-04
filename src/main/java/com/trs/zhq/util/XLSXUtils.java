package com.trs.zhq.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSXUtils {
    public static Object[][] getTestData(String filePath) throws IOException, DataFileException {
        File excelFile = new File(filePath);
        /*
         * 判断给定文件的类型; 1.如果是xls的问价那类型就创建XSSFWorkBook ;
         * 2.如果是xlsx的文件类型就创建HSSFWorkBook ;
         */

        String xls = filePath.substring(filePath.lastIndexOf('.'));
        System.out.println("传入文件的后缀是：" + xls + " ;");
        if (xls.equals(".xls")) {
            HSSFWorkbook xlswb = new HSSFWorkbook(new FileInputStream(excelFile));
            HSSFSheet sheet = xlswb.getSheetAt(0);
            int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            List<Object[]> list = new ArrayList<Object[]>();
            Row row;
            Cell cell;
            for (int i = 0; i < rowcount + 1; i++) {
                row = sheet.getRow(i);
                if (row.getCell(i) == null) {
                    continue;
                }
                Object[] obj = new Object[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            obj[j] = cell.getRichStringCellValue().getString();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                obj[j] = cell.getDateCellValue();
                            } else {
                                obj[j] = cell.getNumericCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            obj[j] = cell.getBooleanCellValue();
                            break;
                        default:
                    }
                }
                list.add(obj);
            }
            Object[][] object = new Object[list.size()][];
            for (int i = 0; i < object.length; i++) {
                object[i] = list.get(i);
            }
            return object;

        } else if (xls.equals(".xlsx")) {
            XSSFWorkbook wbxlsx = new XSSFWorkbook(new FileInputStream(excelFile));
            XSSFSheet sheet = wbxlsx.getSheetAt(0);

            int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            List<Object[]> list = new ArrayList<Object[]>();
            Row row;
            Cell cell;
            for (int i = 0; i < rowcount + 1; i++) {
                row = sheet.getRow(i);
                Object[] obj = new Object[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            obj[j] = cell.getRichStringCellValue().getString();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                obj[j] = cell.getDateCellValue();
                            } else {
                                obj[j] = cell.getNumericCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            obj[j] = cell.getBooleanCellValue();
                            break;
                        default:
                    }

                }
                list.add(obj);
            }
            Object[][] object = new Object[list.size()][];
            for (int i = 0; i < object.length; i++) {
                object[i] = list.get(i);
            }
            return object;
        } else {
            System.out.println("指定的文件不是excle文件！");
            throw new DataFileException("指定的文件不是excle文件！");
        }
    }
}
