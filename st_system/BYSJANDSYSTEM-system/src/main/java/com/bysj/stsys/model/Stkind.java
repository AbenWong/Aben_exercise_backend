package com.bysj.stsys.model;


public class Stkind {

  private String stkindKey;
  private String stkindName;
  private String delFlag;

  @Override
  public String toString() {
    return "Stkind{" +
            "stkindKey='" + stkindKey + '\'' +
            ", stkindName='" + stkindName + '\'' +
            ", delFlag='" + delFlag + '\'' +
            '}';
  }

  public String getStkindKey() {
    return stkindKey;
  }

  public void setStkindKey(String stkindKey) {
    this.stkindKey = stkindKey;
  }


  public String getStkindName() {
    return stkindName;
  }

  public void setStkindName(String stkindName) {
    this.stkindName = stkindName;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
