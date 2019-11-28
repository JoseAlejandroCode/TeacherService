package com.microservice.teacher.service.impl;

import com.microservice.teacher.component.TeacherConverter;
import com.microservice.teacher.model.dto.TeacherDto;
import com.microservice.teacher.repository.TeacherRepository;
import com.microservice.teacher.service.FamilyService;
import com.microservice.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private FamilyService familyService;

  @Autowired
  private TeacherConverter teacherConverter;

  @Override
  public Flux<TeacherDto> findAll() {
    return teacherRepository.findAll()
            .flatMap(teacher -> {
              TeacherDto teacherDto = teacherConverter.convertToDto(teacher);
              teacherDto.setFamilyList(familyService.findByStudent(teacher.getId()).collectList().block());
              return Mono.just(teacherDto);
            });
  }

  @Override
  public Mono<TeacherDto> findById(String id) {
    return teacherRepository.findById(id)
            .flatMap(teacher -> {
              TeacherDto teacherDto = teacherConverter.convertToDto(teacher);
              teacherDto.setFamilyList(familyService.findByStudent(teacher.getId()).collectList().block());
              return Mono.just(teacherDto);
            });
  }

  @Override
  public Mono<TeacherDto> create(TeacherDto teacher) {
    return teacherRepository.save(teacherConverter.convertToDocument(teacher))
            .flatMap(s -> Mono.just(teacherConverter.convertToDto(s)))
            .flatMap(s -> {
              teacher.getFamilyList().forEach(family -> {
                family.addPartner(s);
                 s.addFamily(familyService.save(family).block());
              });
              return update(s, s.getId());
            });
  }

  @Override
  public Mono<TeacherDto> update(TeacherDto teacher, String id) {
    return findById(id).flatMap(s -> {
      s.setFullName(teacher.getFullName());
      s.setBirthdate(teacher.getBirthdate());
      s.setGender(teacher.getGender());
      s.setTypeDocument(teacher.getTypeDocument());
      s.setNumberDocument(teacher.getNumberDocument());
      teacher.getFamilyList().forEach(family -> s.addFamily(family));
      teacher.getCourseList().forEach(course -> s.addCourse(course));
      return teacherRepository.save(teacherConverter.convertToDocument(s))
              .flatMap(st -> Mono.just(teacherConverter.convertToDto(st)))
              .flatMap(st -> {
                teacher.getFamilyList().forEach(family -> {
                  family.addPartner(st);
                  st.addFamily(familyService.update(family, family.getId()).block());
                });
                return Mono.just(st);
              });
    });
  }

  @Override
  public Mono<Void> delete(String  id) {
    return findById(id)
              .flatMap(s -> teacherRepository.delete(teacherConverter.convertToDocument(s))
                        .zipWith(familyService.deleteByStudent(s.getId()))
                        .then());
  }

  @Override
  public Flux<TeacherDto> findByFullNameLikeIgnoreCase(String fullName) {
    return teacherRepository.findByFullNameIgnoreCaseLike(fullName)
            .flatMap(teacher -> {
              TeacherDto teacherDto = teacherConverter.convertToDto(teacher);
              teacherDto.setFamilyList(familyService.findByStudent(teacher.getId()).collectList().block());
              return Mono.just(teacherDto);
            });
  }

  @Override
  public Mono<TeacherDto> findByNumberDocument(String numberDocument) {
    return teacherRepository.findByNumberDocument(numberDocument)
            .flatMap(teacher -> {
              TeacherDto teacherDto = teacherConverter.convertToDto(teacher);
              teacherDto.setFamilyList(familyService.findByStudent(teacher.getId()).collectList().block());
              return Mono.just(teacherDto);
            });
  }

  @Override
  public Flux<TeacherDto> findByBirthdate(Date dateStart, Date dateEnd) {
    return teacherRepository.findByBirthdateBetween(dateStart, dateEnd)
            .flatMap(teacher -> {
              TeacherDto teacherDto = teacherConverter.convertToDto(teacher);
              teacherDto.setFamilyList(familyService.findByStudent(teacher.getId()).collectList().block());
              return Mono.just(teacherDto);
            });
  }

  @Override
  public Flux<TeacherDto> findByIdInstitute(String idInstitute) {
    return teacherRepository.findByIdInstitute(idInstitute)
            .flatMap(teacher -> Mono.just(teacherConverter.convertToDto(teacher)));
  }
}
