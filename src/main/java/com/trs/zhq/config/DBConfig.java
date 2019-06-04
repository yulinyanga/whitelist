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
//    public final static String DB_URL_WHITELIST = "10.72.76.73";      //白名单ip
    public final static String DB_URL_WHITELIST = "127.0.0.1";      //白名单ip
    public final static String DB_PORT_WHITELIST = "8888";      //白名单port
    public final static String DB_TABLE_WHITELIST = "white";        //TRSServer数据库名  WeiXin  WeiBo

    public final static String DB_URL_WEB = "127.0.0.1";      //网站
    public final static String DB_URL_WEI = "10.72.76.89";      //微博微信
    public static String DB_URL = "";      //外网
//    public static String TABLE_WEB = "AS$37$68$1";          //0529www
    public static String TABLE_WEB = "AS$8$14$1";          //ceshi
    public static String TABLE_INTRANET = "AS$2D$58$1";     //051702_nw
    public static String TABLE_WEIXIN = "WeiXin0524";
    public static String TABLE_WEINBO = "WeiBo0521";
    public static String serverTable = "";        //TRSServer数据库名  WeiXin  WeiBo
    public static String groupType = "";            //数据库类型  分为  外部网站、内部网站、微博、微信
    public static String biaodashi = "";      //表达式

    public final static String DB_PORT = "8888";               //TRSServer端口
    public final static String DB_USERNAME = "system";            //TRSServer用户名
    public final static String DB_PASSWORD = "manager";         //TRSServer密码
    public final static String biaodashi_intranet = "C:\\信息报表\\0507内网表达式.txt";        //内网表达式
    public final static String biaodashi_web = "C:\\信息报表\\0601外网表达式.txt";        //外网表达式
    public final static String countFile = "C:\\信息报表\\疑似信息\\" + groupType + "_疑似信息统计_" + LocalDate.now() + ".xlsx";        //存放疑似信息统计
    public final static String detailFile = "C:\\信息报表\\疑似信息\\" + groupType + "_疑似信息详细_" + LocalDate.now() + ".xlsx";        //存放疑似信息详细
    //    public final static String countFile = desktop + "信息报表\\疑似信息\\" + groupType + "_疑似信息统计_" + LocalDate.now() + "_" + serverTable + ".xlsx";        //存放疑似信息统计
//    public final static String detailFile = desktop + "信息报表\\疑似信息\\" + groupType + "_疑似信息详细_" + LocalDate.now() + "_" + serverTable + ".xlsx";        //存放疑似信息详细
    public final static String logPath = "C:\\信息报表\\log.txt";        //存放疑似信息详细

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
