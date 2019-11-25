package com.microservice.teacher.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "teachers")
public class Teacher {
  @Id
  private String id;
  @NotEmpty
  private String fullName;
  @NotEmpty
  private String typeDocument;
  @NotEmpty
  private String numberDocument;
  @NotNull
  private Boolean gender;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;

  private List<String> familyList;

  private List<String> coursesList;

  public Teacher() {
    coursesList = new ArrayList<>();
    familyList = new ArrayList<>();
  }

  public Teacher(String fullName, String typeDocument, String numberDocument, Boolean gender, Date birthdate) {
    this.fullName = fullName;
    this.typeDocument = typeDocument;
    this.numberDocument = numberDocument;
    this.gender = gender;
    this.birthdate = birthdate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getTypeDocument() {
    return typeDocument;
  }

  public void setTypeDocument(String typeDocument) {
    this.typeDocument = typeDocument;
  }

  public String getNumberDocument() {
    return numberDocument;
  }

  public void setNumberDocument(String numberDocument) {
    this.numberDocument = numberDocument;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public List<String> getCoursesList() {
    return coursesList;
  }

  public void setCoursesList(List<String> coursesList) {
    this.coursesList = coursesList;
  }

  public void addCourse(String course) {
    this.coursesList.add(course);
  }

  public List<String> getFamilyList() {
    return familyList;
  }

  public void setFamilyList(List<String> familyList) {
    this.familyList = familyList;
  }

  public void addFamily(String family) {
    this.familyList.add(family);
  }
}
