package com.justl.domain.auto;

import java.util.Date;

/**
 * 举报数据模型
 *
 * @author buhuaqi
 * @date 2018-11-13 15:42
 */
public class ReportDomain {
    private boolean appeal;
    private String reportType;
    private String sex;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private int reportNum;
    /**
     * 被举报用户电话
     **/
    private String userPhoneNumber;
    /**
     * 被举报iOATH
     **/
    private Integer iOATH;
    /**
     * 被举报ObjectId
     **/
    private String reObjectId;

    public boolean isAppeal() {
        return appeal;
    }

    public void setAppeal(boolean appeal) {
        this.appeal = appeal;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Integer getiOATH() {
        return iOATH;
    }

    public void setiOATH(Integer iOATH) {
        this.iOATH = iOATH;
    }

    public String getReObjectId() {
        return reObjectId;
    }

    public void setReObjectId(String reObjectId) {
        this.reObjectId = reObjectId;
    }
}
