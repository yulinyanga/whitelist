package com.trs.zhq.util;

public class TRSSearchUtil {


    public static String formatKeyWord(String keyword) {
        keyword = keyword.replaceAll("\\\\", "\\\\\\\\");
        keyword = keyword.replaceAll("\\s", "\\\\\\ ");//转意空格
        keyword = keyword.replaceAll("\\(", "\\\\\\(").replaceAll("\\)", "\\\\\\)");//转意括号
//        keyword = keyword.replaceAll("\\{", "\\\\\\{").replaceAll("\\}", "\\\\\\}");//转意括号
//        keyword = keyword.replaceAll("\\[", "\\\\\\[").replaceAll("\\]", "\\\\\\]");//转意括号
        keyword = keyword.replaceAll("/", "\\\\\\/");
        keyword = keyword.replaceAll(":", "\\\\\\:");
        keyword = keyword.replaceAll("-", "\\\\\\-");
        keyword = keyword.replaceAll("\\+", "\\\\\\+");
        keyword = keyword.replaceAll("\\*", "\\\\\\*");
        keyword = keyword.replaceAll("%", "\\\\\\%");
        keyword = keyword.replaceAll("=", "\\\\\\=");
//        keyword = keyword.replaceAll("\\\"", "\\\\\\\"");
        keyword = keyword.replaceAll("<", "\\\\\\<");
        keyword = keyword.replaceAll(">", "\\\\\\>");
        keyword = keyword.replaceAll("\\|", "\\\\\\|");
        keyword = keyword.replaceAll("&", "\\\\\\&");
        keyword = keyword.replaceAll("\\?", "\\\\\\?");
        keyword = keyword.replaceAll("\\!", "\\\\\\!");
        keyword = keyword.replaceAll("\\^", "\\\\\\^");
        keyword = keyword.replaceAll("\\~", "\\\\\\~");
        keyword = keyword.replaceAll("!", "\\\\\\!");
        keyword = keyword.replaceAll("\\?", "\\\\\\?");
        keyword = keyword.replaceAll("#", "\\\\\\#");
        return keyword;
    }
}
