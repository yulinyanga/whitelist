package com.trs.zhq.entity;

import org.springframework.stereotype.Component;

@Component
public class WhiteList {

    //单位名称
    private String unitName;
    //应用分类
    private String classification;
    //应用编码
    private String code;
    //应用名称
    private String appName;
    //地址
    private String urlName;
    //命中关键词
    private String keywords;
    //用户
    private String username;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "WhiteList{" +
                "unitName='" + unitName + '\'' +
                ", classification='" + classification + '\'' +
                ", code='" + code + '\'' +
                ", appName='" + appName + '\'' +
                ", urlName='" + urlName + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
