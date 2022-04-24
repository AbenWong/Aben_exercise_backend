package com.bysj.stsys.model;



public class Stinf {

  private String stKey;
  private String stName;
  private String presidentid;
  private String stKind;
  private String stStatue;
  private java.sql.Date stCreatetime;
  private String delFlag;
  private String stIntf;
  private String stLogo;

  @Override
  public String toString() {
    return "Stinf{" +
            "stKey='" + stKey + '\'' +
            ", stName='" + stName + '\'' +
            ", presidentid='" + presidentid + '\'' +
            ", stKind='" + stKind + '\'' +
            ", stStatue='" + stStatue + '\'' +
            ", stCreatetime=" + stCreatetime +
            ", delFlag='" + delFlag + '\'' +
            ", stIntf='" + stIntf + '\'' +
            ", stLogo='" + stLogo + '\'' +
            ", stkind=" + stkind +
            ", studentinf=" + studentinf +
            '}';
  }

  public String getStLogo() {
    return stLogo;
  }

  public void setStLogo(String stLogo) {
    this.stLogo = stLogo;
  }

  public String getStIntf() {
    return stIntf;
  }

  public void setStIntf(String stIntf) {
    this.stIntf = stIntf;
  }

  public Stkind stkind;
  public Studentinf studentinf;


  public String getStKey() {
    return stKey;
  }

  public void setStKey(String stKey) {
    this.stKey = stKey;
  }


  public String getStName() {
    return stName;
  }

  public void setStName(String stName) {
    this.stName = stName;
  }


  public String getPresidentid() {
    return presidentid;
  }

  public void setPresidentid(String presidentid) {
    this.presidentid = presidentid;
  }


  public String getStKind() {
    return stKind;
  }

  public void setStKind(String stKind) {
    this.stKind = stKind;
  }


  public String getStStatue() {
    return stStatue;
  }

  public void setStStatue(String stStatue) {
    this.stStatue = stStatue;
  }


  public java.sql.Date getStCreatetime() {
    return stCreatetime;
  }

  public void setStCreatetime(java.sql.Date stCreatetime) {
    this.stCreatetime = stCreatetime;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
