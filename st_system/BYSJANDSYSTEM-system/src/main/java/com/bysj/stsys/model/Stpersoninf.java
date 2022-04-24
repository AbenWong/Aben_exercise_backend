package com.bysj.stsys.model;


public class Stpersoninf {



  private String stpeKey;
  private String stpePersonid;
  private String stpeStid;
  private String delFlag;
  private String isSz;
  private String joinTime;


  public String getJoinTime() {
    return joinTime;
  }

  @Override
  public String toString() {
    return "Stpersoninf{" +
            "stpeKey='" + stpeKey + '\'' +
            ", stpePersonid='" + stpePersonid + '\'' +
            ", stpeStid='" + stpeStid + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", isSz='" + isSz + '\'' +
            ", joinTime='" + joinTime + '\'' +
            ", stinf=" + stinf +
            ", user=" + user +
            ", studentinf=" + studentinf +
            '}';
  }

  public Stinf getStinf() {
    return stinf;
  }

  public void setStinf(Stinf stinf) {
    this.stinf = stinf;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Studentinf getStudentinf() {
    return studentinf;
  }

  public void setStudentinf(Studentinf studentinf) {
    this.studentinf = studentinf;
  }

  public void setJoinTime(String joinTime) {
    this.joinTime = joinTime;
  }

  public Stinf stinf;
  public User user;
  public Studentinf studentinf;
  public String getIsSz() {
    return isSz;
  }

  public void setIsSz(String isSz) {
    this.isSz = isSz;
  }

  public String getStpeKey() {
    return stpeKey;
  }

  public void setStpeKey(String stpeKey) {
    this.stpeKey = stpeKey;
  }


  public String getStpePersonid() {
    return stpePersonid;
  }

  public void setStpePersonid(String stpePersonid) {
    this.stpePersonid = stpePersonid;
  }


  public String getStpeStid() {
    return stpeStid;
  }

  public void setStpeStid(String stpeStid) {
    this.stpeStid = stpeStid;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
