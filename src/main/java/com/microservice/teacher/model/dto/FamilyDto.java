package com.microservice.teacher.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class FamilyDto extends PersonDto {

  private List<TeacherDto> partnerList;
  @NotEmpty
  private String relationship;

  public FamilyDto() {
    super();
    partnerList = new ArrayList<>();
  }

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public List<TeacherDto> getPartnerList() {
    return partnerList;
  }

  public void setPartnerList(List<TeacherDto> partnerList) {
    this.partnerList = partnerList;
  }

  public void addPartner(TeacherDto partner) {
    this.partnerList.add(partner);
  }
}
