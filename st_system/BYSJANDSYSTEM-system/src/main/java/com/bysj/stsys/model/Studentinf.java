package com.bysj.stsys.model;


public class Studentinf {

  private String studentKey;
  private String studentName;
  private String studentProfession;
  private String delFlag;
  public Profession profession;

  @Override
  public String toString() {
    return "Studentinf{" +
            "studentKey='" + studentKey + '\'' +
            ", studentName='" + studentName + '\'' +
            ", studentProfession='" + studentProfession + '\'' +
            ", delFlag='" + delFlag + '\'' +
            ", profession=" + profession +
            '}';
  }

  public String getStudentKey() {
    return studentKey;
  }

  public void setStudentKey(String studentKey) {
    this.studentKey = studentKey;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public String getStudentProfession() {
    return studentProfession;
  }

  public void setStudentProfession(String studentProfession) {
    this.studentProfession = studentProfession;
  }


  public String getDelFlag() {
    return delFlag;
  }

  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

}
