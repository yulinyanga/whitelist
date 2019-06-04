package com.trs.zhq.service.impl;

import com.eprobiti.trs.*;
import com.trs.zhq.config.DBConfig;
import com.trs.zhq.service.ConfigService;
import com.trs.zhq.util.FileUtil;
import com.trs.zhq.util.ResultDataUtil;
import com.trs.zhq.util.ThreadLocalMap;
import com.trs.zhq.util.XLSXUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {


    /**
     * 导入数据统一格式
     */
    @Override
    public ResultDataUtil importData(MultipartFile file, File savePath) {

        ResultDataUtil resultDataUtil = new ResultDataUtil();
        String filePathName = null;
        try {
            //文件路径全限定名
            String originalFilename = file.getOriginalFilename();
            filePathName = savePath + File.separator + originalFilename;
            file.transferTo(new File(filePathName).getAbsoluteFile());
            insertXLSX(filePathName);
            resultDataUtil.ok();
            return resultDataUtil;
        } catch (Exception e) {
            e.printStackTrace();
            resultDataUtil.error("数据解析异常");
            return resultDataUtil;
        } finally {
            //删除临时文件
            try {
                FileUtil.deleteFile(filePathName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertXLSX(String path) throws Exception {
        String sUserName = "system";
        String sPassWord = "manager";
        TRSConnection trscon = null;

        // 建立连接
        trscon = new TRSConnection();
        trscon.connect(DBConfig.DB_URL_WHITELIST, DBConfig.DB_PORT_WHITELIST, sUserName, sPassWord);
        // 设置当前字符集=TRS 文件字符集=GB18030
//            TRSConnection.setCharset(TRSConstant.TCE_CHARSET_GB, false);
        try {
            //获取excel数据
            Object[][] data = XLSXUtils.getTestData(path);
            Object[] datum = null;
            Integer num = 0;
            for (int i = 1; i < data.length; i++) {
                datum = data[i];
                if (datum[0] != null) {
                    // 插入记录
                    trscon.setMaintOptions('\u00ff', "", "", 0, 0);
                    String strValue = "username=" + datum[0] + "\u00ff url=" + datum[1].toString();
//                    String strValue = "IR_AUTHORS="+ datum[0] +"\u00ff IR_URLNAME="+datum[1];
                    int iInsertNum = trscon.executeInsert("Demo1", "system", strValue);
                    num = num + iInsertNum;
                }
            }
            System.out.println("成功插入了 " + num);
        } catch (TRSException ex) {
            // 输出错误信息
            System.out.println(ex.getErrorCode() + ":" + ex.getErrorString());
            ex.printStackTrace();
        } finally {
            // 关闭连接
            if (trscon != null) trscon.close();
            trscon = null;
        }
    }

    //批量装入到server
    public void insertServer() {


    }


    /**
     * 遍历查询一个目录下的所有文件
     */
    public void paseDir(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) throw new RuntimeException();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File s : files) {
                paseDir(s.getAbsolutePath());
            }
        } else if (fileName.lastIndexOf(".csv") != -1 || fileName.lastIndexOf(".CSV") != -1) {
            ThreadLocalMap.SetValue("CSV", fileName);
        } else if (fileName.lastIndexOf(".txt") != -1 || fileName.lastIndexOf(".TXT") != -1) {
            ThreadLocalMap.SetValue("TXT", fileName);
        } else if (fileName.lastIndexOf(".xlsx") != -1 || fileName.lastIndexOf(".XLSX") != -1
                || fileName.lastIndexOf(".xls") != -1 || fileName.lastIndexOf(".XLS") != -1) {
            ThreadLocalMap.SetValue("XLSX", fileName);
        } else {
            ThreadLocalMap.SetValue("IMG", fileName);
        }
    }
}