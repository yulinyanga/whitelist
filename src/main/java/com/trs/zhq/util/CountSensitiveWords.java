package com.trs.zhq.util;

import com.eprobiti.trs.TRSConnection;
import com.eprobiti.trs.TRSException;
import com.eprobiti.trs.TRSResultSet;
import com.trs.zhq.config.DBConfig;
import com.trs.zhq.entity.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CountSensitiveWords {
    DBConfig db = new DBConfig();
    TRSConnection conn = null;
    TRSResultSet rs = null;

    /**
     * 查找敏感词的相关信息的详细信息
     *
     * @param tableName
     * @param sensitiveWords
     * @param isContinue
     * @param
     * @return 详细信息
     * @throws IOException
     */
    public List searchMessage(String tableName, String sensitiveWords, boolean isContinue) throws IOException {
        long num = 0;
        conn = db.getDBConnection();
        //所有的疑似信息列表
        List list = null;
        try {
            rs = new TRSResultSet();
            rs = conn.executeSelect(tableName, sensitiveWords, isContinue);
            System.out.println("server检索词：" + sensitiveWords);
            num = rs.getRecordCount();
            //循环添加疑似信息
            String log = "";
            String column = ConcatSiteName.getColumnName(tableName);
            String[] wordsArray;
            String searchWords;
            if ("IR_HKEY".equals(column)) {
                list = new ArrayList<WeiXin>();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    rs.moveTo(0, i);
                    WeiXin weiXin = new WeiXin();
                    weiXin.setFileName(rs.getString("IR_AUTHORS"));
                    weiXin.setIR_URLNAME(rs.getString("IR_URLNAME"));
                    weiXin.setIR_WEIXINID(rs.getString("IR_WEIXINID"));
                    weiXin.setIR_CONTENT(rs.getString("IR_CONTENT"));
                    weiXin.setIR_HKEY(rs.getString("IR_HkEY"));
                    String words = rs.getString("IR_CONTENT", "red");
                    wordsArray = words.split("<font color=red>");
                    searchWords = "";
                    for (int j = 1; j < wordsArray.length; j++) {
                        int index = wordsArray[j].indexOf("</font>");
                        wordsArray[j] = wordsArray[j].substring(0, index);
                        searchWords += wordsArray[j] + ",";
                    }
                    weiXin.setKeyword(sameWordNums(searchWords));
                    System.out.println("敏感词出现的次数：" + sameWordNums(searchWords));
                    list.add(weiXin);
                }
            }
            if ("IR_SID".equals(column) || "IR_AUTHORS".equals(column)) {
                list = new ArrayList<SpecialWord>();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    SpecialWord specialWord = new SpecialWord();

                    rs.moveTo(0, i);
                    String id = rs.getString("IR_SID");
                    specialWord.setId(id);
                    String words = rs.getString("IR_URLCONTENT", "red");
                    specialWord.setContent(words);
                    String url = rs.getString("IR_URLNAME");
                    specialWord.setUrl(url);
                    String siteName = rs.getString("IR_SITENAME");
                    specialWord.setSiteName(siteName);
                    specialWord.setChannel(rs.getString("IR_CHANNEL"));
                    wordsArray = words.split("<font color=red>");
                    searchWords = "";
                    String groupname = rs.getString("IR_GROUPNAME");
                    specialWord.setGroupname(groupname);
                    for (int j = 1; j < wordsArray.length; j++) {
                        int index = wordsArray[j].indexOf("</font>");
                        wordsArray[j] = wordsArray[j].substring(0, index);
                        searchWords += wordsArray[j] + ",";
                    }
                    specialWord.setKeyword(sameWordNums(searchWords));
                    System.out.println("敏感词出现的次数：" + sameWordNums(searchWords));
                    list.add(specialWord);
                    log += specialWord.toString() + "\r\n" + "\n" + "\n";
                }
            } else if ("IR_CREATED_AT".equals(column)) {        //增加微博类型
                list = new LinkedList();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    WeiBo weibo = new WeiBo();
                    rs.moveTo(0, i);
                    String id = rs.getString("IR_MID");
                    weibo.setIR_MID(id);
                    String sitename = rs.getString("IR_SITENAME");
                    weibo.setIR_SITENAME(sitename);
                    String IR_SCREEN_NAME = rs.getString("IR_SCREEN_NAME");
                    weibo.setIR_SCREEN_NAME(IR_SCREEN_NAME);
                    String url = rs.getString("IR_URLNAME");
                    weibo.setIR_URLNAME(url);
                    String words = rs.getString("IR_STATUS_CONTENT", "red");
                    weibo.setIR_STATUS_CONTENT(words);
                    wordsArray = words.split("<font color=red>");
                    searchWords = "";
                    for (int j = 1; j < wordsArray.length; j++) {
                        int index = wordsArray[j].indexOf("</font>");
                        wordsArray[j] = wordsArray[j].substring(0, index);
                        searchWords += wordsArray[j] + ",";
                    }
                    weibo.setKeyword(sameWordNums(searchWords));
                    list.add(weibo);
                    log += weibo.toString() + "\r\n" + "\n" + "\n";
                }
            } else if ("FILENAME".equals(column)) {
                list = new ArrayList();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    WangPan wangPan = new WangPan();
                    wangPan.setIp(rs.getString("IP"));
                    wangPan.setFilepath(rs.getString("FILEPATH"));
                    wangPan.setType(rs.getString("TYPE"));
                    log += wangPan.toString() + "\r\n" + "\n" + "\n";
                    list.add(wangPan);
                }
            } else if ("id".equals(column) || "Y_ID".equals(column)) {
                list = new ArrayList<JCMS>();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    rs.moveTo(0, i);
                    JCMS jcms = new JCMS();
                    jcms.setDB_TYPE(rs.getString("DB_TYPE"));
                    jcms.setSID(rs.getString("id"));
                    jcms.setSIP(rs.getString("SIP"));
                    jcms.setSPORT(rs.getString("SPORT"));
                    jcms.setTB_NAME(rs.getString("TB_NAME"));
                    jcms.setY_ID(rs.getString("MID"));
                    jcms.setTrscontent(rs.getString("TRSCONTENT"));
                    jcms.setDb_name(rs.getString("DB_NAME"));
                    String words = rs.getString("TRSCONTENT", "red");
                    wordsArray = words.split("<font color=red>");
                    searchWords = "";
                    for (int j = 1; j < wordsArray.length; j++) {
                        int index = wordsArray[j].indexOf("</font>");
                        wordsArray[j] = wordsArray[j].substring(0, index);
                        searchWords += wordsArray[j] + ",";
                    }
                    jcms.setKeyword(sameWordNums(searchWords));
                    list.add(jcms);
                }
            } else if ("FileName".equals(column) || "FilePath".equals(column)) {
                list = new ArrayList<FIleModel>();
                for (int i = 0; i < rs.getRecordCount(); i++) {
                    rs.moveTo(0, i);
                    FIleModel fIleModel = new FIleModel();
                    fIleModel.setFilename(rs.getString("FileName"));
                    fIleModel.setFilepath(rs.getString("FilePath"));
                    fIleModel.setIp(rs.getString("ip"));
                    String words = rs.getString("WP_Content", "red");
                    wordsArray = words.split("<font color=red>");
                    searchWords = "";
                    for (int j = 1; j < wordsArray.length; j++) {
                        int index = wordsArray[j].indexOf("</font>");
                        wordsArray[j] = wordsArray[j].substring(0, index);
                        searchWords += wordsArray[j] + ",";
                    }
                    fIleModel.setKey(sameWordNums(searchWords));
                    System.out.println("敏感词出现的次数：" + sameWordNums(searchWords));
                    list.add(fIleModel);
                }
            }

            File file = new File(DBConfig.logPath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(log.getBytes());
            fileOutputStream.close();
        } catch (TRSException e) {
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("ErrorString: " + e.getErrorString());
        } finally {
            db.closeConnection(conn);
        }
        return list;
    }

    /**
     * 获取相应目录下文本中所有的敏感词
     *
     * @param filePath
     * @return 文本中所有的敏感词
     */
    public static String getSensitiveWords(String filePath) {
        String sensitiveWords = "";
        FileReader reader = null;
        BufferedReader br = null;
        try {
            reader = new FileReader(filePath);
            br = new BufferedReader(reader);
            sensitiveWords = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sensitiveWords;
    }

    /**
     * 每条记录出现相同敏感词的次数
     *
     * @param searchWords
     * @return 相同敏感词记录
     */
    public static String sameWordNums(String searchWords) {
        String[] string = searchWords.split(",");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < string.length; i++) {
            if (map.get(string[i]) == null) {
                map.put(string[i], 1);
            } else {
                map.put(string[i], map.get(string[i]) + 1);
            }
        }
        return map.toString();
    }

    //排除白名单
    public static Map<String, Boolean> filterWhiteList(String username) {
        String sUserName = "system";
        String sPassWord = "manager";
        TRSConnection trscon = null;
        Map<String, Boolean> whiteMap = null;
        try {
            // 建立连接
            trscon = new TRSConnection();
            trscon.connect(DBConfig.DB_URL_WHITELIST, DBConfig.DB_PORT_WHITELIST, sUserName, sPassWord);
            //查询白名单，遍历
            TRSResultSet rs = new TRSResultSet();
            whiteMap = new HashMap<>();
            rs.setConnection(trscon);
            rs.executeSelect(DBConfig.DB_TABLE_WHITELIST, "username=" + username, false);
            System.out.println("检索到的白名单数量： " + rs.getRecordCount());
            for (int j = 0; j < rs.getRecordCount(); j++) {
                rs.moveTo(0, j);
                whiteMap.put(rs.getString("url"), true);
            }
        } catch (TRSException ex) {
            // 输出错误信息
            System.out.println(ex.getErrorCode() + ":" + ex.getErrorString());
            ex.printStackTrace();
        } finally {
            // 关闭连接
            if (trscon != null) trscon.close();
            trscon = null;
        }
        return whiteMap;
    }

    /**
     * 这个主方法是为了生成疑似信息详细列表
     */
    public static void detail(String username, Boolean isAdmin) throws IOException {
        Map<String, Boolean> whiteMap = filterWhiteList(username);
        CountSensitiveWords csw = new CountSensitiveWords();
        String serverTable = DBConfig.serverTable;
        //使用数据库的的列来区分数据库
        String column = ConcatSiteName.getColumnName(serverTable);
        //增加数据库的判断
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("疑似信息列表");
        XSSFRow header = sheet.createRow(0);
        int bugNum = 0;
        String exp;
        if (isAdmin) {
            exp = getSensitiveWords(DBConfig.biaodashi);
        } else {
            exp = DBConfig.biaodashi;
        }
        if ("IR_HKEY".equals(column)) {
            List<WeiXin> list = new ArrayList<>();
            header.createCell(0).setCellValue("序号");
            header.createCell(1).setCellValue("微信id");
            header.createCell(2).setCellValue("作者名");
            header.createCell(3).setCellValue("链接地址");
            header.createCell(4).setCellValue("检索词");
//            header.createCell(5).setCellValue("文本内容");
//            header.createCell(6).setCellValue("IR_HEKY");
            //定义出异常的数量
            int q = 0;
            list = csw.searchMessage(serverTable, exp, false);
//            list = csw.searchMessage(serverTable, "IR_HKEY=% OR IR_CONTENT=%" + getSensitiveWords(filePath[0]) + "%", false);
            for (int z = 1; z < list.size() + 1; z++) {
                WeiXin weiXin = list.get(z - 1);
                if (whiteMap.get(weiXin.getIR_URLNAME()) != null) continue;
                q = q + 1;
                XSSFRow header2 = sheet.createRow(q);
                header2.createCell(0).setCellValue(q);
                header2.createCell(1).setCellValue(weiXin.getIR_WEIXINID());
                header2.createCell(2).setCellValue(weiXin.getFileName());
                header2.createCell(3).setCellValue(weiXin.getIR_URLNAME());
//                        header2.createCell(5).setCellValue(weiXin.getIR_CONTENT());
                header2.createCell(6).setCellValue(weiXin.getIR_HKEY());
                header2.createCell(4).setCellValue(weiXin.getKeyword());
            }
            bugNum = bugNum + list.size();
            header.createCell(5).setCellValue("记录数总量: " + CountTotalRecordNum.getDataNumAll(serverTable) + "   异常数量为:" + (list.size() - 1));
        } else if ("IR_SID".equals(column)) {
            List<SpecialWord> list;
            header.createCell(0).setCellValue("序号");
            header.createCell(1).setCellValue("单位名称");
            header.createCell(2).setCellValue("应用分类");
            header.createCell(3).setCellValue("应用编码");
            header.createCell(4).setCellValue("应用名称");
            header.createCell(5).setCellValue("疑似地址");
            header.createCell(6).setCellValue("疑似关键词");
            header.createCell(7).setCellValue("发现时间");
            //定义出异常的数量
            int q = 0;
            String url;
            list = csw.searchMessage(serverTable, exp, false);
            for (int z = 1; z < list.size() + 1; z++) {
                SpecialWord specialWord = list.get(z - 1);
                url = specialWord.getUrl();
                //去除白名单
                if (whiteMap.get(url) != null) continue;
                q = q + 1;
                XSSFRow header2 = sheet.createRow(q);
                String siteName = specialWord.getSiteName();
                System.out.println(siteName);
                String code = "";
                String unitName = siteName;
                int index = siteName.indexOf("_");
                if (index != -1) {
                    code = siteName.substring(0, index);
                    unitName = siteName.substring(index + 1);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Long time = new Date().getTime() - 1000 * 60 * 60 * 24;
                String date = sdf.format(time);
                header2.createCell(0).setCellValue(q);
                header2.createCell(1).setCellValue(unitName);
                header2.createCell(2).setCellValue(DBConfig.groupType);
                header2.createCell(3).setCellValue(code);
                header2.createCell(4).setCellValue(specialWord.getChannel());
                header2.createCell(5).setCellValue(url);
                header2.createCell(6).setCellValue(specialWord.getKeyword());
                header2.createCell(7).setCellValue(date);
            }
            bugNum = bugNum + list.size();
            header.createCell(8).setCellValue("记录数总量: " + CountTotalRecordNum.getDataNumAll(serverTable) + "   异常数量为:" + (list.size() - 1));

        } else if ("FileName".equals(column)) {
            List<FIleModel> list;
            header.createCell(0).setCellValue("文件名");
            header.createCell(1).setCellValue("文件地址");
            header.createCell(2).setCellValue("ip");
            header.createCell(3).setCellValue("检索词");
            //定义出异常的数量
            int q = 0;
            list = csw.searchMessage(serverTable, exp, false);
            //list = csw.searchMessage(serverTable,"WP_Content=(%"+getSensitiveWords(filePath[i])	+"%)",false);
            for (int z = 1; z < list.size() + 1; z++) {
                q = q + 1;
                FIleModel fIleModel = list.get(z - 1);
                XSSFRow header2 = sheet.createRow(q);
                header2.createCell(0).setCellValue(fIleModel.getFilename());
                header2.createCell(1).setCellValue(fIleModel.getFilepath());
                header2.createCell(2).setCellValue(fIleModel.getIp());
                header2.createCell(3).setCellValue(fIleModel.getKey());
            }
            bugNum = bugNum + list.size();
            header.createCell(4).setCellValue("记录数总量: " + CountTotalRecordNum.getDataNumAll(serverTable) + "   异常数量为:" + bugNum);

        } else if ("IR_CREATED_AT".equals(column)) {//增加微博库的类型
            List<WeiBo> list;
            header.createCell(0).setCellValue("IR_MID");
            header.createCell(1).setCellValue("IR_SCREEN_NAME");
            header.createCell(2).setCellValue("IR_SITENAME");
            header.createCell(3).setCellValue("url");
            header.createCell(4).setCellValue("内容");
            header.createCell(5).setCellValue("检索词");
            //定义出异常的数量
            int q = 0;
            list = csw.searchMessage(serverTable, exp, false);
            //list = csw.searchMessage(serverTable,"IR_SITENAME=(海油螺号,图说海油) and IR_STATUS_CONTENT=%"+getSensitiveWords(filePath[i])+"%",false);
            for (int z = 1; z < list.size() + 1; ) {
                WeiBo weiBo = list.get(z - 1);
                //去除白名单
                if (whiteMap.get(weiBo.getIR_URLNAME()) != null) continue;
                q = q + 1;
                XSSFRow header2 = sheet.createRow(q);
                header2.createCell(0).setCellValue(weiBo.getIR_MID());
                header2.createCell(1).setCellValue(weiBo.getIR_SCREEN_NAME());
                header2.createCell(2).setCellValue(weiBo.getIR_SITENAME());
                header2.createCell(3).setCellValue(weiBo.getIR_URLNAME());
                header2.createCell(4).setCellValue(weiBo.getIR_STATUS_CONTENT());
                header2.createCell(5).setCellValue(weiBo.getKeyword());
                z++;
            }
            bugNum = bugNum + list.size();
            header.createCell(6).setCellValue("记录数总量: " + CountTotalRecordNum.getDataNumAll(serverTable) + "   异常数量为:" + bugNum);

        } else if ("Y_ID".equals(column) || "id".equals(column)) {
            List<JCMS> list = null;
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("ip");
            header.createCell(2).setCellValue("数据库名称");
            header.createCell(3).setCellValue("表名称");
            header.createCell(4).setCellValue("文本");
            header.createCell(5).setCellValue("检索词");
            //定义出异常的数量
            int q = 0;
            list = csw.searchMessage(serverTable, exp, false);
            for (int z = 1; z < list.size() + 1; z++) {
                q = q + 1;
                JCMS jcms = list.get(z - 1);
                XSSFRow header2 = sheet.createRow(q);
                header2.createCell(0).setCellValue(jcms.getSID());
                header2.createCell(1).setCellValue(jcms.getSIP());
                header2.createCell(2).setCellValue(jcms.getDb_name());
                header2.createCell(3).setCellValue(jcms.getTB_NAME());
                header2.createCell(4).setCellValue(jcms.getTrscontent());
                header2.createCell(5).setCellValue(jcms.getKeyword());
            }
            bugNum = bugNum + list.size();
            header.createCell(6).setCellValue("记录数总量: " + CountTotalRecordNum.getDataNumAll(serverTable) + "   异常数量为:" + bugNum);
        }
        //设置列的宽度
        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            sheet.setColumnWidth(i, 255 * 20);
        }
        header.setHeightInPoints(30);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(DBConfig.detailFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //向指定文件写入内容
        try {
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 分网站统计
     *
     * @throws IOException
     */
    public static void countBySize() {
        String serverTable = DBConfig.serverTable;
        String[] filePath = {DBConfig.biaodashi};
        //使用数据库的的列来区分数据库
        String column = ConcatSiteName.getColumnName(serverTable);
        String columnname = "";
        //增加数据库的判断
        if ("IR_HKEY".equals(column)) {
            columnname = "微信名称";
        } else if ("IR_CREATED_AT".equals(column)) {
            columnname = "微博名称";
        } else if ("IR_SID".equals(column)) {            //增加微博库的类型
            columnname = "网站名称";
        } else if ("FileName".equals(column)) {
            columnname = "ip名称";
        } else if ("id".equals(column)) {
            columnname = "表名称";
        } else if ("Y_ID".equals(column)) {
            columnname = "表名称";
        }
        //增加敏感词的map集合
        HashMap<String, Integer> hashMapSpecialAll = ConcatSiteName.getSpecialSiteName(serverTable, getSensitiveWords(filePath[0]));
        //创建表格
        CreateTable.create(serverTable, columnname, hashMapSpecialAll);
    }
}
