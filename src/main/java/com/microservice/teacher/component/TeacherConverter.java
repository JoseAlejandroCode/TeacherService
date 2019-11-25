package com.microservice.teacher.component;

import com.microservice.teacher.model.document.Teacher;
import com.microservice.teacher.model.dto.TeacherDto;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {

  public TeacherDto convertToDto(Teacher teacher){
    TeacherDto teacherDto = new TeacherDto();
    teacherDto.setId(teacher.getId());
    teacherDto.setFullName(teacher.getFullName());
    teacherDto.setTypeDocument(teacher.getTypeDocument());
    teacherDto.setNumberDocument(teacher.getNumberDocument());
    teacherDto.setGender(teacher.getGender());
    teacherDto.setBirthdate(teacher.getBirthdate());
    return teacherDto;
  }

  public Teacher convertToDocument(TeacherDto teacherDTO){
    Teacher teacher = new Teacher();
    teacher.setId(teacherDTO.getId());
    teacher.setFullName(teacherDTO.getFullName());
    teacher.setTypeDocument(teacherDTO.getTypeDocument());
    teacher.setNumberDocument(teacherDTO.getNumberDocument());
    teacher.setGender(teacherDTO.getGender());
    teacher.setBirthdate(teacherDTO.getBirthdate());
    teacherDTO.getFamilyList().forEach(f -> teacher.addFamily(f.getId()));
    teacherDTO.getCourseList().forEach(c -> teacher.addCourse(c.getId()));
    return teacher;
  }

}
