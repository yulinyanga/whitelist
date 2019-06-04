package com.trs.zhq.config;


import com.eprobiti.trs.TRSConnection;
import com.eprobiti.trs.TRSException;
import com.trs.zhq.util.CountSensitiveWords;

import java.io.IOException;
import java.time.LocalDate;

/**
 * 数据库连接
 *
 * @author Hermes
 */
public class DBConfig {
    //todo 在导出前更新 ip 库名 数据库类型 表达式 存放路径(desktop、countFile、detailFile、logPath)
    public final static String DB_URL_WHITELIST = "127.0.0.1";	  //白名单ip
    public final static String DB_PORT_WHITELIST = "9999";	  //白名单port
    public final static String DB_TABLE_WHITELIST = "Demo1";        //TRSServer数据库名  WeiXin  WeiBo

    public final static String DB_URL_IN = "10.72.76.187";	  //内网
    public final static String DB_URL_WEI = "10.72.76.89";      //微博微信
    public final static String DB_URL_OUT = "10.72.76.73";      //外网
    public static String DB_URL = "";      //外网
    public final static String DB_PORT = "9999";               //TRSServer端口
    public final static String DB_USERNAME = "system";            //TRSServer用户名
    public final static String DB_PASSWORD = "manager";         //TRSServer密码
    public final static String serverTable = "Demo2";        //TRSServer数据库名  WeiXin  WeiBo
    public final static String groupType = "微信";            //数据库类型  分为  外部网站、内部网站、微博、微信
    public final static String desktop = "C:\\Users\\11633\\Desktop\\";
    //    public final static String biaodashi = desktop + "信息报表\\0507内网表达式.txt";        //内网表达式
    public final static String biaodashi = desktop + "信息报表\\0514外网表达式.txt";        //外网表达式
    public final static String countFile = desktop + "信息报表\\疑似信息\\" + groupType + "_疑似信息统计_" + LocalDate.now() + "_" + serverTable + ".xlsx";        //存放疑似信息统计
    public final static String detailFile = desktop + "信息报表\\疑似信息\\" + groupType + "_疑似信息详细_" + LocalDate.now() + "_" + serverTable + ".xlsx";        //存放疑似信息详细
    public final static String logPath = desktop + "信息报表\\log.txt";        //存放疑似信息详细
    public final static String siteList = "";                                     //增加特殊sitename

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String args[]) throws IOException {
        //统计列表
        CountSensitiveWords.countBySize();
        //详细信息
//        CountSensitiveWords.detail();
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public TRSConnection getDBConnection() {
        TRSConnection conn = null;
        try {
            conn = new TRSConnection();
            conn.connect(DB_URL, DB_PORT, DB_USERNAME, DB_PASSWORD, serverTable);
        } catch (TRSException e) {
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("ErrorString: " + e.getErrorString());
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param
     */
    public void closeConnection(TRSConnection conn) {
        try {
            if (conn != null) {
                if (!conn.isClosed()) {   //判断当前连接连接对象如果没有被关闭就调用关闭方法
                    conn.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
