package com.bysj.stsys.model;


public class Collegeinf {

  private String collegeKey;
  private String collegeName;
  private String delKey;

  @Override
  public String toString() {
    return "Collegeinf{" +
            "collegeKey='" + collegeKey + '\'' +
            ", collegeName='" + collegeName + '\'' +
            ", delKey='" + delKey + '\'' +
            '}';
  }

  public String getCollegeKey() {
    return collegeKey;
  }

  public void setCollegeKey(String collegeKey) {
    this.collegeKey = collegeKey;
  }


  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }


  public String getDelKey() {
    return delKey;
  }

  public void setDelKey(String delKey) {
    this.delKey = delKey;
  }

}
