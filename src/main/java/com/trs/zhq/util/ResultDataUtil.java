package com.trs.zhq.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ResultDataUtil {
	public String code;
	public String message;
	public Object datas;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatas() {
		return this.datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public static String toString(String code, String message, List datas) {
		ResultDataUtil result_ = new ResultDataUtil(code, message, datas);
		return JSON.toJSONString(result_);
	}

	public ResultDataUtil() {
	}

	public void ok(){
		this.code="1";
		this.message="success";
	}
	public void ok(String msg){
		this.code="1";
		this.message=msg;
	}
	public void error(){
		this.code="0";
		this.message="error";
	}
	public void error(String msg){
		this.code="0";
		this.message=msg;
	}
	public ResultDataUtil(String code, String message, List datas) {
		this.code = code;
		this.message = message;
		this.datas = datas;
	}

	public static void main(String[] args) {
		new ResultDataUtil();
		System.out.println(toString("1", "操作成功", new ArrayList()));
	}
}

/*
 * Location: C:\Users\UserHao\Desktop\通用框架\wms\WEB-INF\classes\
 * 
 * Qualified Name: com.wms.util.ResultDataUtil
 * 
 * JD-Core Version: 0.7.0.1
 */