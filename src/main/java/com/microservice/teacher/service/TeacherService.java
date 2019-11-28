package com.microservice.teacher.service;

import com.microservice.teacher.model.dto.TeacherDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface TeacherService {
  Flux<TeacherDto> findAll();
  Mono<TeacherDto> findById(String id);
  Mono<TeacherDto> create(TeacherDto student);
  Mono<TeacherDto> update(TeacherDto student, String id);
  Mono<Void> delete(String id);
  Flux<TeacherDto> findByFullNameLikeIgnoreCase(String fullName);
  Mono<TeacherDto> findByNumberDocument(String numberDocument);
  Flux<TeacherDto> findByBirthdate(Date dateStart, Date dateEnd);
  Flux<TeacherDto> findByIdInstitute(String idInstitute);
}
