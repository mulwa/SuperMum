package com.example.gen.supermum.Pojo;

public class User {
    private String username;
    private String email;
    private String mobileNo;
    private String conceptionDate;
    private String Uid;

    public User() {
    }

    public User(String username, String email, String mobileNo,
                String conceptionDate, String uid) {
        this.username = username;
        this.email = email;
        this.mobileNo = mobileNo;
        this.conceptionDate = conceptionDate;
        Uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getConceptionDate() {
        return conceptionDate;
    }

    public void setConceptionDate(String conceptionDate) {
        this.conceptionDate = conceptionDate;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
