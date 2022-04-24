package com.bysj.stsys.model;


public class User {

  private String userTel;

  @Override
  public String toString() {
    return "User{" +
            "userTel='" + userTel + '\'' +
            ", userStid='" + userStid + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userPhoto='" + userPhoto + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", studentinf=" + studentinf +
            '}';
  }

  private String userStid;
  private String userPassword;
  private String userPhoto;
  private String delFlag;
  public Studentinf studentinf;

  public Studentinf getStudentinf() {
    return studentinf;
  }

  public void setStudentinf(Studentinf studentinf) {
    this.studentinf = studentinf;
  }

  public String getUserTel() {
    return userTel;
  }

  public void setUserTel(String userTel) {
    this.userTel = userTel;
  }


  public String getUserStid() {
    return userStid;
  }

  public void setUserStid(String userStid) {
    this.userStid = userStid;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserPhoto() {
    return userPhoto;
  }

  public void setUserPhoto(String userPhoto) {
    this.userPhoto = userPhoto;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
