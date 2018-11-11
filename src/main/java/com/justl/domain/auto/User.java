package com.justl.domain.auto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author buhuaqi
 * @date 2018-10-29 10:38
 */
public class User implements Serializable{

    private String objectId;
    private String salt;
    private String listId;
    private String property;
    private String email;
    private String sessionToken;
    private String acl;
    private String brith;
    private String password;
    private String assumpsitId;
    private String portrait;
    private String vip;
    private String username;
    private String sex;
    private String emailVerified;
    private String mobilePhoneNumber;
    private String name;
    private String emotional;
    private String coin;
    private String authData;
    private String mobilePhoneVerified;
    private Date createdAt;
    private Date updateAt;

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

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
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
