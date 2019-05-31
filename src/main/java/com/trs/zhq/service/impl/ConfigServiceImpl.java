package com.trs.zhq.service.impl;

import com.trs.zhq.service.ConfigService;
import com.trs.zhq.util.FileUtil;
import com.trs.zhq.util.ResultDataUtil;
import com.trs.zhq.util.ThreadLocalMap;
import com.trs.zhq.util.XLSXUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {


    /**
     * 导入数据统一格式
     */
    @Override
    public ResultDataUtil inportData(MultipartFile file) {

        ResultDataUtil resultDataUtil = new ResultDataUtil();
        String dirFile = null;
        String filePathName = null;
        try {
            String originalFilename = file.getOriginalFilename();
            // 读取临时文件系统配置项
            String dirPath = "";
            //读取图片存放路径
            String dirPathImg = "";
            //判断目录是否存在，不存在则创建
            boolean b = new File(dirPath).mkdirs();
            File file1 = new File(dirPathImg);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            //文件路径全限定名
            filePathName = dirPath + File.separator + originalFilename;
            file.transferTo(new File(filePathName).getAbsoluteFile());
//			解压到目标路径中
            dirFile = dirPath + File.separator + System.currentTimeMillis();
            new File(dirFile).mkdirs();
            //解压文件
            FileUtil.unzip(filePathName, dirFile);
            paseDir(dirFile);
            List txt = ThreadLocalMap.getValue("TXT");
            if (txt != null) {
                for (Object o : txt) {
                    String str = o + "";
                    //maintenanceService.insertTxt(str);
                }
            }
            List csv = ThreadLocalMap.getValue("CSV");
            if (csv != null) {
                for (Object o : csv) {
                    String str = o + "";
                    //maintenanceService.insertCVS(str);
                }
            }
            List xlsx = ThreadLocalMap.getValue("XLSX");
            if (xlsx != null) {
                for (Object o : xlsx) {
                    String str = o + "";
                    insertXLSX(str);
                }
            }

            resultDataUtil.ok();
            return resultDataUtil;
        } catch (Exception e) {
            e.printStackTrace();
            resultDataUtil.error("数据解析异常");
            return resultDataUtil;
        } finally {
            //删除临时文件
            try {
                FileUtil.deleteFile(dirFile);
                FileUtil.deleteFile(filePathName);
                ThreadLocalMap.remove();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertXLSX(String path) throws Exception {
        Object[][] data =  XLSXUtils.getTestData(path);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (int i = 1; i < data.length; i++) {
            Object[] datum = data[i];
            if(datum[0]==null){
                continue;
            }
        }
    }

    /**遍历查询一个目录下的所有文件*/
    public void paseDir(String fileName){
        File file=new File(fileName);
        if(!file.exists()) throw new RuntimeException();
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File s : files) {
                paseDir(s.getAbsolutePath());
            }
        }else if(fileName.lastIndexOf(".csv")!=-1||fileName.lastIndexOf(".CSV")!=-1){
            ThreadLocalMap.SetValue("CSV",fileName);
        }else if(fileName.lastIndexOf(".txt")!=-1||fileName.lastIndexOf(".TXT")!=-1) {
            ThreadLocalMap.SetValue("TXT",fileName);
        }else if(fileName.lastIndexOf(".xlsx")!=-1||fileName.lastIndexOf(".XLSX")!=-1
                ||fileName.lastIndexOf(".xls")!=-1||fileName.lastIndexOf(".XLS")!=-1){
            ThreadLocalMap.SetValue("XLSX",fileName);
        }else {
            ThreadLocalMap.SetValue("IMG",fileName);
        }
    }
}