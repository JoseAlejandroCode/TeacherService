package com.microservice.teacher.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDto {
  private String id;
  @NotEmpty
  private String name;
  @NotNull
  private Integer status;
  @NotNull
  private Integer minimumQuota;
  @NotNull
  private Integer maximumQuota;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startCourse;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endCourse;
  private List<StudentDto> studentList;
  private TeacherDto teacher;
  private List<FamilyDto> familyList;

  private InstituteDto institute;

  public InstituteDto getInstitute() {
    return institute;
  }

  public void setInstitute(InstituteDto institute) {
    this.institute = institute;
  }

  public CourseDto() {
    this.studentList = new ArrayList<>();
    this.familyList = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getMinimumQuota() {
    return minimumQuota;
  }

  public void setMinimumQuota(Integer minimumQuota) {
    this.minimumQuota = minimumQuota;
  }

  public Integer getMaximumQuota() {
    return maximumQuota;
  }

  public void setMaximumQuota(Integer maximumQuota) {
    this.maximumQuota = maximumQuota;
  }

  public Date getStartCourse() {
    return startCourse;
  }

  public void setStartCourse(Date startCourse) {
    this.startCourse = startCourse;
  }

  public Date getEndCourse() {
    return endCourse;
  }

  public void setEndCourse(Date endCourse) {
    this.endCourse = endCourse;
  }

  public List<StudentDto> getStudentList() {
    return studentList;
  }

  public void setStudentList(List<StudentDto> studentList) {
    this.studentList = studentList;
  }

  public void addStudent(StudentDto student) {
    this.studentList.add(student);
  }

  public TeacherDto getTeacher() {
    return teacher;
  }

  public void setTeacher(TeacherDto teacher) {
    this.teacher = teacher;
  }

  public List<FamilyDto> getFamilyList() {
    return familyList;
  }

  public void setFamilyList(List<FamilyDto> familyList) {
    this.familyList = familyList;
  }

  public void addFamily(FamilyDto family) {
    this.familyList.add(family);
  }
}
