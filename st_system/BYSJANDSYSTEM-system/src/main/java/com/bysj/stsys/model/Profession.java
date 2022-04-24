package com.bysj.stsys.model;



public class Profession {

  private String professionKey;
  private String professionName;
  private String professionCollege;
  private String delFlag;
  public Collegeinf collegeinf;

  @Override
  public String toString() {
    return "Profession{" +
            "professionKey='" + professionKey + '\'' +
            ", professionName='" + professionName + '\'' +
            ", professionCollege='" + professionCollege + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", collegeinf=" + collegeinf +
            '}';
  }

  public String getProfessionKey() {
    return professionKey;
  }

  public void setProfessionKey(String professionKey) {
    this.professionKey = professionKey;
  }


  public String getProfessionName() {
    return professionName;
  }

  public void setProfessionName(String professionName) {
    this.professionName = professionName;
  }


  public String getProfessionCollege() {
    return professionCollege;
  }

  public void setProfessionCollege(String professionCollege) {
    this.professionCollege = professionCollege;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
