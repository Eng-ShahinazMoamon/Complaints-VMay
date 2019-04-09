package com.example.shahi.citizenscomplaints.Model;

public class UserModel {
    private String userName;
    private String userPhone;
    private String userId;
    private String userAddress;
    private String instituationName;
    private String subject;
    private String description;
    private String comPhoto;



    public UserModel(String userName, String userPhone, String userId, String userAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userId = userId;
        this.userAddress = userAddress;
        this.instituationName = instituationName;
        this.subject = subject;
        this.description = description;
        this.comPhoto = comPhoto;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getInstituationName() {
        return instituationName;
    }

    public void setInstituationName(String instituationName) {
        this.instituationName = instituationName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComPhoto() {
        return comPhoto;
    }

    public void setComPhoto(String comPhoto) {
        this.comPhoto = comPhoto;
    }
}