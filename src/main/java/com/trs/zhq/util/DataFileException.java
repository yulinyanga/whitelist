package com.trs.zhq.util;
/**
 * 数据文件异常：负责检查导入文件是否正确
 * */
public class DataFileException extends RuntimeException {
    public DataFileException(String message) {
        super(message);
    }

    public DataFileException() {
    }
}
