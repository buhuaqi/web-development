package com.justl.domain.auto;

import java.io.Serializable;
import java.util.Date;

/**
 * 注：字段名称必须与数据库完全一致
 * @author buhuaqi
 * @date 2018-10-29 10:38
 */
public class User implements Serializable {

    //动态id，唯一
    private String objectId;
    //盐（盐值加密）
    private String salt;
    private String listId;
    private String property;
    private String email;
    private String sessionToken;
    private String acl;
    private String brith;
    private String password;
    private String assumpsitId;
    //文件资源名
    private String portrait;
    private boolean VIP;
    private String Constellation;
    private String username;
    private String sex;
    private String emailVerified;
    //手机号
    private String mobilePhoneNumber;
    private String name;
    private String emotional;
    private String coin;
    //用户动态
    private String authData;
    private String mobilePhoneVerified;
    private Date createdAt;
    private Date updateAt;
    private String constellation;
    private String admin;
    private String giftNum;
    private boolean Buyer;
    //给别人点赞数
    private Integer giveThumbsupNum;
    //被赞数
    private Integer thumbsupNum;
    private String weight;
    private String height;
    private String age;
    //iOATH号
    private Integer iOATHNum;
    //节操分数
    private Integer CScore;

    public Integer getiOATHNum() {
        return iOATHNum;
    }

    public void setiOATHNum(Integer iOATHNum) {
        this.iOATHNum = iOATHNum;
    }

    public Integer getCScore() {
        return CScore;
    }

    public void setCScore(Integer CScore) {
        this.CScore = CScore;
    }

    public Integer getReportNum() {
        return ReportNum;
    }

    public void setReportNum(Integer reportNum) {
        ReportNum = reportNum;
    }

    //被举报次数
    private Integer ReportNum;

    //是否为推广员
    private boolean Promoters;

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public boolean isPromoters() {
        return Promoters;
    }

    public void setPromoters(boolean promoters) {
        this.Promoters = promoters;
    }

    public boolean isBuyer() {
        return Buyer;
    }

    public void setBuyer(boolean buyer) {
        this.Buyer = buyer;
    }

    public Integer getGiveThumbsupNum() {
        return giveThumbsupNum;
    }

    public void setGiveThumbsupNum(Integer giveThumbsupNum) {
        this.giveThumbsupNum = giveThumbsupNum;
    }

    public Integer getThumbsupNum() {
        return thumbsupNum;
    }

    public void setThumbsupNum(Integer thumbsupNum) {
        this.thumbsupNum = thumbsupNum;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getBrith() {
        return brith;
    }

    public void setBrith(String brith) {
        this.brith = brith;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAssumpsitId() {
        return assumpsitId;
    }

    public void setAssumpsitId(String assumpsitId) {
        this.assumpsitId = assumpsitId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public boolean getVip() {
        return VIP;
    }

    public void setVip(boolean VIP) {
        this.VIP = VIP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmotional() {
        return emotional;
    }

    public void setEmotional(String emotional) {
        this.emotional = emotional;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }

    public String getMobilePhoneVerified() {
        return mobilePhoneVerified;
    }

    public void setMobilePhoneVerified(String mobilePhoneVerified) {
        this.mobilePhoneVerified = mobilePhoneVerified;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
