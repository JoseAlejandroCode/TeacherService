package com.microservice.teacher.model.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class TeacherDto extends PersonDto {

  @ApiModelProperty(value = "List family of teachers", required = false)
  private List<FamilyDto> familyList;

  public TeacherDto() {
    super();
    familyList = new ArrayList<>();
  }

  public void setFamilyList(List<FamilyDto> familyList) {
    this.familyList = familyList;
  }

  public List<FamilyDto> getFamilyList() {
    return familyList;
  }

  public void addFamily(FamilyDto family) {
    this.familyList.add(family);
  }
}
