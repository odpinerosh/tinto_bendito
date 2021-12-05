package com.tintobendito.etb.modelos;

public class Usuario {
    private String id;
    private String firstNameUser;
    private String lastNameUser;
    private String mobileUser;
    private String mailUser;
    private String imgUser;
    private long timeStampUser;

    public Usuario() {

    };

    public Usuario(String id, String firstNameUser, String lastNameUser, String mobileUser, String mailUser, String imgUser, long timeStampUser) {
        this.id = id;
        this.firstNameUser = firstNameUser;
        this.lastNameUser = lastNameUser;
        this.mobileUser = mobileUser;
        this.mailUser = mailUser;
        this.imgUser = imgUser;
        this.timeStampUser = timeStampUser;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser = firstNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getMobileUser() {
        return mobileUser;
    }

    public void setMobileUser(String mobileUser) {
        this.mobileUser = mobileUser;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public long getTimeStampUser() {
        return timeStampUser;
    }

    public void setTimeStampUser(long timeStampUser) {
        this.timeStampUser = timeStampUser;
    }


}
