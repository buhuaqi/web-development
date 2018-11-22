package com.justl.domain.auto;

import java.io.Serializable;

public class BlackRoom implements Serializable {
    //用户账号
    private String  mobilePhoneNumber;
    //用户昵称
    private String Name;
    //性别
    private String Sex;
    //被举报数
    private String ReportNum;
    //  被举报内类型
    private String  ReportType;
    //被举报阵容objectId
    private String ReportObjectid;
   //申报标识
    private Boolean Appeal;

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getReportNum() {
        return ReportNum;
    }

    public void setReportNum(String reportNum) {
        ReportNum = reportNum;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public String getReportObjectid() {
        return ReportObjectid;
    }

    public void setReportObjectid(String reportObjectid) {
        ReportObjectid = reportObjectid;
    }

    public Boolean getAppeal() {
        return Appeal;
    }

    public void setAppeal(Boolean appeal) {
        Appeal = appeal;
    }
}
